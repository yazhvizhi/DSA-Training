import java.util.*;

public class PrefixSumProblems {

    // =====================================================
    // 1. 2D PREFIX SUM (NumMatrix)
    // =====================================================

    static class NumMatrix {

        int[][] prefix;

        public NumMatrix(int[][] matrix) {

            int rows = matrix.length;

            if (rows == 0)
                return;

            int cols = matrix[0].length;

            prefix = new int[rows + 1][cols + 1];

            for (int i = 0; i < rows; i++) {

                for (int j = 0; j < cols; j++) {

                    prefix[i + 1][j + 1] =
                            matrix[i][j]
                                    + prefix[i][j + 1]
                                    + prefix[i + 1][j]
                                    - prefix[i][j];
                }
            }
        }

        public int sumRegion(int row1, int col1,
                             int row2, int col2) {

            return prefix[row2 + 1][col2 + 1]
                    - prefix[row1][col2 + 1]
                    - prefix[row2 + 1][col1]
                    + prefix[row1][col1];
        }
    }

    // =====================================================
    // 2. SUBARRAY SUM EQUALS K
    // =====================================================

    public static int subarraySumEqualsK(int[] nums, int k) {

        HashMap<Integer, Integer> prefixCount = new HashMap<>();

        prefixCount.put(0, 1);

        int currentSum = 0;
        int count = 0;

        for (int num : nums) {

            currentSum += num;

            if (prefixCount.containsKey(currentSum - k)) {
                count += prefixCount.get(currentSum - k);
            }

            prefixCount.put(
                    currentSum,
                    prefixCount.getOrDefault(currentSum, 0) + 1
            );
        }

        return count;
    }

    // =====================================================
    // 3. LONGEST SUBARRAY WITH SUM K
    // =====================================================

    public static int longestSubarrayWithSumK(int[] nums, int k) {

        HashMap<Integer, Integer> firstOccurrence = new HashMap<>();

        int prefixSum = 0;
        int maxLen = 0;

        for (int i = 0; i < nums.length; i++) {

            prefixSum += nums[i];

            if (prefixSum == k) {
                maxLen = i + 1;
            }

            if (firstOccurrence.containsKey(prefixSum - k)) {

                maxLen = Math.max(
                        maxLen,
                        i - firstOccurrence.get(prefixSum - k)
                );
            }

            firstOccurrence.putIfAbsent(prefixSum, i);
        }

        return maxLen;
    }

    // =====================================================
    // MAIN METHOD
    // =====================================================

    public static void main(String[] args) {

        // -----------------------------
        // 1. 2D Prefix Sum
        // -----------------------------

        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };

        NumMatrix obj = new NumMatrix(matrix);

        System.out.println("2D Range Sum Query:");
        System.out.println(
                obj.sumRegion(2, 1, 4, 3)
        );

        // -----------------------------
        // 2. Subarray Sum Equals K
        // -----------------------------

        int[] arr1 = {1, 1, 1};
        int k1 = 2;

        System.out.println("\nSubarray Sum Equals K:");
        System.out.println(
                subarraySumEqualsK(arr1, k1)
        );

        // -----------------------------
        // 3. Longest Subarray Sum K
        // -----------------------------

        int[] arr2 = {10, 5, 2, 7, 1, 9};
        int k2 = 15;

        System.out.println("\nLongest Subarray With Sum K:");
        System.out.println(
                longestSubarrayWithSumK(arr2, k2)
        );
    }
}