package com.company;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Philosopher[] philosophers = new Philosopher[5];
        Object[] forks = new Object[philosophers.length];

        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Object();
        }

        for (int i = 0; i < philosophers.length; i++) {
            Object leftFork = forks[i];
            Object rightFork = forks[(i + 1) % forks.length];

            // przypisanie filozofom odpowiednich widelców
            // (ostatni filozof dostaje zamieniony widelec lewy z prawym, aby
            // uniknąć efektu zagnieżdżenia)
            if (i == philosophers.length - 1) {
                philosophers[i] = new Philosopher(rightFork, leftFork);
            } else {
                philosophers[i] = new Philosopher(leftFork, rightFork);
            }

            Thread t = new Thread(philosophers[i], "Philosopher " + (i + 1));
            t.start();
        }
        clearScreen();

        // wyświetlanie informacji o stanie filozofów co 100 milisekund
        while (true) {
            for (int i = 0; i < 5; i++) {
                System.out.println("Philosopher " + (i + 1) + " " + philosophers[i].getAction());
            }
            TimeUnit.MILLISECONDS.sleep(100);
            clearScreen();
        }

    }

    // funkcja odpowiadająca za czyszczenie konsoli
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
