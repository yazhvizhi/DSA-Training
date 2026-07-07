package day12;

public class LinkedList {

    // -------------------- 1) Remove Nth Node from End --------------------
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n <= 0) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy;
        ListNode slow = dummy;

        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;
        return dummy.next;
    }

    public static void printList(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val);
            if (temp.next != null) {
                System.out.print(" -> ");
            }
            temp = temp.next;
        }
        System.out.println();
    }

    // -------------------- 2) Flatten a Linked List --------------------
    public static class MultiLevelNode {
        int val;
        MultiLevelNode next;
        MultiLevelNode child;

        MultiLevelNode(int val) {
            this.val = val;
        }
    }

    public static MultiLevelNode flattenLinkedList(MultiLevelNode head) {
        MultiLevelNode current = head;

        while (current != null) {
            if (current.child != null) {
                MultiLevelNode tail = current.child;
                while (tail.next != null) {
                    tail = tail.next;
                }

                tail.next = current.next;
                current.next = current.child;
                current.child = null;
            }
            current = current.next;
        }

        return head;
    }

    public static void printMultiLevelList(MultiLevelNode head) {
        MultiLevelNode temp = head;
        while (temp != null) {
            System.out.print(temp.val);
            if (temp.next != null) {
                System.out.print(" -> ");
            }
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("Removing nth node from end:");
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);

        ListNode result = removeNthFromEnd(head1, 2);
        printList(result);

        System.out.println("\nFlattening multi-level linked list:");
        MultiLevelNode node1 = new MultiLevelNode(1);
        MultiLevelNode node2 = new MultiLevelNode(2);
        MultiLevelNode node3 = new MultiLevelNode(3);
        MultiLevelNode node4 = new MultiLevelNode(4);
        MultiLevelNode node5 = new MultiLevelNode(5);
        MultiLevelNode node6 = new MultiLevelNode(6);
        MultiLevelNode node7 = new MultiLevelNode(7);
        MultiLevelNode node8 = new MultiLevelNode(8);

        node1.next = node2;
        node2.next = node3;
        node1.child = node4;
        node4.next = node5;
        node2.child = node6;
        node3.child = node7;
        node7.next = node8;

        MultiLevelNode flatHead = flattenLinkedList(node1);
        printMultiLevelList(flatHead);
    }
}