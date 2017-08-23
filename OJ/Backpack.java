/*
Given n items with size Ai, an integer m denotes the size of a backpack. How full you 
can fill this backpack?

Notice:

You can not divide any item into small pieces.

Example
If we have 4 items with size [2, 3, 5, 7], the backpack size is 11, we can select
[2, 3, 5], so that the max size we can fill this backpack is 10. If the backpack size
is 12. we can select [2, 3, 7] so that we can fulfill the backpack.

You function should return the max size we can fill in the given backpack.
*/

public class Backpack {

    //O(nm) time and O(m) space
    //Let dp[i][k] be the maximum size of first i items no greater than k.
    //dp[i][k] = max{dp[i-1][k], dp[i-1][k-A[i-1]] + A[i-1], k >= A[i-1]}
    public int backPack0(int m, int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int[] dp = new int[m + 1];
        for (int i = 1; i <= A.length; ++i) {
            for (int k = m; k >= A[i-1]; --k) {
                dp[k] = Math.max(dp[k], dp[k - A[i-1]] + A[i-1]);
            }
        }
        return dp[m];
    }
    
    //Another DP solution, O(nm) time and O(m) space.
    //f[i][w] is if the first i items can have exact size w
    //f[i][w] = f[i-1][w] || f[i-1][w - A[i-1]]
    public int backPack(int m, int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        boolean[] f = new boolean[m + 1];
        f[0] = true;
        for (int i = 1; i <= A.length; ++i) {
            for (int k = m; k >= A[i-1]; --k) {
                f[k] = f[k] || f[k - A[i-1]];
            }
        }
        for (int w = m; w >= 0; --w) {
            if (f[w]) {
                return w;
            }
        }
        return 0;
    }
}