package com.practice.queue.window;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueueReentrantLock {

    private Queue<Integer> queue;
    private int capacity;
    private final ReentrantLock lock = new ReentrantLock();
    //for producer
    private final Condition notFull = lock.newCondition();
   //for Conumer
    private final Condition notEmpty = lock.newCondition();

    BlockingQueueReentrantLock(int capacity){
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }
/*
we use try beacuse if an exception is occured the lok is kept forever.
 */
    public void enqueue(int item) throws InterruptedException {

        lock.lock();
        try{
            //jab tak queue full hai producer wait karega
            while(queue.size() == capacity){
                notFull.await();
            }
            queue.offer(item);
            notEmpty.signal();
        }finally{
            lock.unlock();
        }
    }
//jab tak queue me elemet nai hai comsumer should wait
    public int dequeue() throws InterruptedException {
        lock.lock();
        try{
            while(queue.isEmpty()){
                notEmpty.await();
            }
            int item = queue.poll();
            notFull.signal();
            return item;
        }finally{
            lock.unlock();
        }
    }

    static void main() {
        BlockingQueueReentrantLock queue =
                new BlockingQueueReentrantLock(3);

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
