import java.util.HashMap;
import java.util.Map;

public class Main {
    // 1) Range Sum Query 2D (Prefix Sum Matrix)
    static class NumMatrix {
        private final int[][] prefix;

        public NumMatrix(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                prefix = new int[0][0];
                return;
            }

            int rows = matrix.length;
            int cols = matrix[0].length;
            prefix = new int[rows + 1][cols + 1];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    prefix[i + 1][j + 1] = matrix[i][j]
                            + prefix[i][j + 1]
                            + prefix[i + 1][j]
                            - prefix[i][j];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            if (prefix.length == 0 || prefix[0].length == 0) {
                return 0;
            }
            return prefix[row2 + 1][col2 + 1]
                    - prefix[row1][col2 + 1]
                    - prefix[row2 + 1][col1]
                    + prefix[row1][col1];
        }
    }

    // 2) Subarray Sum Equals K (LeetCode 560)
    static int subarraySumEqualsK(int[] nums, int k) {
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1);

        int prefixSum = 0;
        int count = 0;

        for (int num : nums) {
            prefixSum += num;
            int needed = prefixSum - k;
            count += prefixCount.getOrDefault(needed, 0);
            prefixCount.put(prefixSum, prefixCount.getOrDefault(prefixSum, 0) + 1);
        }
        return count;
    }

    // 3) Longest Subarray With Sum K
    static int longestSubarrayWithSumK(int[] nums, int k) {
        Map<Integer, Integer> firstSeen = new HashMap<>();
        firstSeen.put(0, -1);

        int prefixSum = 0;
        int longest = 0;

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            int needed = prefixSum - k;
            if (firstSeen.containsKey(needed)) {
                longest = Math.max(longest, i - firstSeen.get(needed));
            }
            if (!firstSeen.containsKey(prefixSum)) {
                firstSeen.put(prefixSum, i);
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        System.out.println("=== Range Sum Query 2D ===");
        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 3, 2}
        };
        NumMatrix numMatrix = new NumMatrix(matrix);
        System.out.println(numMatrix.sumRegion(1, 1, 2, 3));
        System.out.println(numMatrix.sumRegion(0, 0, 3, 3));

        System.out.println("\n=== Subarray Sum Equals K ===");
        int[] nums1 = {1, 1, 1};
        System.out.println(subarraySumEqualsK(nums1, 2));

        System.out.println("\n=== Longest Subarray With Sum K ===");
        int[] nums2 = {1, 2, 1, 0, 1, 1, 1};
        System.out.println(longestSubarrayWithSumK(nums2, 3));
    }
}