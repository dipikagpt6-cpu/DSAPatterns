package com.practice.SlowAndFastPointer;

public class MiddleOfLinkedList {

    static class LinkedListNode{
        int val;
        LinkedListNode next;
        LinkedListNode(int val){
            this.val = val;
        }
    }

    static void main() {
        LinkedListNode head = new LinkedListNode(1);
        head.next = new LinkedListNode(2);
        head.next.next  = new LinkedListNode(3);
        head.next.next.next = new LinkedListNode(4);
        head.next.next.next.next = new LinkedListNode(5);
        head.next.next.next.next.next = new LinkedListNode(6);
        head.next.next.next.next.next.next = new LinkedListNode(7);
        System.out.println("Middle of List: " + getMiddleOfList(head));
    }

    private static Integer getMiddleOfList(LinkedListNode head) {

        if(head == null)
            return null;
        LinkedListNode slow = head;
        LinkedListNode fast = head;
        //even list
        // while(fast != null && fast.next.next != null){
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            }
        return slow.val;
        }

}
