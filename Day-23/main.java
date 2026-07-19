package day23;

import java.util.*;

// Max Heap Implementation
class MaxHeap {
    private PriorityQueue<Integer> pq;
    
    public MaxHeap() {
        // Collections.reverseOrder() creates a max heap in Java
        pq = new PriorityQueue<>(Collections.reverseOrder());
    }
    
    public void push(int x) {
        pq.offer(x);
    }
    
    public void pop() {
        if (!pq.isEmpty()) {
            pq.poll();
        }
    }
    
    public int peek() {
        if (pq.isEmpty()) {
            return -1;
        }
        return pq.peek();
    }
    
    public int size() {
        return pq.size();
    }
    
    public boolean isEmpty() {
        return pq.isEmpty();
    }
}

// Min Heap Implementation (Manual)
class MinHeap {
    private List<Integer> heap;
    
    public MinHeap() {
        heap = new ArrayList<>();
    }
    
    public void push(int x) {
        // Insert x into the heap
        heap.add(x);
        int curr = heap.size() - 1;
        
        // Heapify Up (Bubble Up)
        while (curr > 0) {
            int parent = (curr - 1) / 2;
            if (heap.get(curr) < heap.get(parent)) {
                // Swap
                int temp = heap.get(curr);
                heap.set(curr, heap.get(parent));
                heap.set(parent, temp);
                curr = parent;
            } else {
                break;
            }
        }
    }
    
    public void pop() {
        // Remove the top (minimum) element
        if (heap.isEmpty())
            return;
        
        // Replace root with the last element and remove last
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        
        if (heap.isEmpty())
            return;
        
        // Heapify Down (Sift Down)
        int curr = 0;
        int n = heap.size();
        while (true) {
            int smallest = curr;
            int left = 2 * curr + 1;
            int right = 2 * curr + 2;
            
            if (left < n && heap.get(left) < heap.get(smallest))
                smallest = left;
            if (right < n && heap.get(right) < heap.get(smallest))
                smallest = right;
            
            if (smallest != curr) {
                // Swap
                int temp = heap.get(curr);
                heap.set(curr, heap.get(smallest));
                heap.set(smallest, temp);
                curr = smallest;
            } else {
                break;
            }
        }
    }
    
    public int peek() {
        // Return the top element or -1 if empty
        if (heap.isEmpty())
            return -1;
        return heap.get(0);
    }
    
    public int size() {
        // Return the number of elements in the heap
        return heap.size();
    }
    
    public boolean isEmpty() {
        return heap.isEmpty();
    }
    
    public void printHeap() {
        System.out.println("Heap: " + heap);
    }
}

public class Main {
    
    public static void main(String[] args) {
        // Test Max Heap
        System.out.println("===== Max Heap Implementation =====");
        MaxHeap maxHeap = new MaxHeap();
        
        System.out.println("Inserting: 10, 20, 15, 30, 5");
        maxHeap.push(10);
        maxHeap.push(20);
        maxHeap.push(15);
        maxHeap.push(30);
        maxHeap.push(5);
        
        System.out.println("Size: " + maxHeap.size());
        System.out.println("Peek (max element): " + maxHeap.peek());
        
        System.out.println("\nRemoving elements in order:");
        while (!maxHeap.isEmpty()) {
            System.out.println("Top: " + maxHeap.peek());
            maxHeap.pop();
        }
        
        System.out.println("Size after all removals: " + maxHeap.size());
        System.out.println("Peek on empty heap: " + maxHeap.peek());
        
        // Test Min Heap
        System.out.println("\n\n===== Min Heap Implementation =====");
        MinHeap minHeap = new MinHeap();
        
        System.out.println("Inserting: 10, 20, 15, 30, 5");
        minHeap.push(10);
        minHeap.push(20);
        minHeap.push(15);
        minHeap.push(30);
        minHeap.push(5);
        
        minHeap.printHeap();
        System.out.println("Size: " + minHeap.size());
        System.out.println("Peek (min element): " + minHeap.peek());
        
        System.out.println("\nRemoving elements in order:");
        while (!minHeap.isEmpty()) {
            System.out.println("Top: " + minHeap.peek());
            minHeap.pop();
            if (!minHeap.isEmpty())
                minHeap.printHeap();
        }
        
        System.out.println("Size after all removals: " + minHeap.size());
        System.out.println("Peek on empty heap: " + minHeap.peek());
        
        // Additional test for Min Heap
        System.out.println("\n\n===== Additional Min Heap Test =====");
        MinHeap minHeap2 = new MinHeap();
        int[] arr = {50, 30, 20, 15, 10, 8, 16};
        
        System.out.println("Inserting: " + Arrays.toString(arr));
        for (int num : arr) {
            minHeap2.push(num);
        }
        
        minHeap2.printHeap();
        System.out.println("Min element: " + minHeap2.peek());
    }
}