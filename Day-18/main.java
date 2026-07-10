package day18;

import java.util.Arrays;

public class Main {
    public static int[] runningSum(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        return nums;
    }

    public static boolean isPalindrome(String s) {
        return isPalindromeHelper(s, 0, s.length() - 1);
    }

    private static boolean isPalindromeHelper(String s, int left, int right) {
        if (left >= right) {
            return true;
        }
        if (s.charAt(left) != s.charAt(right)) {
            return false;
        }
        return isPalindromeHelper(s, left + 1, right - 1);
    }

    public static long towerOfHanoi(int n, int from, int to, int aux) {
        if (n == 1) {
            return 1;
        }
        long left = towerOfHanoi(n - 1, from, aux, to);
        long right = towerOfHanoi(n - 1, aux, to, from);
        return left + right + 1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println("Running Sum: " + Arrays.toString(runningSum(nums)));

        String word = "madam";
        System.out.println("Is palindrome: " + isPalindrome(word));

        int n = 4;
        System.out.println("Tower of Hanoi moves for n=" + n + ": " + towerOfHanoi(n, 1, 3, 2));
    }
}