public class MaxSubarray2 {
    /**
     * @param nums: A list of integers
     * @return: An integer denotes the sum of max two non-overlapping subarrays
     */

    //Convert the problem into maximum k segments sum problem.
    //DP solution, O(kn) time and O(n) space.
    //d[m][l] is the maximum sum of m subarrays in the first l elements,
    //and the mth subarray ends with the lth element.
    //d[m][l] = max(d[m][l-1], max(d[m-1][l-1], d[m-1][l-2] ... d[m-1][m-1])) + nums[l-1]
    //Calculate the second max on the fly.
    //result = max({d[k][l]}), k <= l <= nums.size()
    public int maxTwoSubArrays(ArrayList<Integer> nums) {
        int[] prevSums = new int[nums.size() + 1];
        int[] curSums = new int[nums.size() + 1];
        int k = 2;
        int result = Integer.MIN_VALUE;
        for (int m = 1; m <= k; ++m) {
            curSums[0] = 0;
            for (int l = 1; l <= nums.size(); ++l) {
                if (l < m) {
                    curSums[l] = curSums[l-1] + nums.get(l-1);
                } else {
                    prevSums[l] = Math.max(prevSums[l-1], prevSums[l]);
                    curSums[l] = Math.max(prevSums[l-1], curSums[l-1]) + nums.get(l-1);
                }
            }
            int[] temp = prevSums;
            prevSums = curSums;
            curSums = temp;
        }
        for (int i = k; i <= nums.size(); ++i) {
            result = Math.max(result, prevSums[i]);
        }
        return result;
    }

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

