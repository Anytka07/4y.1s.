package com.example.Lab_1_1;

import java.util.Random;

public class Main {
    static Thread sunThread;
    static Thread moonThread;
    static StarsThread starsThread;

    // Змінні для підрахунку кількості повторів кожного потоку
    static int sunCount = 0;
    static int moonCount = 0;
    static int starsCount = 0;

    public static void main(String[] args) {
        // Запуск таймера для відліку загального часу
        long startTime = System.currentTimeMillis();

        // Створення і запуск потоків для сонця, місяця та зірок
        sunThread = new Thread(new SunVoice());
        moonThread = new Thread(new MoonVoice());
        starsThread = new StarsThread(); // Потік на основі спадкування класу Thread

        System.out.println("Початок суперечки між сонцем, місяцем та зорями!");

        // Запуск усіх трьох потоків
        sunThread.start();
        moonThread.start();
        starsThread.start();

        // Чекаємо завершення всіх потоків
        try {
            sunThread.join();
            moonThread.join();
            starsThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Визначаємо, хто світить найяскравіше
        if (sunCount > moonCount && sunCount > starsCount) {
            System.out.println("Переможець суперечки: Сонце! Воно світить найяскравіше!");
        } else if (moonCount > sunCount && moonCount > starsCount) {
            System.out.println("Переможець суперечки: Місяць! Він світить найяскравіше!");
        } else if (starsCount > sunCount && starsCount > moonCount) {
            System.out.println("Переможець суперечки: Зорі! Вони світять найяскравіше!");
        } else {
            System.out.println("Немає однозначного переможця! Усі світять однаково яскраво.");
        }

        // Завершення роботи програми і виведення загального часу
        long endTime = System.currentTimeMillis();
        long totalTime = (endTime - startTime) / 1000;
        System.out.println("Завершення суперечки! Загальний час: " + totalTime + " секунд.");
    }

    // Клас SunVoice реалізує інтерфейс Runnable і представляє потік для "сонця"
    static class SunVoice implements Runnable {
        @Override
        public void run() {
            Random random = new Random();
            int sunRepeats = random.nextInt(5) + 1; // Випадкове число від 1 до 5
            for (int i = 0; i < sunRepeats; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Сонце каже: Я світлю найяскравіше!");
                sunCount++;
            }
        }
    }

    // Клас MoonVoice реалізує інтерфейс Runnable і представляє потік для "місяця"
    static class MoonVoice implements Runnable {
        @Override
        public void run() {
            Random random = new Random();
            int moonRepeats = random.nextInt(5) + 1; // Випадкове число від 1 до 5
            for (int i = 0; i < moonRepeats; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Місяць каже: Я найспокійніший світильник у нічному небі!");
                moonCount++;
            }
        }
    }

    // Клас StarsThread успадковує Thread і представляє потік для "зірок"
    static class StarsThread extends Thread {
        @Override
        public void run() {
            Random random = new Random();
            int starsRepeats = random.nextInt(5) + 1; // Випадкове число від 1 до 5
            for (int i = 0; i < starsRepeats; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Зорі кажуть: Ми сяємо яскраво і виблискуємо у всьому небі!");
                starsCount++;
            }
        }
    }
}
