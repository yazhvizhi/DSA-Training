package Day09;

import java.util.HashMap;
import java.util.Map;

public class Main {

    // Minimum size subarray sum with sum >= target
    public static int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int sum = 0;
        int minLen = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= target) {
                minLen = Math.min(minLen, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    // Maximum consecutive ones III: at most k zeros in the window
    public static int longestOnes(int[] nums, int k) {
        int left = 0;
        int zeros = 0;
        int maxLen = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zeros++;
            }
            while (zeros > k) {
                if (nums[left] == 0) {
                    zeros--;
                }
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    // Longest substring without repeating characters
    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> lastIndex = new HashMap<>();
        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (lastIndex.containsKey(c)) {
                left = Math.max(left, lastIndex.get(c) + 1);
            }
            lastIndex.put(c, right);
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    // Binary search on a sorted array
    public static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    // Order-agnostic binary search (works on ascending or descending sorted arrays)
    public static int orderAgnosticBinarySearch(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        boolean ascending = nums[left] < nums[right];

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (ascending) {
                if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (nums[mid] > target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    // First occurrence of target in sorted array
    public static int firstOccurrence(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                result = mid;
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    // Last occurrence of target in sorted array
    public static int lastOccurrence(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                result = mid;
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    // Number of occurrences of target in sorted array
    public static int countOccurrences(int[] nums, int target) {
        int first = firstOccurrence(nums, target);
        if (first == -1) {
            return 0;
        }
        int last = lastOccurrence(nums, target);
        return last - first + 1;
    }

    // Find the kth rotation of an array (right rotation)
    public static int[] kthRotation(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) {
            return new int[0];
        }
        k %= n;
        if (k < 0) {
            k += n;
        }
        int[] rotated = new int[n];
        for (int i = 0; i < n; i++) {
            rotated[(i + k) % n] = nums[i];
        }
        return rotated;
    }

    // Search in a sorted rotated array
    public static int searchInSortedRotatedArray(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[left] <= nums[mid]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("Minimum size subarray sum: " + minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
        System.out.println("Maximum consecutive ones III: " + longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2));
        System.out.println("Longest substring without repeating characters: " + lengthOfLongestSubstring("pwwkew"));

        System.out.println("\nBinary search: " + binarySearch(new int[]{1, 2, 4, 5, 7, 9}, 5));
        System.out.println("Order-agnostic binary search (desc): " + orderAgnosticBinarySearch(new int[]{9, 7, 5, 4, 2, 1}, 5));
        System.out.println("First occurrence: " + firstOccurrence(new int[]{1, 2, 2, 2, 3, 4}, 2));
        System.out.println("Last occurrence: " + lastOccurrence(new int[]{1, 2, 2, 2, 3, 4}, 2));
        System.out.println("Count occurrences: " + countOccurrences(new int[]{1, 2, 2, 2, 3, 4}, 2));

        int[] rotated = kthRotation(new int[]{1, 2, 3, 4, 5}, 2);
        System.out.print("2nd rotation: ");
        for (int x : rotated) {
            System.out.print(x + " ");
        }
        System.out.println();

        System.out.println("Search in sorted rotated array: " + searchInSortedRotatedArray(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));

        System.out.println("\nSliding window usage:");
        System.out.println("- minSubArrayLen uses a variable-size sliding window");
        System.out.println("- longestOnes uses sliding window with at most k zeros");
        System.out.println("- lengthOfLongestSubstring uses sliding window with a moving start index");
    }
}