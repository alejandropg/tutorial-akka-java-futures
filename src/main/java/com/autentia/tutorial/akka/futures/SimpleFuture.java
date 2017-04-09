package com.autentia.tutorial.akka.futures;

import akka.actor.ActorSystem;
import akka.dispatch.Futures;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;

class SimpleFuture {

    private final LoggingAdapter log;
    private final ExecutionContext ec;

    SimpleFuture(ActorSystem system) {
        log = Logging.getLogger(system, getClass());
        ec = system.dispatcher();
    }

    void runExample() {
        log.info("*** Init");

        final Future<String> f1 = Futures.future(
                () -> "Hola" + " mundo del futuro!!!",
                ec
        );

        f1.onSuccess(new PrintResult<>(log), ec);

        log.info("*** End");
    }

}
