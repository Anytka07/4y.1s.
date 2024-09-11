
package com.example.Lab2;
// Основний клас, де запускається вся програма
public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Приклад роботи з класом Counter, де методи не синхронізовані
        System.out.println("Приклад без синхронізації:");
        Counter counter = new Counter();

        // Створюємо два потоки, які одночасно викликають increment
        Thread thread1 = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    counter.increment();
                    System.out.println("Thread 1 increment: " + counter.value());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    counter.increment();
                    System.out.println("Thread 2 increment: " + counter.value());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Запускаємо обидва потоки одночасно
        thread1.start();
        thread2.start();

        // Очікуємо завершення обох потоків
        thread1.join();
        thread2.join();

        // Аналізуйте результати, особливо чи є некоректні значення через гонку потоків.

        System.out.println("\nПриклад з decrement без синхронізації:");

        // Тепер запускаємо decrement в обох потоках
        thread1 = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    counter.decrement();
                    System.out.println("Thread 1 decrement: " + counter.value());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread2 = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    counter.decrement();
                    System.out.println("Thread 2 decrement: " + counter.value());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Запускаємо обидва потоки одночасно
        thread1.start();
        thread2.start();

        // Очікуємо завершення обох потоків
        thread1.join();
        thread2.join();

        // Аналізуйте результати, щоб побачити проблему гонки потоків.

        System.out.println("\nПриклад із синхронізацією:");

        // Тепер працюємо з класом, де методи синхронізовані
        CounterSync counterSync = new CounterSync();

        thread1 = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    counterSync.increment();
                    System.out.println("Thread 1 (sync) increment: " + counterSync.value());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread2 = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    counterSync.increment();
                    System.out.println("Thread 2 (sync) increment: " + counterSync.value());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Запускаємо обидва потоки одночасно
        thread1.start();
        thread2.start();

        // Очікуємо завершення обох потоків
        thread1.join();
        thread2.join();

        // Далі аналогічно запускаємо decrement
        System.out.println("\nПриклад із синхронізацією та decrement:");

        thread1 = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    counterSync.decrement();
                    System.out.println("Thread 1 (sync) decrement: " + counterSync.value());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread2 = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    counterSync.decrement();
                    System.out.println("Thread 2 (sync) decrement: " + counterSync.value());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Запускаємо обидва потоки одночасно
        thread1.start();
        thread2.start();

        // Очікуємо завершення обох потоків
        thread1.join();
        thread2.join();

        // Аналізуйте результати: синхронізація усуває проблему гонки потоків.

        System.out.println("\nПриклад із двома незалежними лічильниками:");

        // Приклад роботи з класом, де є два незалежні лічильники з власними блокуваннями
        DualCounter dualCounter = new DualCounter();

        Thread thread3 = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    dualCounter.incrementCounter1();
                    System.out.println("Thread 3 incrementCounter1: " + dualCounter.valueCounter1());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread4 = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    dualCounter.incrementCounter2();
                    System.out.println("Thread 4 incrementCounter2: " + dualCounter.valueCounter2());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Запускаємо обидва потоки одночасно
        thread3.start();
        thread4.start();

        // Очікуємо завершення обох потоків
        thread3.join();
        thread4.join();
    }
}

// Клас Counter без синхронізації
class Counter {
    private int c = 0;

    public void increment() throws InterruptedException {
        int a;
        Thread.sleep(150); // Імітація затримки
        a = c;
        a++;
        c = a;
    }

    public void decrement() throws InterruptedException {
        int a;
        Thread.sleep(100); // Імітація затримки
        a = c;
        a--;
        c = a;
    }

    public int value() {
        return c;
    }
}

// Клас Counter із синхронізованими методами
class CounterSync {
    private int c = 0;

    public synchronized void increment() throws InterruptedException {
        int a;
        Thread.sleep(150); // Імітація затримки
        a = c;
        a++;
        c = a;
    }

    public synchronized void decrement() throws InterruptedException {
        int a;
        Thread.sleep(100); // Імітація затримки
        a = c;
        a--;
        c = a;
    }

    public synchronized int value() {
        return c;
    }
}

// Клас з двома незалежними лічильниками, де кожен має власне блокування
class DualCounter {
    private int counter1 = 0;
    private int counter2 = 0;

    // Об'єкти для блокування окремих лічильників
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void incrementCounter1() throws InterruptedException {
        synchronized (lock1) {
            int a;
            Thread.sleep(150); // Імітація затримки
            a = counter1;
            a++;
            counter1 = a;
        }
    }

    public void decrementCounter1() throws InterruptedException {
        synchronized (lock1) {
            int a;
            Thread.sleep(100); // Імітація затримки
            a = counter1;
            a--;
            counter1 = a;
        }
    }

    public int valueCounter1() {
        synchronized (lock1) {
            return counter1;
        }
    }

    public void incrementCounter2() throws InterruptedException {
        synchronized (lock2) {
            int a;
            Thread.sleep(150); // Імітація затримки
            a = counter2;
            a++;
            counter2 = a;
        }
    }

    public void decrementCounter2() throws InterruptedException {
        synchronized (lock2) {
            int a;
            Thread.sleep(100); // Імітація затримки
            a = counter2;
            a--;
            counter2 = a;
        }
    }

    public int valueCounter2() {
        synchronized (lock2) {
            return counter2;
        }
    }
}
