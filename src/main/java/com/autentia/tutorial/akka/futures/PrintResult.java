package com.autentia.tutorial.akka.futures;

import akka.dispatch.OnSuccess;
import akka.event.LoggingAdapter;

class PrintResult<T> extends OnSuccess<T> {
    private final LoggingAdapter log;

    PrintResult(LoggingAdapter log) {
        this.log = log;
    }

    @Override
    public void onSuccess(T result) {
        log.info("---> {}", result);
    }
}
