package samples;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinRouter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MasterActor extends UntypedActor {
    
    public static class Word {
        final int id;
        final String word;
        
        public Word(int id, String word) {
            this.id = id;
            this.word = word;
        }

        @Override
        public String toString() {
            return word;
        }

        @Override
        public int hashCode() {
            return word == null ? 0 : word.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (!(obj instanceof Word)) {
                return false;
            }
            final Word other = (Word) obj;
            return Objects.equals(word, other.word);
        }
    }
    
    public static class WordMatch {
        final Word word;
        final int matches;
        
        public WordMatch(Word word, int matches) {
            this.word = word;
            this.matches = matches;
        }

        @Override
        public String toString() {
            return new StringBuilder("Word: ").append(word).append(", Matches: ").append(matches).toString();
        }
    }
    
    private final Map<Word, Integer> matches = new HashMap<>();
    private final int workers;
    private int wordCount;
    private int received;
    
    public MasterActor(int workers) {
        this.workers = workers;
    }

    @Override
    public void onReceive(Object o) throws Exception {
        if (o instanceof String) {
            String filename = (String) o;
            ActorRef print = context().actorOf(Props.create(PrintWordActor.class).withRouter(new RoundRobinRouter(workers)), "printer");
            final String[] lines = readLines(filename);
            wordCount = lines.length;
            System.out.println("Analyzing " + wordCount + " words and their reverses");
            for (int i = 0; i < wordCount; i++) {
                Word word = new Word(i, lines[i]);
                print.tell(word, self());
            }
        } else if (o instanceof WordMatch) {
            WordMatch wm = (WordMatch) o;
            received++;
            if (matches.containsKey(wm.word)) {
                Integer val = matches.get(wm.word);
                val += wm.matches;
                matches.put(wm.word, val);
            } else {
                matches.put(wm.word, wm.matches);
            }
            
            // Shutdown if all words received
            if (received >= wordCount * 2) {
                context().system().shutdown();
            }
        }
    }

    private static String[] readLines(String filename) throws IOException {
        FileReader fileReader = new FileReader(filename);
        List<String> lines;
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            lines = new ArrayList<>();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines.toArray(new String[lines.size()]);
    }

}
