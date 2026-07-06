package day11;

import java.util.Scanner;

public class LinkedList {
    private Node head;

    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }

        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    public void takeInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter values to create linked list (-1 to stop):");

        while (true) {
            int value = sc.nextInt();
            if (value == -1) {
                break;
            }
            insertAtEnd(value);
        }
    }

    public void printList() {
        Node temp = head;
        if (temp == null) {
            System.out.println("Linked list is empty.");
            return;
        }

        System.out.print("Linked List: ");
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public int length() {
        int count = 0;
        Node temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    public void printNthNode(int n) {
        if (n <= 0) {
            System.out.println("Invalid position.");
            return;
        }

        Node temp = head;
        int count = 1;
        while (temp != null && count < n) {
            temp = temp.next;
            count++;
        }

        if (temp == null) {
            System.out.println("Position out of range.");
        } else {
            System.out.println("Node at position " + n + " is: " + temp.data);
        }
    }

    public void printKthNodeFromEnd(int k) {
        if (k <= 0) {
            System.out.println("Invalid value of k.");
            return;
        }

        Node fast = head;
        Node slow = head;

        for (int i = 0; i < k; i++) {
            if (fast == null) {
                System.out.println("k is greater than list length.");
                return;
            }
            fast = fast.next;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        System.out.println("Kth node from end is: " + slow.data);
    }

    public void printMiddleNode() {
        if (head == null) {
            System.out.println("Linked list is empty.");
            return;
        }

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        System.out.println("Middle node is: " + slow.data);
    }

    public void insertAtIth(int index, int data) {
        if (index < 0) {
            System.out.println("Invalid index.");
            return;
        }

        Node newNode = new Node(data);

        if (index == 0) {
            newNode.next = head;
            head = newNode;
            return;
        }

        Node temp = head;
        int count = 0;
        while (temp != null && count < index - 1) {
            temp = temp.next;
            count++;
        }

        if (temp == null) {
            System.out.println("Index out of range.");
            return;
        }

        newNode.next = temp.next;
        temp.next = newNode;
    }

    public void deleteAtIth(int index) {
        if (head == null) {
            System.out.println("Linked list is empty.");
            return;
        }

        if (index < 0) {
            System.out.println("Invalid index.");
            return;
        }

        if (index == 0) {
            head = head.next;
            System.out.println("Deleted node at index 0.");
            return;
        }

        Node temp = head;
        int count = 0;
        while (temp != null && count < index - 1) {
            temp = temp.next;
            count++;
        }

        if (temp == null || temp.next == null) {
            System.out.println("Index out of range.");
            return;
        }

        temp.next = temp.next.next;
        System.out.println("Deleted node at index " + index + ".");
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.takeInput();

        list.printList();
        System.out.println("Length of linked list: " + list.length());

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter position to print nth node: ");
        int n = sc.nextInt();
        list.printNthNode(n);

        System.out.print("Enter k for kth node from end: ");
        int k = sc.nextInt();
        list.printKthNodeFromEnd(k);

        list.printMiddleNode();

        System.out.print("Enter index to insert: ");
        int insertIndex = sc.nextInt();
        System.out.print("Enter value to insert: ");
        int insertValue = sc.nextInt();
        list.insertAtIth(insertIndex, insertValue);
        list.printList();

        System.out.print("Enter index to delete: ");
        int deleteIndex = sc.nextInt();
        list.deleteAtIth(deleteIndex);
        list.printList();

        sc.close();
    }
}