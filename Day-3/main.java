import java.util.*;

public class ArrayProblems {

    // 1. Two Sum using HashSet
    public static boolean twoSum(int[] arr, int target) {
        HashSet<Integer> set = new HashSet<>();

        for (int num : arr) {
            int complement = target - num;

            if (set.contains(complement)) {
                System.out.println("Pair Found: " + complement + " " + num);
                return true;
            }

            set.add(num);
        }

        return false;
    }

    // 2. Remove Duplicates from Sorted Array
    public static int removeDuplicates(int[] arr) {
        if (arr.length == 0)
            return 0;

        int i = 0;

        for (int j = 1; j < arr.length; j++) {
            if (arr[j] != arr[i]) {
                i++;
                arr[i] = arr[j];
            }
        }

        return i + 1;
    }

    // 3. Move Zeroes to End
    public static void moveZeroes(int[] arr) {
        int index = 0;

        for (int num : arr) {
            if (num != 0) {
                arr[index++] = num;
            }
        }

        while (index < arr.length) {
            arr[index++] = 0;
        }
    }

    // 4. Negative First, Positive Next (Two Pointer)
    public static void negativeFirst(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            while (left < right && arr[left] < 0)
                left++;

            while (left < right && arr[right] >= 0)
                right--;

            if (left < right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }
    }

    // 5. Positive First, Negative Next (Two Pointer)
    public static void positiveFirst(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            while (left < right && arr[left] >= 0)
                left++;

            while (left < right && arr[right] < 0)
                right--;

            if (left < right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }
    }

    // 6. Segregate 0s and 1s (Naive Method)
    public static void segregateZeroOne(int[] arr) {
        int countZero = 0;

        for (int num : arr) {
            if (num == 0)
                countZero++;
        }

        for (int i = 0; i < countZero; i++) {
            arr[i] = 0;
        }

        for (int i = countZero; i < arr.length; i++) {
            arr[i] = 1;
        }
    }

    // 7. Segregate 0s, 1s and 2s (Naive Method)
    public static void segregateZeroOneTwo(int[] arr) {
        int count0 = 0, count1 = 0, count2 = 0;

        for (int num : arr) {
            switch (num) {
                case 0 -> count0++;
                case 1 -> count1++;
                default -> count2++;
            }
        }

        int index = 0;

        while (count0-- > 0)
            arr[index++] = 0;

        while (count1-- > 0)
            arr[index++] = 1;

        while (count2-- > 0)
            arr[index++] = 2;
    }

    // Utility Function
    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {

        // Two Sum
        int[] arr1 = {2, 7, 11, 15};
        int target = 9;
        System.out.println("Two Sum Exists: " + twoSum(arr1, target));

        // Remove Duplicates
        int[] arr2 = {1, 1, 2, 2, 3, 4, 4};
        int newLength = removeDuplicates(arr2);
        System.out.print("After Removing Duplicates: ");
        for (int i = 0; i < newLength; i++) {
            System.out.print(arr2[i] + " ");
        }
        System.out.println();

        // Move Zeroes
        int[] arr3 = {0, 1, 0, 3, 12};
        moveZeroes(arr3);
        System.out.print("Move Zeroes: ");
        printArray(arr3);

        // Negative First
        int[] arr4 = {-1, 2, -3, 4, -5, 6};
        negativeFirst(arr4);
        System.out.print("Negative First: ");
        printArray(arr4);

        // Positive First
        int[] arr5 = {-1, 2, -3, 4, -5, 6};
        positiveFirst(arr5);
        System.out.print("Positive First: ");
        printArray(arr5);

        // Segregate 0s and 1s
        int[] arr6 = {0, 1, 1, 0, 1, 0, 0, 1};
        segregateZeroOne(arr6);
        System.out.print("Segregate 0s and 1s: ");
        printArray(arr6);

        // Segregate 0s, 1s and 2s
        int[] arr7 = {2, 0, 1, 2, 1, 0, 1, 2};
        segregateZeroOneTwo(arr7);
        System.out.print("Segregate 0s,1s,2s: ");
        printArray(arr7);
    }
}