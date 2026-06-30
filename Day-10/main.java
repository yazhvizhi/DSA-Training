public class Main {
	// Floor in a sorted array: largest element <= target
	public static int floorInSortedArray(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;
		int floorIndex = -1;

		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] == target) {
				return mid;
			}
			if (nums[mid] < target) {
				floorIndex = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return floorIndex;
	}

	// Search in an almost sorted array: element may be at i, i-1, or i+1
	public static int searchAlmostSorted(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;

		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] == target) {
				return mid;
			}
			if (mid - 1 >= left && nums[mid - 1] == target) {
				return mid - 1;
			}
			if (mid + 1 <= right && nums[mid + 1] == target) {
				return mid + 1;
			}
			if (nums[mid] > target) {
				right = mid - 2;
			} else {
				left = mid + 2;
			}
		}
		return -1;
	}

	// Find a peak element in an array (an element greater than its neighbors)
	public static int findPeakElement(int[] nums) {
		int left = 0;
		int right = nums.length - 1;

		while (left < right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] > nums[mid + 1]) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	// Koko eating bananas: minimum speed to finish within h hours
	public static int minEatingSpeed(int[] piles, int h) {
		int left = 1;
		int right = 0;
		for (int pile : piles) {
			right = Math.max(right, pile);
		}

		while (left < right) {
			int mid = left + (right - left) / 2;
			if (canEatAll(piles, h, mid)) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	private static boolean canEatAll(int[] piles, int h, int speed) {
		long hours = 0;
		for (int pile : piles) {
			hours += (pile + speed - 1) / speed;
			if (hours > h) {
				return false;
			}
		}
		return hours <= h;
	}

	public static void main(String[] args) {
		System.out.println("Floor in sorted array index: " + floorInSortedArray(new int[]{1, 2, 4, 6, 8}, 5));
		System.out.println("Search in almost sorted array: " + searchAlmostSorted(new int[]{10, 3, 40, 20, 50, 80, 70}, 40));
		System.out.println("Find peak element index: " + findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4}));
		System.out.println("Koko eating bananas speed: " + minEatingSpeed(new int[]{3, 6, 7, 11}, 8));
	}
    
}