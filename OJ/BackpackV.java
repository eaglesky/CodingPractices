/*
Given n items with size nums[i] which an integer array and all positive numbers.
An integer target denotes the size of a backpack. Find the number of possible fill
the backpack.

Each item may only be used once

Example
Given candidate items [1,2,3,3,7] and target 7,

A solution set is: 
[7]
[1, 3, 3]
return 2
*/

public class BackpackV {

    //O(n*target) time and O(target) space
    //Let dp[i][k] be how many ways for the first i items have combinations
    //with sum of k
    //dp[i][k] = dp[i-1][k] + dp[i-1][k-nums[i-1]
    public int backPackV(int[] nums, int target) {
        if (nums == null || nums.length == 0 || target <= 0) {
            return 0;
        }
        int dp[] = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= nums.length; ++i) {
            for (int k = target; k >= nums[i-1]; --k) {
                dp[k] += dp[k - nums[i-1]];
            }
        }
        return dp[target];
    }
}