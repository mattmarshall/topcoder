package samples;

import akka.actor.UntypedActor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import samples.MasterActor.Word;

public class PrintWordActor extends UntypedActor {
    
    private static final Pattern pattern = Pattern.compile("foo");

    @Override
    public void onReceive(Object o) throws Exception {
        if (o instanceof Word) {
            Word word = (Word) o;
            matchWord(word);
            Word reverse = new Word(word.id, new StringBuilder(word.word).reverse().toString());
            matchWord(reverse);
        }
    }
    
    private void matchWord(Word word) {
        Matcher matcher = pattern.matcher(word.word);
        int count = 0;
        while (matcher.find()) {
            count++;
        }

        // Send message with match results
        MasterActor.WordMatch wm = new MasterActor.WordMatch(word, count);
        sender().tell(wm, self());
    }    
}
