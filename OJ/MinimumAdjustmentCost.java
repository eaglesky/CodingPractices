/*
Given an integer array, adjust each integers so that the difference of every adjacent
integers(absolute value) are not greater than a given number target.

If the array before adjustment is A, the array after adjustment is B, you should 
minimize the sum of |A[i]-B[i]|

Notice

You can assume each number in the array is a positive integer and not greater than 100.
target is non-negative.

Example
Given [1,4,2,3] and target = 1, one of the solutions is [2,3,2,3], the adjustment cost
is 2 and it's minimal.

Return 2.
*/

public class MinimumAdjustmentCost {

    //DP solution, O(l*range*target) time and O(range) space
    //d[l][b] is the total minimum cost of first l elements,
    //where A[l-1] is changed to b. b must be in the same range.
    //d[l][b] = min{d[l-1][t], max{1, b-target} <= t <= min{100, b+target}}
    //          + |A[l-1] - b|
    //d[0][x] = 0
    //If the range is not between 1 and 100, we can get the range by iterating
    //the input array. If the bound is negative, we can use map of b_value to
    //min_cost_value instead of d[b]. 
    public int MinAdjustmentCost(List<Integer> A, int target) {
        if (A == null || A.isEmpty() || target < 0) {
            return 0;
        }
        int len = A.size();
        int minVal = 1;
        int maxVal = 100;
        int[][] d = new int[2][maxVal + 1];
        int cur = 1;
        int pre = 0;
        for (int l = 1; l <= len; ++l) {
            for (int b = minVal; b <= maxVal; ++b) {
                d[cur][b] = Integer.MAX_VALUE;
                for (int t = Math.max(minVal, b - target); 
                    t <= Math.min(maxVal, b + target); ++t) {
                    d[cur][b] = Math.min(d[cur][b], 
                                         d[pre][t] + Math.abs(A.get(l-1) - b));
                }
            }
            pre = cur;
            cur = 1 - cur;
        }
        int result = Integer.MAX_VALUE;
        for (int i = minVal; i <= maxVal; ++i) {
            result = Math.min(result, d[pre][i]);
        }
        return result;
    }
}