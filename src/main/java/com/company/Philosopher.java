package com.company;

public class Philosopher implements Runnable {

    private final Object leftFork;
    private final Object rightFork;
    private String action;


    public Philosopher(Object leftFork, Object rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    private void doAction(String action) throws InterruptedException {
        this.action = action;
        Thread.sleep(((int) (Math.random() * 2000)));
    }

    @Override
    public void run() {
        try {
            while (true) {
                doAction(": Myśli");
                synchronized (leftFork) {
                    doAction(": Podniósł lewy widelec");
                    synchronized (rightFork) {
                        doAction(": Podniósł lewy widelec - je");
                        doAction(": Odłożył prawy widelec");
                    }
                    doAction(": Odłożył lewy widelec. Myśli");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }

    public String getAction() {
        return action;
    }
}