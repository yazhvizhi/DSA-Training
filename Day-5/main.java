package day05;

import java.util.*;

class PrefixSuffixProblems {

    // 1. Prefix Sum
    public static int[] prefixSum(int[] arr) {
        int n = arr.length;
        int[] prefix = new int[n];

        prefix[0] = arr[0];

        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + arr[i];
        }

        return prefix;
    }

    // 2. Range Sum Query using Prefix Sum
    public static int rangeSum(int[] prefix, int left, int right) {
        if (left == 0) {
            return prefix[right];
        }
        return prefix[right] - prefix[left - 1];
    }

    // 3. Suffix Sum
    public static int[] suffixSum(int[] arr) {
        int n = arr.length;
        int[] suffix = new int[n];

        suffix[n - 1] = arr[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + arr[i];
        }

        return suffix;
    }

    // 4. Equilibrium Point Index
    public static int equilibriumIndex(int[] arr) {
        int totalSum = 0;

        for (int num : arr) {
            totalSum += num;
        }

        int leftSum = 0;

        for (int i = 0; i < arr.length; i++) {

            totalSum -= arr[i]; // right sum

            if (leftSum == totalSum) {
                return i;
            }

            leftSum += arr[i];
        }

        return -1;
    }

    // 5. Pivot Index (LeetCode 724)
    public static int pivotIndex(int[] nums) {
        int totalSum = 0;

        for (int num : nums) {
            totalSum += num;
        }

        int leftSum = 0;

        for (int i = 0; i < nums.length; i++) {

            int rightSum = totalSum - leftSum - nums[i];

            if (leftSum == rightSum) {
                return i;
            }

            leftSum += nums[i];
        }

        return -1;
    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5};

        // Prefix Sum
        int[] prefix = prefixSum(arr);
        System.out.println("Prefix Sum:");
        System.out.println(Arrays.toString(prefix));

        // Range Sum Query
        int left = 1;
        int right = 3;

        System.out.println("\nRange Sum (" + left + "," + right + "): "
                + rangeSum(prefix, left, right));

        // Suffix Sum
        int[] suffix = suffixSum(arr);
        System.out.println("\nSuffix Sum:");
        System.out.println(Arrays.toString(suffix));

        // Equilibrium Index Example
        int[] eqArr = {-7, 1, 5, 2, -4, 3, 0};

        System.out.println("\nEquilibrium Index: "
                + equilibriumIndex(eqArr));

        // Pivot Index Example
        int[] pivotArr = {1, 7, 3, 6, 5, 6};

        System.out.println("Pivot Index: "
                + pivotIndex(pivotArr));
    }
}