package com.autentia.tutorial.akka.futures;

import akka.actor.ActorSystem;
import akka.dispatch.Futures;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;

import static java.util.Arrays.asList;

class FoldFutures {

    private final LoggingAdapter log;
    private final ExecutionContext ec;
    private final Business business;

    FoldFutures(ActorSystem system) {
        log = Logging.getLogger(system, getClass());
        ec = system.dispatcher();
        business = new Business(log);
    }

    void runExample() {
        log.info("*** Init");

        final Future<String> f1 = Futures.future(business::action1, ec);
        final Future<String> f2 = Futures.future(business::action2, ec);
        final Future<String> f3 = Futures.future(business::action3, ec);

        final Future<String> futureString = Futures.fold(
                "",
                asList(f1, f2, f3),
                (previousResult, param) -> previousResult + param,
                ec
        );

        futureString.onSuccess(new PrintResult<>(log), ec);

        log.info("*** End");
    }

}
