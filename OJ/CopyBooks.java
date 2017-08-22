public class CopyBooks {

    //The following solution can all be found here:
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