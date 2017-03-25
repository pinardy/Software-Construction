package Cohort_Exercise_1;

import java.util.Random;

public class BufferFixed {
    public static void main(String[] args) throws Exception {
        Buffer buffer = new Buffer(10);
        MyProducer prod = new MyProducer(buffer);
        prod.start();
        MyConsumer cons = new MyConsumer(buffer);
        cons.start();
        while (true) ;
    }
}

class MyProducer extends Thread {
    private Buffer buffer;

    public MyProducer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        while (true) {
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            buffer.addItem(new Object());
        }
    }
}

class MyConsumer extends Thread {
    private Buffer buffer;

    public MyConsumer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        while (true) {

            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            buffer.removeItem();
        }
    }
}

class Buffer {
    public int SIZE;
    private Object[] objects;
    private int count = 0;

    public Buffer(int size) {
        SIZE = size;
        objects = new Object[SIZE];
    }

    public synchronized void addItem(Object object) {
        while (count >= SIZE) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (count < SIZE) { // we cannot add an item to a full buffer
            objects[count] = object;
            count++;
            notify(); // wake up thread
            System.out.println("Added an object!");
            System.out.println("Count: " + count);
        }
    }

    public synchronized Object removeItem() {
        // if there is no item in the buffer, we wait until there is an item
        while (count == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // if there is an item in the buffer...
        count--;
        notify(); // wake up thread
        System.out.println("Removed an object!");
        System.out.println("Count: " + count);
        return objects[count];
    }
}