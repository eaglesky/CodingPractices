import java.util.*;

public class Sortings {


	//Insertion sort
	//O(n^2) time and O(1) space
	//Stable
	static void insertionSort(int[] nums) {
		for (int i = 1; i < nums.length; ++i) {
			int cur = nums[i];
			int j = i - 1;
			for (; j >= 0 && cur < nums[j]; --j) {
				nums[j+1] = nums[j];
			}
			nums[j+1] = cur;
		}

		System.out.println("After insertion sort: " + Arrays.toString(nums));
	}

	//Bubble sort
	//O(n^2) time and O(1) space
	//Stable
	static void bubbleSort(int[] nums) {
		for (int k = 0; k < nums.length; ++k) {
			boolean isExchanged = false;
			for (int i = nums.length - 1; i > k; --i) {
				if (nums[i] < nums[i-1]) {
					int temp = nums[i];
					nums[i] = nums[i-1];
					nums[i-1] = temp;
					isExchanged = true;
				}
			}
			if (!isExchanged) {
				break;
			}
		}

		System.out.println("After bubble sort: " + Arrays.toString(nums));
	}

	//Optimized bubble sort
	//O(n^2) time and O(1) space
	//Stable
	static void bubbleSortOptimized(int[] nums) {
		for (int k = 0; k < nums.length;) {
			int sortedId = nums.length;
			for (int i = nums.length - 1; i > k; --i) {
				if (nums[i] < nums[i-1]) {
					int temp = nums[i];
					nums[i] = nums[i-1];
					nums[i-1] = temp;
					sortedId = i;
				}
			}
			k = sortedId;
		}

		System.out.println("After optimized bubble sort: " + Arrays.toString(nums));
	}

	//Selection sort
	//O(n^2) time and O(1) space
	//Unstable
	static void selectionSort(int[] nums) {
		for (int i = 0; i < nums.length - 1; ++i) {
			int minId = i;
			for(int j = i + 1; j < nums.length; ++j) {
				if (nums[j] < nums[minId]) {
					minId = j;
				}
			}
			if (minId != i) {
				int temp = nums[i];
				nums[i] = nums[minId];
				nums[minId] = temp;
			}
		}
		System.out.println("After selection sort: " + Arrays.toString(nums));
	}

	//Merge sort.
	//O(nlogn) time and O(nlogn) space
	//Stable
	static int[] mergeSort(int[] nums, int start, int end) {
		if (start == end) {
			return new int[]{nums[start]};
		}
		int mid = start + (end - start) / 2;
		int[] leftSortedNums = mergeSort(nums, start, mid);
		int[] rightSortedNums = mergeSort(nums, mid + 1, end);
		int[] sortedNums = new int[end - start + 1];
		for (int left = 0, right = 0, k = 0; k < sortedNums.length; ++k) {
			if (left < leftSortedNums.length
				&& (right == rightSortedNums.length || leftSortedNums[left] <= rightSortedNums[right])) {
				sortedNums[k] = leftSortedNums[left++];
			} else {
				sortedNums[k] = rightSortedNums[right++];
			}
		}
		return sortedNums;
	}

	static void mergeSort(int[] nums) {
		if (nums.length > 0) {
			int[] sortedNums = mergeSort(nums, 0, nums.length - 1);
			System.arraycopy(sortedNums, 0, nums, 0, sortedNums.length);
		}
		System.out.println("After merge sort: " + Arrays.toString(nums));
	}

	public static void main(String[] args) {
		int[][] nums = new int[][]{
			{},
			{1},
			{1, 3, 5, 7},
			{9, 8, 3, 11, -55},
			{4, 4, 8, 12, 55, 3, 8, -3, 4}
		};
		for (int[] array : nums) {
			System.out.println("Original array: " + Arrays.toString(array));
			insertionSort(Arrays.copyOf(array, array.length));
			bubbleSort(Arrays.copyOf(array, array.length));
			bubbleSortOptimized(Arrays.copyOf(array, array.length));
			selectionSort(Arrays.copyOf(array, array.length));
			mergeSort(Arrays.copyOf(array, array.length));
			System.out.println("");
		}
		
	}
}