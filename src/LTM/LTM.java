/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package LTM;

/**
 *
 * @author Acer
 */
import java.util.LinkedList;
import java.util.Queue;

class Buffer {
    private Queue<Integer> queue = new LinkedList<>();
    private final int LIMIT = 5; // Giới hạn kích thước của buffer

    // Method cho Producer
    public synchronized void produce(int value) throws InterruptedException {
        while (queue.size() == LIMIT) {
            wait(); // Chờ nếu buffer đã đầy
        }
        queue.add(value);
        System.out.println("Produced: " + value);
        notify(); // Báo cho Consumer rằng có item mới
    }

    // Method cho Consumer
    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) {
            wait(); // Chờ nếu buffer rỗng
        }
        int value = queue.poll();
        System.out.println("Consumed: " + value);
        notify(); // Báo cho Producer rằng đã tiêu thụ item
        return value;
    }
}

class Producer implements Runnable {
    private Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            int i = 0;
            while (true) {
                buffer.produce(i++);
                Thread.sleep(500); // Giả sử việc sản xuất mất thời gian
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumer implements Runnable {
    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                buffer.consume();
                Thread.sleep(1000); // Giả sử việc tiêu thụ mất thời gian
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class LTM {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        Thread producerThread = new Thread(new Producer(buffer));
        Thread consumerThread = new Thread(new Consumer(buffer));

        producerThread.start();
        consumerThread.start();
    }
}

