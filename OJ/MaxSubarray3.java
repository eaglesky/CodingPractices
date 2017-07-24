public class MaxSubarray3 {
    /**
     * @param nums: A list of integers
     * @param k: An integer denote to find k non-overlapping subarrays
     * @return: An integer denote the sum of max k non-overlapping subarrays
     */

    //Convert the problem into maximum k segments sum problem.
    //DP solution, O(kn) time and O(n) space.
    //d[m][l] is the maximum sum of m subarrays in the first l elements,
    //and the mth subarray ends with the lth element.
    //d[m][l] = max(d[m][l-1], max(d[m-1][l-1], d[m-1][l-2] ... d[m-1][m-1])) + nums[l-1]
    //Calculate the second max on the fly.
    //result = max({d[k][l]}), k <= l <= nums.size()
    public int maxSubArray(ArrayList<Integer> nums) {
        int[] prevSums = new int[nums.size() + 1];
        int[] curSums = new int[nums.size() + 1];
        int k = 2;
        //The following is the k case
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

    //Best solution using only one additional array
    public int maxSubArray(int[] nums, int k) {
        int[] dp = new int[nums.length + 1];
        for (int m = 1; m <= k; ++m) {
            int prevMax = dp[m-1];
            for (int l = m; l <= nums.length; ++l) {
                int cur = prevMax + nums[l-1];
                prevMax = Math.max(prevMax, dp[l]);
                if (l > m) {
                    cur = Math.max(cur, dp[l-1] + nums[l-1]);
                }
                dp[l] = cur;
            }
        }
        int maxSum = Integer.MIN_VALUE;
        for (int l = k; l <= nums.length; ++l) {
            maxSum = Math.max(maxSum, dp[l]);
        }
        return maxSum;
    }
}
