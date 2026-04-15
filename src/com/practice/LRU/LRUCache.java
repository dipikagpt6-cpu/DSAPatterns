package com.practice.LRU;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {

    private final int capacity;
    private final Map<K, Node<K, V>> cache;

    private final DoublyLinkedList<K, V> dll;

    public LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be > 0");
        }
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.dll = new DoublyLinkedList<>();
    }

    public V get(K key) {
        Node<K, V> node = cache.get(key);
        if (node == null) {
            return null;
        }
        dll.moveToFront(node);
        return node.value;
    }

    public void put(K key, V value) {
        Node<K, V> node = cache.get(key);

        if (node != null) {
            node.value = value;
            dll.moveToFront(node);
            return;
        }

        if (cache.size() == capacity) {
            Node<K, V> lru = dll.removeLast();
            if (lru != null) {
                cache.remove(lru.key);
            }
        }

        Node<K, V> newNode = new Node<>(key, value);
        dll.addFirst(newNode);
        cache.put(key, newNode);
    }

    // ------------------- Node -------------------
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // ------------------- Doubly Linked List -------------------
    private static class DoublyLinkedList<K, V> {
        private final Node<K, V> head;
        private final Node<K, V> tail;

        DoublyLinkedList() {
            head = new Node<>(null, null);
            tail = new Node<>(null, null);
            head.next = tail;
            tail.prev = head;
        }

        void addFirst(Node<K, V> node) {
            node.next = head.next;
            node.prev = head;

            head.next.prev = node;
            head.next = node;
        }

        void remove(Node<K, V> node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        void moveToFront(Node<K, V> node) {
            remove(node);
            addFirst(node);
        }

        Node<K, V> removeLast() {
            if (tail.prev == head) {
                return null;
            }
            Node<K, V> last = tail.prev;
            remove(last);
            return last;
        }
    }
}
