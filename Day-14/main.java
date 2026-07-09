import java.util.Stack;

public class Main {

    // 1) Largest rectangle in histogram
    public static int getMaxArea(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int n = arr.length;
        int maxArea = 0;

        for (int i = 0; i <= n; i++) {
            int current = (i == n) ? 0 : arr[i];
            while (!st.isEmpty() && arr[st.peek()] >= current) {
                int height = arr[st.pop()];
                int width;
                if (st.isEmpty()) {
                    width = i;
                } else {
                    width = i - st.peek() - 1;
                }
                maxArea = Math.max(maxArea, height * width);
            }
            if (i < n) {
                st.push(i);
            }
        }
        return maxArea;
    }

    // 2) Largest rectangle in binary matrix
    public static int largestRectangleArea(int[] h) {
        Stack<Integer> st = new Stack<>();
        int ans = 0;
        int n = h.length;

        for (int i = 0; i <= n; i++) {
            int current = (i == n) ? 0 : h[i];
            while (!st.isEmpty() && h[st.peek()] > current) {
                int height = h[st.pop()];
                int width = st.isEmpty() ? i : i - st.peek() - 1;
                ans = Math.max(ans, height * width);
            }
            if (i < n) {
                st.push(i);
            }
        }
        return ans;
    }

    public static int maxArea(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return 0;
        }

        int n = mat.length;
        int m = mat[0].length;
        int ans = 0;
        int[] h = new int[m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                h[j] = mat[i][j] == 1 ? h[j] + 1 : 0;
            }
            ans = Math.max(ans, largestRectangleArea(h));
        }
        return ans;
    }

    // 3) Trapping rain water
    public static int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int waterTrapped = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    waterTrapped += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    waterTrapped += rightMax - height[right];
                }
                right--;
            }
        }
        return waterTrapped;
    }

    public static void main(String[] args) {
        int[] histogram = {2, 1, 5, 6, 2, 3};
        System.out.println("Largest rectangle in histogram: " + getMaxArea(histogram));

        int[][] matrix = {
                {1, 0, 1, 0, 0},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 0, 0, 1, 0}
        };
        System.out.println("Largest rectangle in binary matrix: " + maxArea(matrix));

        int[] water = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("Trapping rain water: " + trap(water));
    }
}