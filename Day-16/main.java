package day16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    // 1) Subsets with duplicates
    public static class SubsetsWithDupSolution {
        public void backtrack(int[] nums, int start, List<Integer> path, List<List<Integer>> result) {
            result.add(new ArrayList<>(path));
            for (int i = start; i < nums.length; i++) {
                if (i > start && nums[i] == nums[i - 1]) continue;
                path.add(nums[i]);
                backtrack(nums, i + 1, path, result);
                path.remove(path.size() - 1);
            }
        }

        public List<List<Integer>> subsetsWithDup(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            Arrays.sort(nums);
            backtrack(nums, 0, path, result);
            return result;
        }
    }
 
    // 2) Combination Sum
    public static class CombinationSumSolution {
        public void backtrack(int[] candidates, int target, List<List<Integer>> result, List<Integer> curr, int index) {
            if (target == 0) {
                result.add(new ArrayList<>(curr));
                return;
            }
            for (int i = index; i < candidates.length; i++) {
                if (candidates[i] <= target) {
                    curr.add(candidates[i]);
                    backtrack(candidates, target - candidates[i], result, curr, i);
                    curr.remove(curr.size() - 1);
                }
            }
        }

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> curr = new ArrayList<>();
            Arrays.sort(candidates);
            backtrack(candidates, target, result, curr, 0);
            return result;
        }
    }

    private static void printResult(List<List<Integer>> result) {
        System.out.print("[");
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));
            if (i + 1 < result.size()) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        SubsetsWithDupSolution subsetsSolver = new SubsetsWithDupSolution();
        int[] nums = {1, 2, 2};
        List<List<Integer>> subsets = subsetsSolver.subsetsWithDup(nums);
        System.out.print("subsetsWithDup([1, 2, 2]) -> ");
        printResult(subsets);

        CombinationSumSolution combinationSolver = new CombinationSumSolution();
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> combinations = combinationSolver.combinationSum(candidates, target);
        System.out.print("combinationSum([2, 3, 6, 7], 7) -> ");
        printResult(combinations);
    }
}