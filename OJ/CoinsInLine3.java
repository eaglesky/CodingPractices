/*
There are n coins in a line. Two players take turns to take a coin from one of the
ends of the line until there are no more coins left. The player with the larger amount
of money(or equal) wins.

Could you please decide the first player will win or lose?

Example
Given array A = [3,2,2], return true.

Given array A = [1,2,4], return true.

Given array A = [1,20,4], return false.

Follow Up Question:

If n is even. Is there any hacky algorithm that can decide whether first player
will win or lose in O(1) memory and O(n) time?
*/

public class CoinsInLine3 {

    //The first player wins if they have equal amount of money
    //O(n^2) time and O(n) space
    //Let dp[i][j] be the max diff between the money of first
    //player and second player, if both are using same smart strategy,
    //which is trying to maximize the diff between oneself and the other.
    //dp[i][j] = max{v[j] - dp[i][j-1], v[i] - dp[i+1][j]}
    //If the first player sticks to the smart strategy all the time,
    //while the other player doesn't, then dp[i][j] can only be larger.
    //Think of a sequence of picked numbers when both are using the smart strategy.
    //If one selection made by the other player is not smart, say dp[i][j-1],
    //dp[i][j-1] will be less than equal to the result chosen by the smart strategy,
    //if dp[i][j] selects v[j], then dp[i][j] would be larger. This can be proved
    //recursively.
    public boolean firstWillWin(int[] values) {
        if (values == null || values.length == 0) {
            return false;
        }
        
        //If there are even number of coins, the first player always wins.
        //Add up the values at odd positions, say sumOdd, and the values at
        //even positions, say sumEven. If sumOdd >= sumEven, the first player
        //can pick the first coin, and the second player will be forced to
        //pick the coins at original even positions, and so on, the first player
        //must be able to gather all coins at original odd positions and win.
        //If sumOdd < sumEven, he can pick the last coin in the same way and win too.
        if ((values.length & 1) == 0) {
            return true;
        }
        
        int[] dp = new int[values.length];
        for (int i = values.length - 1; i >= 0; --i) {
            dp[i] = values[i];
            for (int j = i + 1; j < values.length; ++j) {
                dp[j] = Math.max(values[j] - dp[j-1], values[i]- dp[j]);
            }
        }
        return dp[values.length - 1] >= 0;
    }
    
    //Other solutions:
    //http://massivealgorithms.blogspot.com/2016/09/lintcode-396-coins-in-line-iii.html
    //Not as good as the above one
}