package day22;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class Main {
    
    // 1. Top View of Binary Tree
    static class Solution1 {
        List<Integer> topView(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            if (root == null)
                return result;
            
            Map<Integer, Integer> topNodeMap = new HashMap<>();
            Queue<Pair<TreeNode, Integer>> q = new LinkedList<>();
            q.add(new Pair<>(root, 0));
            
            while (!q.isEmpty()) {
                Pair<TreeNode, Integer> temp = q.poll();
                TreeNode curr = temp.getKey();
                int hd = temp.getValue();
                
                if (!topNodeMap.containsKey(hd))
                    topNodeMap.put(hd, curr.val);
                
                if (curr.left != null)
                    q.add(new Pair<>(curr.left, hd - 1));
                if (curr.right != null)
                    q.add(new Pair<>(curr.right, hd + 1));
            }
            
            List<Integer> keys = new ArrayList<>(topNodeMap.keySet());
            Collections.sort(keys);
            for (int hd : keys)
                result.add(topNodeMap.get(hd));
            
            return result;
        }
    }
    
    // 2. Bottom View of Binary Tree
    static class Solution2 {
        List<Integer> bottomView(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null)
                return res;
            
            Map<Integer, Integer> bottomNodes = new HashMap<>();
            Queue<Pair<TreeNode, Integer>> q = new LinkedList<>();
            q.add(new Pair<>(root, 0));
            
            while (!q.isEmpty()) {
                Pair<TreeNode, Integer> curr = q.poll();
                TreeNode temp = curr.getKey();
                int hd = curr.getValue();
                
                bottomNodes.put(hd, temp.val);
                
                if (temp.left != null)
                    q.add(new Pair<>(temp.left, hd - 1));
                if (temp.right != null)
                    q.add(new Pair<>(temp.right, hd + 1));
            }
            
            List<Integer> keys = new ArrayList<>(bottomNodes.keySet());
            Collections.sort(keys);
            for (int hd : keys)
                res.add(bottomNodes.get(hd));
            
            return res;
        }
    }
    
    // 3. Left View of Binary Tree
    static class Solution3 {
        void helper(TreeNode root, int level, List<Integer> res) {
            if (root == null)
                return;
            
            if (res.size() == level) {
                res.add(root.val);
            }
            
            helper(root.left, level + 1, res);
            helper(root.right, level + 1, res);
        }
        
        List<Integer> leftView(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            helper(root, 0, res);
            return res;
        }
    }
    
    // 4. Right View of Binary Tree
    static class Solution4 {
        List<Integer> rightView(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            if (root == null)
                return result;
            
            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);
            
            while (!q.isEmpty()) {
                int levelSize = q.size();
                for (int i = 0; i < levelSize; i++) {
                    TreeNode currentNode = q.poll();
                    if (i == levelSize - 1) {
                        result.add(currentNode.val);
                    }
                    if (currentNode.left != null)
                        q.add(currentNode.left);
                    if (currentNode.right != null)
                        q.add(currentNode.right);
                }
            }
            
            return result;
        }
    }
    
    // 5. Insert into a Binary Search Tree
    static class Solution5 {
        TreeNode insertIntoBST(TreeNode root, int val) {
            if (root == null)
                return new TreeNode(val);
            
            if (val > root.val)
                root.right = insertIntoBST(root.right, val);
            else
                root.left = insertIntoBST(root.left, val);
            
            return root;
        }
    }
    
    // 6. Search in a Binary Search Tree
    static class Solution6 {
        TreeNode searchBST(TreeNode root, int val) {
            if (root == null)
                return null;
            
            if (root.val == val)
                return root;
            else if (val < root.val)
                return searchBST(root.left, val);
            else
                return searchBST(root.right, val);
        }
    }
    
    // 7. Delete Node in a Binary Search Tree
    static class Solution7 {
        TreeNode minValueNode(TreeNode node) {
            TreeNode curr = node;
            while (curr != null && curr.left != null) {
                curr = curr.left;
            }
            return curr;
        }
        
        TreeNode deleteNode(TreeNode root, int key) {
            if (root == null)
                return null;
            
            if (key < root.val)
                root.left = deleteNode(root.left, key);
            else if (key > root.val)
                root.right = deleteNode(root.right, key);
            else {
                if (root.left == null) {
                    return root.right;
                } else if (root.right == null) {
                    return root.left;
                }
                
                TreeNode temp = minValueNode(root.right);
                root.val = temp.val;
                root.right = deleteNode(root.right, temp.val);
            }
            
            return root;
        }
    }
    
    // 8. Convert Sorted Array to a BST
    static class Solution8 {
        TreeNode sortedArrayToBST(int[] nums) {
            return buildBST(nums, 0, nums.length - 1);
        }
        
        private TreeNode buildBST(int[] nums, int left, int right) {
            if (left > right)
                return null;
            
            int mid = left + (right - left) / 2;
            TreeNode root = new TreeNode(nums[mid]);
            root.left = buildBST(nums, left, mid - 1);
            root.right = buildBST(nums, mid + 1, right);
            
            return root;
        }
    }
    
    // Helper class to replace std::pair
    static class Pair<K, V> {
        private K key;
        private V value;
        
        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
        
        K getKey() {
            return key;
        }
        
        V getValue() {
            return value;
        }
    }
    
    public static void main(String[] args) {
        // Create a sample BST
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        
        // Test Solution 1: Top View
        System.out.println("1. Top View of Binary Tree:");
        Solution1 sol1 = new Solution1();
        System.out.println(sol1.topView(root));
        
        // Test Solution 2: Bottom View
        System.out.println("\n2. Bottom View of Binary Tree:");
        Solution2 sol2 = new Solution2();
        System.out.println(sol2.bottomView(root));
        
        // Test Solution 3: Left View
        System.out.println("\n3. Left View of Binary Tree:");
        Solution3 sol3 = new Solution3();
        System.out.println(sol3.leftView(root));
        
        // Test Solution 4: Right View
        System.out.println("\n4. Right View of Binary Tree:");
        Solution4 sol4 = new Solution4();
        System.out.println(sol4.rightView(root));
        
        // Test Solution 5: Insert into BST
        System.out.println("\n5. Insert into BST:");
        Solution5 sol5 = new Solution5();
        TreeNode bst = new TreeNode(4);
        bst = sol5.insertIntoBST(bst, 2);
        bst = sol5.insertIntoBST(bst, 6);
        bst = sol5.insertIntoBST(bst, 1);
        bst = sol5.insertIntoBST(bst, 3);
        System.out.println("BST created with insertions");
        
        // Test Solution 6: Search in BST
        System.out.println("\n6. Search in BST:");
        Solution6 sol6 = new Solution6();
        TreeNode found = sol6.searchBST(root, 3);
        System.out.println("Search for 3: " + (found != null ? "Found" : "Not Found"));
        found = sol6.searchBST(root, 10);
        System.out.println("Search for 10: " + (found != null ? "Found" : "Not Found"));
        
        // Test Solution 7: Delete Node
        System.out.println("\n7. Delete Node in BST:");
        Solution7 sol7 = new Solution7();
        root = sol7.deleteNode(root, 2);
        System.out.println("Deleted node 2");
        
        // Test Solution 8: Convert Sorted Array to BST
        System.out.println("\n8. Convert Sorted Array to BST:");
        Solution8 sol8 = new Solution8();
        int[] sortedArray = {-10, -3, 0, 5, 9};
        TreeNode bstFromArray = sol8.sortedArrayToBST(sortedArray);
        System.out.println("BST created from sorted array");
    }
}