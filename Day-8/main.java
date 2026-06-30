package Day08;

import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    // 1) Container With Most Water (optimal two pointers)
    public static int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int width = right - left;
            int currentHeight = Math.min(height[left], height[right]);
            maxArea = Math.max(maxArea, currentHeight * width);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    // 2) Maximum subarray of size k (fixed-size sliding window)
    public static int maxSumSubarrayOfSizeK(int[] nums, int k) {
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (i >= k) {
                sum -= nums[i - k];
            }
            if (i >= k - 1) {
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }

    // 3) Maximum average subarray of size k (sliding window)
    public static double maxAverageSubarray(int[] nums, int k) {
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (i >= k) {
                sum -= nums[i - k];
            }
            if (i >= k - 1) {
                maxSum = Math.max(maxSum, sum);
            }
        }
        return (double) maxSum / k;
    }

    // 4) Maximum number of vowels in a substring of given length (sliding window)
    public static int maxVowels(String s, int k) {
        int count = 0;
        int maxCount = 0;

        for (int i = 0; i < s.length(); i++) {
            if (isVowel(s.charAt(i))) {
                count++;
            }
            if (i >= k) {
                if (isVowel(s.charAt(i - k))) {
                    count--;
                }
            }
            if (i >= k - 1) {
                maxCount = Math.max(maxCount, count);
            }
        }
        return maxCount;
    }

    private static boolean isVowel(char c) {
        c = Character.toLowerCase(c);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    // 5) Number of subarrays of size k with average >= threshold (sliding window)
    public static int numOfSubarraysWithAvgAtLeastThreshold(int[] nums, int k, int threshold) {
        int sum = 0;
        int count = 0;
        int required = k * threshold;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (i >= k) {
                sum -= nums[i - k];
            }
            if (i >= k - 1 && sum >= required) {
                count++;
            }
        }
        return count;
    }

    // 6) Minimum recolors to get k consecutive black blocks (sliding window)
    public static int minimumRecolors(String blocks, int k) {
        int whiteCount = 0;
        int minRecolors = Integer.MAX_VALUE;

        for (int i = 0; i < blocks.length(); i++) {
            if (blocks.charAt(i) == 'W') {
                whiteCount++;
            }
            if (i >= k) {
                if (blocks.charAt(i - k) == 'W') {
                    whiteCount--;
                }
            }
            if (i >= k - 1) {
                minRecolors = Math.min(minRecolors, whiteCount);
            }
        }
        return minRecolors == Integer.MAX_VALUE ? 0 : minRecolors;
    }

    // 7) First negative in every window of size k (sliding window + deque)
    public static int[] firstNegativeInWindow(int[] nums, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        int n = nums.length;
        int[] result = new int[Math.max(0, n - k + 1)];
        int ri = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] < 0) {
                deque.addLast(i);
            }
            if (i >= k && !deque.isEmpty() && deque.peekFirst() == i - k) {
                deque.removeFirst();
            }
            if (i >= k - 1) {
                result[ri++] = deque.isEmpty() ? 0 : nums[deque.peekFirst()];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Container With Most Water: " + maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        System.out.println("Maximum sum subarray of size k: " + maxSumSubarrayOfSizeK(new int[]{2, 1, 5, 1, 3, 2}, 3));
        System.out.println("Maximum average subarray of size k: " + maxAverageSubarray(new int[]{1, 12, -5, -6, 50, 3}, 4));
        System.out.println("Maximum vowels in substring of length k: " + maxVowels("azerdii", 5));
        System.out.println("Number of subarrays size k with avg >= threshold: " + numOfSubarraysWithAvgAtLeastThreshold(new int[]{2, 2, 2, 2, 5, 5, 5, 8}, 3, 4));
        System.out.println("Minimum recolors to get k consecutive: " + minimumRecolors("WBBWWBBWBW", 7));
        int[] firstNeg = firstNegativeInWindow(new int[]{12, -1, -7, 8, -15, 30, 16, 28}, 3);
        System.out.print("First negative in every window: ");
        for (int x : firstNeg) {
            System.out.print(x + " ");
        }
        System.out.println();

        System.out.println("\nSliding window should be used for:");
        System.out.println("- Maximum subarray of size k");
        System.out.println("- Maximum average subarray of size k");
        System.out.println("- Maximum number of vowels in a substring of given length");
        System.out.println("- Number of subarrays of size k and average >= threshold");
        System.out.println("- Minimum recolors to get k consecutive blocks");
        System.out.println("- First negative in every window of size k");
        System.out.println("Container With Most Water uses two pointers, not fixed-window sliding window.");
    }
}