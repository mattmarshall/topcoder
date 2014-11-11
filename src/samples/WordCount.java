package samples;

import akka.actor.ActorRef;
import java.io.IOException;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class WordCount {

    public static void main(String[] args) throws IOException {

        long startTime = System.currentTimeMillis();
        ActorSystem actorSystem = ActorSystem.create("system");

        // create the master
        ActorRef master = actorSystem.actorOf(Props.create(MasterActor.class, 1), "master");
        master.tell("C:\\words.txt", actorSystem.deadLetters());

        actorSystem.awaitTermination();
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Elapsed time: " + elapsedTime + "ms");
    }
}
