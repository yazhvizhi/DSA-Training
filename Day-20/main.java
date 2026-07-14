package day20;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    static void solveInOrder(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        solveInOrder(root.left, ans);
        ans.add(root.val);
        solveInOrder(root.right, ans);
    }

    static List<Integer> inOrder(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        solveInOrder(root, ans);
        return ans;
    }

    static void solvePreorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        solvePreorder(root.left, result);
        solvePreorder(root.right, result);
    }

    static List<Integer> preOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        solvePreorder(root, result);
        return result;
    }

    static void traverse(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        traverse(root.left, result);
        traverse(root.right, result);
        result.add(root.val);
    }

    static List<Integer> postOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traverse(root, result);
        return result;
    }

    static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    static int checkHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = checkHeight(root.left);
        if (left == -1) {
            return -1;
        }
        int right = checkHeight(root.right);
        if (right == -1) {
            return -1;
        }
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }

    static boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = 0;
        TreeNode l = root;
        while (l != null) {
            leftHeight++;
            l = l.left;
        }

        int rightHeight = 0;
        TreeNode r = root;
        while (r != null) {
            rightHeight++;
            r = r.right;
        }

        if (leftHeight == rightHeight) {
            return (1 << leftHeight) - 1;
        }

        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        int remain = targetSum - root.val;
        return hasPathSum(root.left, remain) || hasPathSum(root.right, remain);
    }

    static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null || p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println("Inorder: " + inOrder(root));
        System.out.println("Preorder: " + preOrder(root));
        System.out.println("Postorder: " + postOrder(root));
        System.out.println("Max Depth: " + maxDepth(root));
        System.out.println("Balanced: " + isBalanced(root));
        System.out.println("Node Count: " + countNodes(root));
        System.out.println("Path Sum 12: " + hasPathSum(root, 12));

        TreeNode same1 = new TreeNode(1);
        same1.left = new TreeNode(2);
        TreeNode same2 = new TreeNode(1);
        same2.left = new TreeNode(2);
        System.out.println("Same Tree: " + isSameTree(same1, same2));
    }
}