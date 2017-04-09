package com.autentia.tutorial.akka.futures;

import akka.actor.ActorSystem;
import akka.dispatch.Futures;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;

import java.util.List;

import static java.util.Arrays.asList;

class TraverseFutures {

    private final LoggingAdapter log;
    private final ExecutionContext ec;

    TraverseFutures(ActorSystem system) {
        log = Logging.getLogger(system, getClass());
        ec = system.dispatcher();
    }

    void runExample() {
        log.info("*** Init");

        final List<String> listOfWords = asList("Hola", " mundo", " del futuro!!!");

        final Future<Iterable<String>> futureListOfString = Futures.traverse(
                listOfWords,
                word -> Futures.future(() -> {
                    log.info("init");
                    final String upper = word.toUpperCase();
                    log.info("end");
                    return upper;
                    }, ec),
                ec
        );

        futureListOfString.onSuccess(new PrintResult<>(log), ec);

        log.info("*** End");
    }

}
