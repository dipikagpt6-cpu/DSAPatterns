package com.practice.LRU;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

    public class LRUCache1<K, V> {

        private final int capacity;
        private final ConcurrentHashMap<K, Node<K, V>> cache;
        private final DoublyLinkedList<K, V> dll;
        private final ReentrantLock lock = new ReentrantLock();

        public LRUCache1(int capacity) {
            this.capacity = capacity;
            this.cache = new ConcurrentHashMap<>();
            this.dll = new DoublyLinkedList<>();
        }

        public V get(K key) {
            Node<K, V> node = cache.get(key);
            if (node == null) return null;

            lock.lock();
            try {
                dll.moveToFront(node); // critical section
            } finally {
                lock.unlock();
            }
            return node.value;
        }

        public void put(K key, V value) {
            lock.lock();
            try {
                Node<K, V> node = cache.get(key);

                if (node != null) {
                    node.value = value;
                    dll.moveToFront(node);
                    return;
                }

                if (cache.size() >= capacity) {
                    Node<K, V> lru = dll.removeLast();
                    if (lru != null) {
                        cache.remove(lru.key);
                    }
                }

                Node<K, V> newNode = new Node<>(key, value);
                dll.addFirst(newNode);
                cache.put(key, newNode);

            } finally {
                lock.unlock();
            }
        }

        // ---------- Node ----------
        private static class Node<K, V> {
            K key;
            V value;
            Node<K, V> prev, next;

            Node(K k, V v) {
                key = k;
                value = v;
            }
        }

        // ---------- DLL ----------
        private static class DoublyLinkedList<K, V> {
            private final Node<K, V> head = new Node<>(null, null);
            private final Node<K, V> tail = new Node<>(null, null);

            DoublyLinkedList() {
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
                if (tail.prev == head) return null;
                Node<K, V> last = tail.prev;
                remove(last);
                return last;
            }
        }
    }
/*
“I would use ConcurrentHashMap for thread-safe access and
a ReentrantLock to protect the doubly linked list.
This ensures consistency between the map and list. For higher scalability,
I would partition the cache into segments, each with its own lock, to reduce contention.”
 */
