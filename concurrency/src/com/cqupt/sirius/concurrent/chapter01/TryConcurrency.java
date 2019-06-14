package com.cqupt.sirius.concurrent.chapter01;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class TryConcurrency {
    public static void main(String[] args) {
        //browseNews();
        new Thread() {
            public void run() {
                browseNews();
            }
        }.start();
        enjoyMusic();
    }

    private static void browseNews() {
        for (; ; ) {
            System.out.println("Uh-hub, the good news.");
            sleep(1);
        }
    }

    private static void enjoyMusic() {
        for (; ; ) {
            System.out.println("Uh-hub, the nice music.");
            sleep(1);
        }
    }

    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
