package com.practice.queue;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueueSynchronized {

    private Queue<Integer> queue;
    private int capacity;

    BlockingQueueSynchronized(int capacity){
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }

    public synchronized void enqueue(int item) throws InterruptedException {

        while(queue.size() == capacity){
            wait();
        }
        queue.offer(item);
        notifyAll();
    }

    public synchronized int dequeue() throws InterruptedException {
        while(queue.isEmpty()){
            wait();
        }
        int item = queue.poll();
        notifyAll();
        return item;
    }

    static void main() {
        BlockingQueueSynchronized queue =
                new BlockingQueueSynchronized(3);

        Thread producer = new Thread(() -> {
            try {

                for (int i = 1; i <= 10; i++) {

                    queue.enqueue(i);

                    System.out.println(
                            "Produced : " + i);

                    Thread.sleep(500);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread consumer = new Thread(() -> {
            try {

                while (true) {

                    int val = queue.dequeue();

                    System.out.println(
                            "Consumed : " + val);

                    Thread.sleep(1000);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();
    }


}
