package com.autentia.tutorial.akka.futures;

import akka.actor.ActorSystem;
import akka.dispatch.Futures;
import akka.dispatch.Mapper;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;

class MapFutures {

    private final LoggingAdapter log;
    private final ExecutionContext ec;
    private final Business business;

    MapFutures(ActorSystem system) {
        log = Logging.getLogger(system, getClass());
        ec = system.dispatcher();
        business = new Business(log);
    }

    void runExample() {
        log.info("*** Init");

        final Future<String> f1 = Futures.future(business::action1, ec);

        final Future<String> f2 = f1.map(new Mapper<String, String>() {
            @Override
            public String apply(String parameter) {
                return parameter + business.action2();
            }
        }, ec);

        final Future<String> f3 = f2.map(new Mapper<String, String>() {
            @Override
            public String apply(String parameter) {
                return parameter + business.action3();
            }
        }, ec);

        f3.onSuccess(new PrintResult<>(log),ec);

        log.info("*** End");
    }
}
