/*
Given n books and the ith book has A[i] pages. You are given k people to copy the n books.

n books list in a row and each person can claim a continous range of the n books.
For example one copier can copy the books from ith to jth continously, but he can not
copy the 1st book, 2nd book and 4th book (without 3rd book).

They start copying books at the same time and they all cost 1 minute to copy 1 page
of a book. What's the best strategy to assign books so that the slowest copier can
finish at earliest time?

Example
Given array A = [3,2,4], k = 2.

Return 5( First person spends 5 minutes to copy book 1 and book 2 and second person
spends 4 minutes to copy book 3).
*/

public class CopyBooks {

    //The following solutions can all be found here:
    //http://massivealgorithms.blogspot.com/2015/11/copy-books-walking-dad.html
    
    
    //O(k*l^2) time and O(l) space
    //Let dp[k][l] be the min time of k people finishing copying first l books.
    //dp[k][l] = min{d[k-1][l], {max(d[k-1][j], p[j]), 0 <= j <= l-1}}
    public int copyBooks(int[] pages, int k) {
        if (pages == null || pages.length == 0 || k <= 0) {
            return 0;
        }
        int[] dp = new int[pages.length + 1];
        for (int l = 1; l <= pages.length; ++l) {
            dp[l] = dp[l-1] + pages[l - 1];
        }
        for (int i = 2; i <= k; ++i) {
            for (int l = pages.length; l >= 0; --l) {
                int lastPages = 0;
                for (int j = l - 1; j >= 0; --j) {
                    lastPages += pages[j];
                    dp[l] = Math.min(dp[l], Math.max(dp[j], lastPages));
                }
            }
        }
        return dp[pages.length];
    }
    
    //Binary search solution. O(l*log(m/k)) time and O(1) space,
    //m is the total sum of pages.
    //Not recommended since the time complexity could be huge if m is big
    //https://www.jiuzhang.com/qa/2731/
    /* Just check if k people is enough to finish the job if each one
       can take at most x minutes. Then m/k <= x <= m, if k people are needed.
       So we can have a function bool valid(pages,  k,  x) to check it, and 
       binary search in the range of [m/k, m], for each mid value, call
       valid(pages, k, mid), until we find the min valid value within that range.
    */

    //O(nk) solution ???
}