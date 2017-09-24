/*
https://www.interviewbit.com/problems/longest-arithmetic-progression/
*/

public class LongestArithmeticProgression {
    // DO NOT MODIFY THE LIST. IT IS READ ONLY

    //O(n^2) time and O(n^2) space
    public int solve(final List<Integer> A) {
        if (A.size() <= 2) {
            return A.size();
        }
        List<Map<Integer, Integer>> diffToLens = new ArrayList<>();
        int maxLen = 0;
        for (int i = 0; i < A.size(); ++i) {
            diffToLens.add(new HashMap<>());
            for (int j = i - 1; j >= 0; --j) {
                int diff = A.get(i) - A.get(j);
                int len = diffToLens.get(j).getOrDefault(diff, 1);
                diffToLens.get(i).putIfAbsent(diff, len + 1); 
                maxLen = Math.max(maxLen, len + 1);
            }
        }
        return maxLen;
    }

    //Another solution:
    //http://codercareer.blogspot.com/2014/03/no-53-longest-arithmetic-sequence.html
    //No better than mine.

    //To print out the result, just record the last element with the maximum length of
    //progression, we can get all the elements in the arithmetic progression from them.
}