/*
Given n distinct positive integers, integer k (k <= n) and a number target.

Find k numbers where sum is target. Calculate how many solutions there are?

Example
Given [1,2,3,4], k = 2, target = 5.

There are 2 solutions: [1,4] and [2,3].

Return 2.
*/

public class KSum {

    //DP solution, O(l*k*target) time and O(k*target) space
    //d[l][kk][t] is the number of solutions that in the first l elements,
    //kk of them add up to t. 
    //d[l][kk][t] = d[l-1][kk-1][t-a[l-1]]| (k <= l && t >= A[l-1])
    //            + d[l-1][kk][t]|(k <= l-1)
    //d[x][0][0] = 1, d[x][0][y]|y > 0 = 0
    //Note that if the element and target could be negative, then we
    //can use Map<target, num>[l][k] as the dp array instead. Need to sort and
    //sum the input array to get the maximum and minimum possible sums as the
    //range of target. Or simply use memoization.
    public int kSum(int[] A, int k, int target) {
        if (A == null || k < 0 || target < 0 || A.length < k) {
            return 0;
        }
        int[][] d = new int[k + 1][target + 1];
        d[0][0] = 1;
        for (int l = 1; l <= A.length; ++l) {
            for (int kk = Math.min(l, k); kk > 0; --kk) {
                for (int t = target; t >= A[l-1]; --t) {
                    if (kk <= l - 1) {
                        d[kk][t] += d[kk - 1][t - A[l-1]];
                    } else {
                        d[kk][t] = d[kk - 1][t - A[l-1]];
                    }
                }
            }
        }
        return d[k][target];
    }
}