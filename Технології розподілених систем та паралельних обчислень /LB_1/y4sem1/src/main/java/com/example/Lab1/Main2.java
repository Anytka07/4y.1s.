package com.example.Lab1;

import java.util.Random;

public class Main2 {
    static Thread mAnotherOpinion;

    public static void main(String[] args) {
        // Запуск таймера для відліку загального часу
        long startTime = System.currentTimeMillis();

        // Створення і запуск потоку для "яйця"
        mAnotherOpinion = new Thread(new EggVoice2());
        System.out.println("Початок суперечки з використанням класу Runnable");
        mAnotherOpinion.start();

        // Головний потік виводить "курка!" з рандомною кількістю повторів
        Random random = new Random();
        int chickenRepeats = random.nextInt(5) + 1; // Випадкове число від 1 до 5
        for (int i = 0; i < chickenRepeats; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println("курка каже: ко-ко-ко!");
        }

        // Перевірка, чи досі працює додатковий потік EggVoice2
        if (mAnotherOpinion.isAlive()) {
            try {
                mAnotherOpinion.join();
            } catch (InterruptedException e) {
            }
            System.out.println("Першим було яйце!");
        } else {
            System.out.println("Першою була курка!");
        }

        // Завершення роботи програми і виведення загального часу
        long endTime = System.currentTimeMillis();
        long totalTime = (endTime - startTime) / 1000;
        System.out.println("Завершення суперечки! Загальний час: " + totalTime + " секунд.");
    }
}

// Клас EggVoice2 реалізує інтерфейс Runnable
class EggVoice2 implements Runnable {
    @Override
    public void run() {
        // Цикл з рандомною кількістю повторів для "яйця"
        Random random = new Random();
        int eggRepeats = random.nextInt(5) + 1; // Випадкове число від 1 до 5
        for (int i = 0; i < eggRepeats; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println("яйце каже: кряк!");
        }
    }
}
