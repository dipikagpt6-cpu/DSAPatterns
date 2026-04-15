package com.practice.SlowAndFastPointer;

public class LinkedListCycleDetectionAndReturnStartOfCycle {

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
        head.next.next.next.next.next = head.next.next;

        System.out.println("Has Cycle:" + hasCycle(head));
        System.out.println("Start of Cycle: " + getStartOfCycle(head));
    }

    private static Integer getStartOfCycle(LinkedListNode head) {

        LinkedListNode slow = head;
        LinkedListNode fast = head;
        boolean cycle = false;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                cycle = true;
                break;
            }
        }
        if(cycle){
            slow = head;
            while(slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }
            return slow.val;
        }
        return null;
    }

    private static Boolean hasCycle(LinkedListNode head) {

        LinkedListNode slow = head;
        LinkedListNode fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return true;
            }
        }
        return false;

    }


}
