package Day02;

import java.util.*;

public class Main {

    // 1. Calculate Mid
    static int calculateMid(int low, int high) {
        return low + (high - low) / 2;
    }

    // 2. Binary Search using Mid
    static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = calculateMid(low, high);

            if (arr[mid] == target)
                return mid;
            else if (arr[mid] < target)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return -1;
    }

    // 3. Print N Natural Numbers using Recursion
    static void printNatural(int n) {
        if (n == 0)
            return;

        printNatural(n - 1);
        System.out.print(n + " ");
    }

    // 4. Sum of First N Natural Numbers using Recursion
    static int sumNatural(int n) {
        if (n == 0)
            return 0;

        return n + sumNatural(n - 1);
    }

    // 5. Nth Fibonacci using Optimized Recursion (Memoization)
    static int fibonacci(int n, int[] dp) {
        if (n <= 1)
            return n;

        if (dp[n] != -1)
            return dp[n];

        return dp[n] = fibonacci(n - 1, dp) + fibonacci(n - 2, dp);
    }

    // 6. Array of Squares
    static int[] squareArray(int[] arr) {
        int[] result = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i] * arr[i];
        }

        return result;
    }

    // 7. Count Factors
    static int countFactors(int n) {
        int count = 0;

        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                if (i == n / i)
                    count++;
                else
                    count += 2;
            }
        }

        return count;
    }

    // 8. Prime Check
    static boolean isPrime(int n) {
        if (n < 2)
            return false;

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0)
                return false;
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Mid Calculation
        System.out.println("Mid of 10 and 20: " + calculateMid(10, 20));

        // Binary Search
        int[] sortedArr = {1, 3, 5, 7, 9, 11, 13, 15};
        int target = 9;

        int index = binarySearch(sortedArr, target);

        System.out.println("Target Found At Index: " + index);

        // Print Natural Numbers
        System.out.print("Natural Numbers: ");
        printNatural(10);
        System.out.println();

        // Sum of Natural Numbers
        System.out.println("Sum of First 10 Natural Numbers: "
                + sumNatural(10));

        // Fibonacci
        int n = 10;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        System.out.println("10th Fibonacci Number: "
                + fibonacci(n, dp));

        // Square Array
        int[] arr = {1, 2, 3, 4, 5};

        int[] squared = squareArray(arr);

        System.out.println("Squared Array: "
                + Arrays.toString(squared));

        // Factors and Prime
        System.out.print("Enter a number: ");
        int num = sc.nextInt();

        System.out.println("Number of Factors: "
                + countFactors(num));

        if (isPrime(num))
            System.out.println(num + " is Prime");
        else
            System.out.println(num + " is Not Prime");

        sc.close();
    }
}