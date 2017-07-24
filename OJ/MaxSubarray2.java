public class MaxSubarray2 {
    /**
     * @param nums: A list of integers
     * @return: An integer denotes the sum of max two non-overlapping subarrays
     */

    //Two passes solution.
    //O(n) time and O(n) space
    public int maxTwoSubArrays(ArrayList<Integer> nums) {
        if (nums == null || nums.isEmpty()) {
            return 0;
        }
        List<Integer> maxSumsLeft = new ArrayList<>();
        int prev = 0;
        int maxLeftSum = Integer.MIN_VALUE;
        for (int num : nums) {
            maxSumsLeft.add(maxLeftSum);
            int cur = Math.max(num, num + prev);
            maxLeftSum = Math.max(maxLeftSum, cur);
            prev = cur;
        }
        int maxSum = Integer.MIN_VALUE;
        prev = 0;
        for (int i = nums.size() - 1; i >= 1; --i) {
            int cur = Math.max(nums.get(i), nums.get(i) + prev);
            maxSum = Math.max(maxSum, maxSumsLeft.get(i) + cur);
            prev = cur;
        }
        return maxSum;
    }
}

