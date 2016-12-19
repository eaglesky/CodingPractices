/**
* Let A be an unsorted array of n integers, with A[O] >= A[1] and A[n- 2] <= A[n- 1]. 
* Call an index i a local minimum if A[i] is less than or equal to its neighbors.
* How would you efficiently find a local minimum, if one exists?
*/

import java.util.*;

class SearchLocalMinimum {

	//Returns the index of local minimum. There could be one or no neighbors.
	//Returns -1 if the local minimum doesn't exsit, which will only happen when
	//the input is not valid.
	//O(logn) time and O(1) space
	public static int findLocalMinimum(int[] nums) {
		int low = 0;
		int high = nums.length - 1;
		while (low <= high) {
			int mid = low + ((high - low) >> 1);
			if ((mid == 0 || (mid > 0 && nums[mid] <= nums[mid-1]))
			 && (mid == nums.length - 1 || (mid < nums.length - 1) && nums[mid] <= nums[mid + 1])) {
				return mid;
			}
			if (mid == nums.length - 1 || (mid < nums.length - 1) && nums[mid] <= nums[mid + 1]) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int[][] validTests = {
			{1},
			{1, 1},
			{8, 7, 6, 6, 6, 7},
			{1, 1, 8, 99, -2, 7, -2, 8, 9}
		};
		int[][] invalidTests = {
			{},
			{1, 2},
			{1, 2, 3, 4, 5}
		};

		System.out.println("Valid input");
		for(int[] test : validTests) {
			System.out.println(Arrays.toString(test));
			System.out.println(findLocalMinimum(test));
			System.out.println("");
		}

		System.out.println("Invalid input");
		for(int[] test : invalidTests) {
			System.out.println(Arrays.toString(test));
			System.out.println(findLocalMinimum(test));
			System.out.println("");
		}
	}

}