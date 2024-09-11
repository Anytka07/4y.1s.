package com.example.Lab1;

public class Main {
    // Статична змінна для об'єкта потоку EggVoice
    static EggVoice mAnotherOpinion;

    public static void main(String[] args) {
        // Ініціалізація об'єкта EggVoice та запуск потоку
        mAnotherOpinion = new EggVoice();
        System.out.println("Початок суперечки з використанням класу Thread");
        mAnotherOpinion.start(); // Запускаємо новий потік, який починає виконувати метод run() у класі EggVoice

        // Головний потік виводить "курка!" 5 разів із затримкою в 1 секунду
        for (int i = 0; i < 5; i++) {
            try {
                // Затримка в 1 секунду між виведенням "курка!"
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // Обробка переривання (в даному випадку порожня)
            }
            System.out.println("курка!");
        }

        // Перевірка, чи досі працює додатковий потік EggVoice
        if (mAnotherOpinion.isAlive()) {
            try {
                // Якщо потік ще живий, головний потік чекає його завершення
                mAnotherOpinion.join();
            } catch (InterruptedException e) {
                // Обробка переривання (в даному випадку порожня)
            }
            System.out.println("Першим було яйце!");
        } else {
            // Якщо потік вже завершився, виводиться повідомлення про те, що першою була курка
            System.out.println("Першою була курка!");
        }

        // Завершення роботи програми
        System.out.println("Завершення суперечки!");
    }
}

// Клас EggVoice, що успадковує Thread і реалізує окремий потік
class EggVoice extends Thread {
    @Override
    public void run() {
        // Додатковий потік виводить "яйце!" 5 разів із затримкою в 1 секунду
        for (int i = 0; i < 5; i++) {
            try {
                // Затримка в 1 секунду між виведенням "яйце!"
                sleep(1000);
            } catch (InterruptedException e) {
                // Обробка переривання (в даному випадку порожня)
            }
            System.out.println("яйце!");
        }
    }
}
