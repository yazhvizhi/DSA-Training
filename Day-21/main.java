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
    
    // 1. Root to Leaf Paths
    static class Solution1 {
        void solve(TreeNode root, List<Integer> curr, List<List<Integer>> allPaths) {
            if (root == null)
                return;
            curr.add(root.val);
            if (root.left == null && root.right == null) {
                allPaths.add(new ArrayList<>(curr));
            } else {
                solve(root.left, curr, allPaths);
                solve(root.right, curr, allPaths);
            }
            curr.remove(curr.size() - 1);
        }
        
        List<List<Integer>> paths(TreeNode root) {
            List<List<Integer>> allPaths = new ArrayList<>();
            List<Integer> curr = new ArrayList<>();
            solve(root, curr, allPaths);
            return allPaths;
        }
    }
    
    // 2. Diameter of Binary Tree
    static class Solution2 {
        int maxDiameter = 0;
        
        int calculateHeight(TreeNode node) {
            if (node == null)
                return 0;
            int leftHeight = calculateHeight(node.left);
            int rightHeight = calculateHeight(node.right);
            maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight);
            return 1 + Math.max(leftHeight, rightHeight);
        }
        
        int diameterOfBinaryTree(TreeNode root) {
            maxDiameter = 0;
            calculateHeight(root);
            return maxDiameter;
        }
    }
    
    // 3. Binary Tree Level Order Traversal
    static class Solution3 {
        List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            if (root == null)
                return result;
            
            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);
            
            while (!q.isEmpty()) {
                int levelSize = q.size();
                List<Integer> curr = new ArrayList<>();
                
                for (int i = 0; i < levelSize; i++) {
                    TreeNode node = q.poll();
                    curr.add(node.val);
                    
                    if (node.left != null)
                        q.add(node.left);
                    if (node.right != null)
                        q.add(node.right);
                }
                result.add(curr);
            }
            
            return result;
        }
    }
    
    // 4. Vertical Order Traversal of a Binary Tree
    static class Solution4 {
        void dfs(TreeNode root, int row, int col, 
                Map<Integer, Map<Integer, TreeSet<Integer>>> nodes) {
            if (root == null)
                return;
            
            nodes.putIfAbsent(col, new HashMap<>());
            nodes.get(col).putIfAbsent(row, new TreeSet<>());
            nodes.get(col).get(row).add(root.val);
            
            dfs(root.left, row + 1, col - 1, nodes);
            dfs(root.right, row + 1, col + 1, nodes);
        }
        
        List<List<Integer>> verticalTraversal(TreeNode root) {
            Map<Integer, Map<Integer, TreeSet<Integer>>> nodes = new TreeMap<>();
            dfs(root, 0, 0, nodes);
            
            List<List<Integer>> result = new ArrayList<>();
            for (Map<Integer, TreeSet<Integer>> rows : nodes.values()) {
                List<Integer> col_nodes = new ArrayList<>();
                for (TreeSet<Integer> values : rows.values()) {
                    col_nodes.addAll(values);
                }
                result.add(col_nodes);
            }
            
            return result;
        }
    }
    
    public static void main(String[] args) {
        // Create a sample tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        
        // Test Solution 1: Root to Leaf Paths
        System.out.println("1. Root to Leaf Paths:");
        Solution1 sol1 = new Solution1();
        List<List<Integer>> paths = sol1.paths(root);
        System.out.println(paths);
        
        // Test Solution 2: Diameter of Binary Tree
        System.out.println("\n2. Diameter of Binary Tree:");
        Solution2 sol2 = new Solution2();
        int diameter = sol2.diameterOfBinaryTree(root);
        System.out.println("Diameter: " + diameter);
        
        // Test Solution 3: Level Order Traversal
        System.out.println("\n3. Level Order Traversal:");
        Solution3 sol3 = new Solution3();
        List<List<Integer>> levels = sol3.levelOrder(root);
        System.out.println(levels);
        
        // Test Solution 4: Vertical Order Traversal
        System.out.println("\n4. Vertical Order Traversal:");
        Solution4 sol4 = new Solution4();
        List<List<Integer>> vertical = sol4.verticalTraversal(root);
        System.out.println(vertical);
    }
}