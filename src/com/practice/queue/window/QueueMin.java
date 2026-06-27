package com.practice.queue.window;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/*
Design a queue with operations enqueue, dequeue, and getMax
Enqueue and deque can be done through queue. but for get max, instead of O(n),
use dequeue.
 */
public class QueueMin {

    private final Queue<Integer> queue;
    private final Deque<Integer> dq;

    public QueueMin() {
        this.queue = new LinkedList<>();
        this.dq = new LinkedList<>();
    }

    public void enqueue(int val){
       queue.offer(val);
       while(!dq.isEmpty() && dq.peekLast() > val){
           dq.pollLast();
       }
       dq.offerLast(val);
    }

    public int deque(){
        if (queue.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        int removed = queue.poll();
        if(removed == dq.peekFirst()){
            dq.pollFirst();
        }
        return removed;
    }

    public int getMin(){
        if(dq.isEmpty()){
            throw new RuntimeException("Queue is empty");
        }
        return dq.peekFirst();
    }
    public void printState() {
        System.out.println("Queue    : " + queue);
        System.out.println("MaxDeque : " + dq);
        System.out.println("Min      : " + getMin());
        System.out.println("--------------------------------");
    }

    public static void main(String[] args) {

        QueueMin mq = new QueueMin();

        System.out.println("Enqueue 3");
        mq.enqueue(3);
        mq.printState();

        System.out.println("Enqueue 1");
        mq.enqueue(1);
        mq.printState();

        System.out.println("Enqueue 5");
        mq.enqueue(5);
        mq.printState();

        System.out.println("Enqueue 2");
        mq.enqueue(2);
        mq.printState();

        System.out.println("Dequeue = " + mq.deque());
        mq.printState();

        System.out.println("Dequeue = " + mq.deque());
        mq.printState();

        System.out.println("Dequeue = " + mq.deque());
        mq.printState();
    }

}
