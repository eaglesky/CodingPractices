/* There are n coins in a line. Two players take turns to take one or two coins
from right side until there are no more coins left. The player who take the last
coin wins.

Could you please decide the first play will win or lose?

Example
n = 1, return true.

n = 2, return true.

n = 3, return false.

n = 4, return true.

n = 5, return true.
*/

public class CoinsInLine {

    //O(n) time and O(1) space
    //d[i] is wheter the first player has a must-win strategy when
    //there are i coins.
    //d[i] = !d[i-1] || !d[i-2]
    public boolean firstWillWin(int n) {
        if (n <= 0) {
            return false;
        }
        if (n == 1 || n == 2) {
            return true;
        }
        boolean pre2 = true;
        boolean pre = true;
        for (int i = 3; i <= n; ++i) {
            boolean cur = !pre2 || !pre;
            pre2 = pre;
            pre = cur;
        }
        return pre;
    }
}