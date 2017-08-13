/*
Give an integer array，find the longest increasing continuous 
subsequence in this array.

An increasing continuous subsequence:

Can be from right to left or from left to right.
Indices of the integers in the subsequence should be continuous.

Example
For [5, 4, 2, 1, 3], the LICS is [5, 4, 2, 1], return 4.

For [5, 1, 2, 3, 4], the LICS is [1, 2, 3, 4], return 4.
*/

public class LongestIncreasingContinuousSubsequence {
    /*
     * @param : An array of Integer
     * @return: an integer
     */
    
    private void reverseInPlace(int[] A) {
        for (int start = 0, end = A.length - 1; start < end; start++, end--) {
            int temp = A[start];
            A[start] = A[end];
            A[end] = temp;
        }
    }
    
    private int longestLen(int[] A) {
        int maxLen = 0;
        int prev = 0;
        for (int i = 0; i < A.length; ++i) {
            int cur = 1;
            if (i >= 1 && A[i-1] < A[i]) {
                cur += prev;
            }
            prev = cur;
            maxLen = Math.max(maxLen, cur);
        }
        return maxLen;
    }
    
    public int longestIncreasingContinuousSubsequence(int[] A) {
        int maxLen0 = longestLen(A);
        reverseInPlace(A);
        int maxLen1 = longestLen(A);
        return Math.max(maxLen0, maxLen1);
    }
};