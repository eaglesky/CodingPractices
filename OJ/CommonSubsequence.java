/**
 * https://www.lintcode.com/en/problem/longest-common-subsequence/
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CommonSubsequence {

	//DP solution, O(mn) time and O(n) space, m is the length of s1, n is the length of s2
	//commonIds stores the ids in s1 of the common subsequence
	//d[l1][l2] = d[l1-1][l2-1] + 1 , if s1[l1-1] == s2[l2-1]
	//         or max(d[l1][l2-1], d[l1-1][l2]) , if otherwise
	public static int longestCommonSubsequence0(String s1, String s2, List<Integer> commonIds) {
		int[] prevLens = new int[s2.length() + 1];
		int[] curLens = new int[s2.length() + 1];
		int[][] paths = new int[s1.length() + 1][s2.length() + 1];
		for (int i = 1; i <= s1.length(); ++i) {
			curLens[0] = 0;
			for (int j = 1; j <= s2.length(); ++j) {
				if (s1.charAt(i-1) == s2.charAt(j-1)) {
					curLens[j] = prevLens[j-1] + 1;
				} else {
					curLens[j] = Math.max(prevLens[j], curLens[j-1]);
					paths[i][j] = (prevLens[j] > curLens[j-1]) ? 1 : -1;
				}
			}
			int[] temp = prevLens;
			prevLens = curLens;
			curLens = temp;
		}

		getCommonIds(paths, s1.length(), s2.length(), commonIds);
		return prevLens[s2.length()];
	}

	//O(m + n) time and O(m + n) space
	private static void getCommonIds(int[][] paths, int i, int j, List<Integer> commonIds) {
		if (i == 0 || j == 0) {
			return;
		}
		if (paths[i][j] == 0) {
			getCommonIds(paths, i-1, j-1, commonIds);
			commonIds.add(i-1);
		} else if (paths[i][j] == 1) {
			getCommonIds(paths, i-1, j, commonIds);
		} else if (paths[i][j] == -1) {
			getCommonIds(paths, i, j-1, commonIds);
		}
	}

	private static void getCommonIdsFromLens(int[][] lens, String s1, String s2, 
											 int l1, int l2, List<Integer> commonIds) {
		if (l1 == 0 || l2 == 0) {
			return;
		}
		if (s1.charAt(l1-1) == s2.charAt(l2-1)) {
			getCommonIdsFromLens(lens, s1, s2, l1-1, l2-1, commonIds);
			commonIds.add(l1-1);
		} else if (lens[l1][l2-1] > lens[l1-1][l2]) {
			getCommonIdsFromLens(lens, s1, s2, l1, l2-1, commonIds);
		} else {
			getCommonIdsFromLens(lens, s1, s2, l1-1, l2, commonIds);
		}
	}

	//Memoization solution
	private static int longestCommonSubsequence(String s1, String s2, int l1, int l2, int[][] lens) {
		if (l1 == 0 || l2 == 0) {
			lens[l1][l2] = 0;
			return 0;
		}
		int length = 0;
		if (s1.charAt(l1-1) == s2.charAt(l2-1)) {
			length = (lens[l1-1][l2-1] > 0) ? lens[l1-1][l2-1] + 1 
				: longestCommonSubsequence(s1, s2, l1-1, l2-1, lens) + 1;
		} else {
			int length1 = lens[l1][l2-1] > 0 ? lens[l1][l2-1] : longestCommonSubsequence(s1, s2, l1, l2-1, lens);
			int length2 = lens[l1-1][l2] > 0 ? lens[l1-1][l2] : longestCommonSubsequence(s1, s2, l1-1, l2, lens);
			length = Math.max(length1, length2);
		}
		lens[l1][l2] = length;
		return length;
	}

	public static int longestCommonSubsequence(String s1, String s2, List<Integer> commonIds) {
		int[][] lens = new int[s1.length() + 1][s2.length() + 1];
		int len = longestCommonSubsequence(s1, s2, s1.length(), s2.length(), lens);
		getCommonIdsFromLens(lens, s1, s2, s1.length(), s2.length(), commonIds);
		return len;
	}


	public static void main(String[] args) {
		String fileName = args[0];
		//System.out.println("input file: " + fileName);
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] strs = line.split("[\t ]+");
				List<Integer> commonIds = new ArrayList<>();
				int maxLen = longestCommonSubsequence(strs[0], strs[1], commonIds);
				System.out.println("str1 = " + strs[0]);
				System.out.println("str2 = " + strs[1]);
				System.out.println(commonIds);
				System.out.println(maxLen);
				System.out.println("");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}