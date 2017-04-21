/*
http://practice.geeksforgeeks.org/problems/longest-arithmetic-progression/0
Length of Longest Arithmetic Progression in a sorted array.
*/

import java.util.*;
import java.lang.*;
import java.io.*;

class LongestArithmeticProgressionSorted {

	//DP solution, O(n^2) time and O(n^2) space
	//http://www.geeksforgeeks.org/length-of-the-longest-arithmatic-progression-in-a-sorted-array/
	//My solution also works when there are duplicates in the given array
    private static int lengthOfLAPSorted(int[] a) {
        if (a.length <= 2) {
        	return a.length;
        }
        int len = 2;
        int n = a.length;
        int[][] d = new int[n][n];
        for (int i = 0; i < n - 1; ++i) {
        	for (int j = i + 1; j < n; ++j) {
        		d[i][j] = 2;
        	}
        }
        for (int i = n - 2; i >= 1; --i) {
        	int l = i - 1;
        	int r = i + 1;
        	int mid = 2 * a[i];
        	while (l >= 0 && r <= n - 1) {
        		if (mid == a[l] + a[r]) {
        			d[l][i] = Math.max(d[l][i], d[i][r] + 1);
        			len = Math.max(len, d[l][i]);
        			--l;
        		} else if (mid < a[l] + a[r]) {
        			--l;
        		} else {
        			++r;
        		}
        	}
        }
        return len;
    }
    
    //It can also be solved using the solution in LongestArithmeticProgression
    //(unsorted case), however the space complexity is slightly higher.
   
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
 
        while (t > 0)
        {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            System.out.println(lengthOfLAPSorted(arr));
            t--;
        }
	}
}