package com.autentia.tutorial.akka.futures;

import akka.event.LoggingAdapter;

class Business {
    private final LoggingAdapter log;

    Business(LoggingAdapter log) {
        this.log = log;
    }

    String action1() {
        log.info("action1 init");
        final String s = "Hola";
        veryHardWork(3);
        log.info("action1 end");
        return s;
    }

    String action2() {
        log.info("action2 init");
        final String s = " mundo";
        veryHardWork(2);
        log.info("action2 end");
        return s;
    }

    String action3() {
        log.info("action3 init");
        final String s = " del futuro!!!";
        veryHardWork(1);
        log.info("action3 end");
        return s;
    }

    private void veryHardWork(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
