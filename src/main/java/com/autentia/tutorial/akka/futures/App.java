package com.autentia.tutorial.akka.futures;

import akka.actor.ActorSystem;

public class App {

    public static void main(String[] args) throws InterruptedException {
        final App app = new App();
        app.execute();
    }

    private void execute() throws InterruptedException {
        final ActorSystem system = boot();

//        new SimpleFuture(system).runExample();
//        new MapFutures(system).runExample();
//        new TraverseFutures(system).runExample();
//        new SequenceFutures(system).runExample();
//        new FoldFutures(system).runExample();
        new ReduceFutures(system).runExample();

        shutdown(system);
    }

    private ActorSystem boot() {
        return ActorSystem.create("actorSystem");
    }

    private void shutdown(ActorSystem system) throws InterruptedException {
        Thread.sleep(3);
        system.terminate();
    }

}
