package com.practice.LRU;

import java.util.HashMap;
import java.util.Map;

public class MyLRUCache<K,V> {

    private final int capacity;
    private final Map<K,Node<K,V>> cache;
    private final DoublyLinkedList<K,V> dll;

    public MyLRUCache(int capacity) {
        if(capacity<=0){
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        this.cache= new HashMap<>();
        this.dll = new DoublyLinkedList<>();

    }

    public V get(K key){
        Node<K,V> node = cache.get(key);
        if(node == null){
            return null;
        }
        dll.moveToFront(node);
        return node.value;
    }

    public void put(K key, V value){
        Node<K,V> node = cache.get(key);
        if(node!=null){
            node.value = value;
            dll.moveToFront(node);
            return;
        }
        if(cache.size() >= capacity){
            Node<K,V> last = dll.removeLast();
            if(last !=null){
                cache.remove((last.key));
            }
        }
        Node<K,V> newNode =  new Node<>(key, value);
        dll.addFirst(newNode);
        cache.put(key, newNode);

    }


    private static class Node<K,V>{
        Node<K,V> next;
        Node<K,V> previous;
        K key;
        V value;
        Node(K key, V value){
           this.key = key;
           this.value = value;
        }

    }
    private static class DoublyLinkedList<K,V>{

        Node<K,V> head;
        Node<K,V> tail;

        DoublyLinkedList(){
            this.head = new Node<K,V>(null, null);
            this.tail = new Node<K,V>(null,null);
            head.next = tail;
            tail.previous = head;

        }

        public void moveToFront(Node<K,V> node){
           remove(node);
           addFirst(node);
        }

        private void remove(Node<K, V> node) {
            node.previous.next = node.next;
            node.next.previous = node.previous;
        }

        public Node<K,V> removeLast() {
            if(tail.previous == head){
                return null;
            }
            Node<K,V> last = tail.previous;
            remove(last);
            return last;
        }

        public void addFirst(Node<K, V> node) {

            node.next = head.next;
            node.previous = head;
            head.next.previous = node;
            head.next = node;
        }
    }

}
/*
Use Segmented locking for thread safe and O(1) operartion
Sharding for distributed system -> Consistent hashing
At scale, I would shard the cache using consistent hashing, where each node maintains its own local LRU. Global LRU not feasible for distributed env.
” User1 to node 1

Segmented Eviction policy.

 */