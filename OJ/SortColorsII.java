public class SortColorsII {
    /*
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
     
    //Naive solution is to use counting sort, O(n) time and O(k) extra space.

    //However we can use the original array to store the counts instead of the
    //O(k) extra space.
    //This works because the elements in colors array
    //must be between [1, k], and nothing is missing from
    //1 ... k. k <= colors.length
    //O(n) time and O(1) space
    public void sortColors2(int[] colors, int k) {
        for (int i = 0; i < colors.length; ++i) {
            if (colors[i] > 0) {
                int targetId = colors[i] - 1;
                colors[i] = 0;
                for(; colors[targetId] > 0;) {
                    int temp = colors[targetId];
                    colors[targetId] = -1;
                    targetId = temp - 1;
                }
                colors[targetId]--;
            }
        }
        
        int lastId = colors.length - 1;
        for (int i = colors.length - 1; i >= 0; --i) {
            int count = Math.abs(colors[i]);
            for(; count > 0; --count) {
                colors[lastId--] = i + 1;
            }
        }
    }

    //Another way, have a helper function that rearrange elements
    //within a range so that the minium elements are on the left,
    //while the maximum elements are on the right. Return the 
    //ids of the start and end elements of the unsorted one. Then
    //use the helper function again to sort the ones in between.
    //Run time is O(k/2 * n) = O(kn) time, O(1) extra space
}