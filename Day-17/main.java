package day17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    // 1) Combination Sum II
    public static class CombinationSum2Solution {
        public void backtrack(int[] candidates, int target, int start, List<Integer> curr, List<List<Integer>> result) {
            if (target == 0) {
                result.add(new ArrayList<>(curr));
                return;
            }
            for (int i = start; i < candidates.length; i++) {
                if (i > start && candidates[i] == candidates[i - 1]) continue;
                if (candidates[i] > target) break;
                curr.add(candidates[i]);
                backtrack(candidates, target - candidates[i], i + 1, curr, result);
                curr.remove(curr.size() - 1);
            }
        }

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            Arrays.sort(candidates);
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> current = new ArrayList<>();
            backtrack(candidates, target, 0, current, result);
            return result;
        }
    }

    // 2) Permutations of a string
    public static class PermutationStringSolution {
        public void backtrack(char[] chars, int index, List<String> ans) {
            if (index == chars.length) {
                ans.add(new String(chars));
                return;
            }
            for (int i = index; i < chars.length; i++) {
                swap(chars, index, i);
                backtrack(chars, index + 1, ans);
                swap(chars, index, i);
            }
        }

        private void swap(char[] chars, int a, int b) {
            char temp = chars[a];
            chars[a] = chars[b];
            chars[b] = temp;
        }

        public List<String> permutation(String s) {
            List<String> ans = new ArrayList<>();
            backtrack(s.toCharArray(), 0, ans);
            Collections.sort(ans);
            return ans;
        }
    }

    // 3) Permutations of an array
    public static class PermuteSolution {
        public void backtrack(int[] nums, int start, List<List<Integer>> result) {
            if (start == nums.length) {
                List<Integer> permutation = new ArrayList<>();
                for (int num : nums) {
                    permutation.add(num);
                }
                result.add(permutation);
                return;
            }
            for (int i = start; i < nums.length; ++i) {
                swap(nums, start, i);
                backtrack(nums, start + 1, result);
                swap(nums, start, i);
            }
        }

        private void swap(int[] nums, int a, int b) {
            int temp = nums[a];
            nums[a] = nums[b];
            nums[b] = temp;
        }

        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            backtrack(nums, 0, result);
            return result;
        }
    }
 
    // 4) Permutations of an array with duplicates
    public static class PermuteUniqueSolution {
        public void backtrack(int[] nums, List<List<Integer>> result, List<Integer> current, boolean[] used) {
            if (current.size() == nums.length) {
                result.add(new ArrayList<>(current));
                return;
            }
            for (int i = 0; i < nums.length; ++i) {
                if (used[i]) continue;
                if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
                used[i] = true;
                current.add(nums[i]);
                backtrack(nums, result, current, used);
                used[i] = false;
                current.remove(current.size() - 1);
            }
        }

        public List<List<Integer>> permuteUnique(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> current = new ArrayList<>();
            boolean[] used = new boolean[nums.length];
            backtrack(nums, result, current, used);
            return result;
        }
    }

    // 5) Letter Combinations of a Phone Number
    public static class LetterCombinationsSolution {
        public void backtrack(String digits, String[] map, int index, StringBuilder curr, List<String> ans) {
            if (index == digits.length()) {
                ans.add(curr.toString());
                return;
            }
            String letters = map[digits.charAt(index) - '0'];
            for (char ch : letters.toCharArray()) {
                curr.append(ch);
                backtrack(digits, map, index + 1, curr, ans);
                curr.deleteCharAt(curr.length() - 1);
            }
        }

        public List<String> letterCombinations(String digits) {
            if (digits.isEmpty()) return new ArrayList<>();
            String[] map = {
                "", "", "abc", "def", "ghi",
                "jkl", "mno", "pqrs", "tuv", "wxyz"
            };
            List<String> ans = new ArrayList<>();
            backtrack(digits, map, 0, new StringBuilder(), ans);
            return ans;
        }
    }

    private static <T> void printListList(List<List<T>> list) {
        System.out.print("[");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i + 1 < list.size()) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    private static void printStringList(List<String> list) {
        System.out.print("[");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i + 1 < list.size()) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        CombinationSum2Solution sum2 = new CombinationSum2Solution();
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        System.out.print("combinationSum2 -> ");
        printListList(sum2.combinationSum2(candidates, 8));

        PermutationStringSolution stringPerm = new PermutationStringSolution();
        System.out.print("permutation -> ");
        printStringList(stringPerm.permutation("abc"));

        PermuteSolution permute = new PermuteSolution();
        int[] nums1 = {1, 2, 3};
        System.out.print("permute -> ");
        printListList(permute.permute(nums1));

        PermuteUniqueSolution permuteUnique = new PermuteUniqueSolution();
        int[] nums2 = {1, 1, 2};
        System.out.print("permuteUnique -> ");
        printListList(permuteUnique.permuteUnique(nums2));

        LetterCombinationsSolution letterComb = new LetterCombinationsSolution();
        System.out.print("letterCombinations -> ");
        printStringList(letterComb.letterCombinations("23"));
    }
}