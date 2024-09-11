package com.example.Lab2;

public class Main2 {
    static MyThread2 thread1;
    static MyThread2 thread2;

    public static void main(String[] args) throws InterruptedException {
        Counter2 counter1 = new Counter2();

        new MyThread2(counter1).start();
        new MyThread2(counter1).start();

        new MyThread3(counter1).start();
        new MyThread3(counter1).start();
    }
}

class MyThread2 extends Thread {
    Counter2 counter = null;

    public MyThread2(Counter2 param) {
        this.counter = param;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                counter.increment_first();
                System.out.print(counter.value_first() + "c ");
                Thread.sleep(500);
            }
            System.out.print(" start decrement ");
            for (int i = 0; i < 5; i++) {
                counter.decrement_first();
                System.out.print(counter.value_first() + "c ");
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
        }
    }
}

class MyThread3 extends Thread {
    Counter2 counter = null;

    public MyThread3(Counter2 param) {
        this.counter = param;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                counter.increment_second();
                System.out.print(counter.value_second() + "x ");
                Thread.sleep(500);
            }
            System.out.print(" start decrement ");
            for (int i = 0; i < 5; i++) {
                counter.decrement_second();
                System.out.print(counter.value_second() + "x ");
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
        }
    }
}

class Counter2 {
    private int c = 0;
    private int c2 = 0;
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void increment_first() throws InterruptedException {
        synchronized (lock1) {
            Thread.sleep(150);
            c++;
        }
    }

    public void decrement_first() throws InterruptedException {
        synchronized (lock1) {
            Thread.sleep(100);
            c--;
        }
    }

    public int value_first() {
        return c;
    }


    public void increment_second() throws InterruptedException {
        synchronized (lock2) {
            Thread.sleep(150);
            c2++;
        }
    }

    public void decrement_second() throws InterruptedException {
        synchronized (lock2) {
            Thread.sleep(100);
            c2--;
        }
    }

    public int value_second() {
        return c2;
    }
}
