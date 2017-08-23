/*
Given n items with size Ai and value Vi, and a backpack with size m. 
What's the maximum value can you put into the backpack?

Notice

You cannot divide item into small pieces and the total size of items you choose
should smaller or equal to m.

Example
Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], and a backpack with size 10.
The maximum value is 9.
*/

public class BackpackII {

    /*
    Greedy algorithm that chooses the item with higher per unit value doesn't work
    because each item cannot be divided and the room left may not be able to fit
    a new item.
    E.g. [2.1, 1, 1, 1, 1], value: [2.2, 1, 1, 1, 1], size = 4. 
    */

    //DP, O(mn) time and O(m) space
    //dp[l][w] is the maximum value of first l items with size limit of w
    //dp[l][w] = max{dp[l-1][w], dp[l-1][w-A[l-1]] + V[l-1]}
    public int backPackII(int m, int[] A, int[] V) {
        if (A == null || V == null || A.length == 0 || A.length != V.length) {
            return 0;
        }
        int n = A.length;
        int[] dp = new int[m + 1];
        for (int l = 1; l <= n; ++l) {
            for (int w = m; w >= A[l-1]; --w) {
                dp[w] = Math.max(dp[w], dp[w - A[l-1]] + V[l-1]);
            }
        }
        return dp[m];
    }
}