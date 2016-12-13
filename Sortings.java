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

	//Top-down merge sort.
	//O(nlogn) time and O(n) space
	//Stable
	private static int[] mergeSort(int[] nums, int start, int end) {
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

	static void mergeSortTopDown(int[] nums) {
		if (nums.length > 0) {
			int[] sortedNums = mergeSort(nums, 0, nums.length - 1);
			System.arraycopy(sortedNums, 0, nums, 0, sortedNums.length);
		}
		System.out.println("After top-down merge sort: " + Arrays.toString(nums));
	}

	//Bottome-up merge sort.
	//Performance and stability are the same as the top-down one.
	static void mergeSortBottomUp(int[] nums) {
		int[] nums2 = Arrays.copyOf(nums, nums.length);
		for(int k = 1; k < nums.length; k = k << 1) {
			int[] temp = new int[nums2.length];
			for (int leftStart = 0; leftStart < nums2.length;) {
				int rightStart = leftStart + k;	
				if (rightStart >= nums2.length) {
					for (; leftStart < nums2.length; ++leftStart) {
						temp[leftStart] = nums2[leftStart];
					}
				} else {
					int rightEnd = Math.min(rightStart + k, nums2.length);
					for(int i = leftStart, j = rightStart; i < rightStart || j < rightEnd;) {
						if (i < rightStart && (j == rightEnd || nums2[i] <= nums2[j])) {
							temp[leftStart++] = nums2[i++];
						} else {
							temp[leftStart++] = nums2[j++];
						}
					}
				}
			}
			nums2 = temp;
		}
		System.arraycopy(nums2, 0, nums, 0, nums.length);
		System.out.println("After bottom-up merge sort: " + Arrays.toString(nums));
	}

	private static Random rand = new Random();

	private static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	private static int partition(int[] nums, int start, int end) {
		if (start >= end) {
			return start;
		}
		int id = rand.nextInt(end - start + 1) + start;
		int pivot = nums[id];
		int i = start;
		//Has to be i <= j rather than i < j, because it is possible that 
		//i == j after swapping and changing i and j. When that happens, 
		//nums[i] has to be checked again. nums[i] .. nums[end] must be guranteed 
		//to be larger than nums[start]..num[i-1]. Therefore equals should be added
		//to the break condition. And it should also be added to the condition of swap
		//to prevent indefinite loop.
		//E.g. nums = [2, 1, 2], and id = 2. 
		for (int j = end; i <= j;) {
			for (; nums[i] < pivot; ++i);
			for (; nums[j] > pivot; --j);
			if (i <= j) {
				swap(nums, i, j);
				++i;
				--j;
			}
		}
		return i;
	}

	//Quick sort
	//Worst case time O(n^2). Average O(nlogn). O(n) space worst case. O(logn) space on average
	//Unstable
	private static void quickSort(int[] nums, int start, int end) {
		if (start < end) {
			int i = partition(nums, start, end);
			quickSort(nums, start, i-1);
			quickSort(nums, i, end);
		}
	}

	static void quickSort(int[] nums) {
		quickSort(nums, 0, nums.length - 1);
		System.out.println("After quick sort: " + Arrays.toString(nums));
	}

	//Counting sort
	//O(n + k) time and O(n + k) space. Works only when the input elements can be
	//mapped to a range of integers [0, k-1].
	//Stable!
	static void countingSort(int[] nums) {
		if (nums.length <= 1) {
			return;
		}
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for(int num : nums) {
			min = Math.min(num, min);
			max = Math.max(num, max);
		}
		int[] counts = new int[max - min + 1];
		for (int num : nums) {
			counts[num - min]++;
		}
		for (int i = 1; i < counts.length; ++i) {
			counts[i] += counts[i-1];
		}
		int[] sorted = new int[nums.length];
		for (int i = nums.length - 1; i >= 0; --i) {
			int countId = nums[i] - min;
			sorted[counts[countId]-1] = nums[i];
			counts[countId]--;
		}
		System.arraycopy(sorted, 0, nums, 0, nums.length);
		System.out.println("After counting sort: " + Arrays.toString(nums));
	}

	public static void main(String[] args) {
		int[][] nums = new int[][]{
			{},
			{1},
			{1, 3, 5, 7},
			{9, 8, 3, 11, -55},
			{4, 4, 8, 12, 55, 3, 8, -3, 4},
			{2, 2, 3, 2, 1, 2}
		};
		for (int[] array : nums) {
			System.out.println("Original array: " + Arrays.toString(array));
			insertionSort(Arrays.copyOf(array, array.length));
			bubbleSort(Arrays.copyOf(array, array.length));
			bubbleSortOptimized(Arrays.copyOf(array, array.length));
			selectionSort(Arrays.copyOf(array, array.length));
			mergeSortTopDown(Arrays.copyOf(array, array.length));
			mergeSortBottomUp(Arrays.copyOf(array, array.length));
			quickSort(Arrays.copyOf(array, array.length));
			countingSort(Arrays.copyOf(array, array.length));
			System.out.println("");
		}
		
	}
}