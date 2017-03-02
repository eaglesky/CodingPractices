/*
Consider this string representation for binary trees. Each node is of the form (lr), 
where l represents the left child and r represents the right child. If l is the character 0,
then there is no left child. Similarly, if r is the character 0, then there is no right child.
Otherwise, the child can be a node of the form (lr), and the representation continues 
recursively. For example: (00) is a tree that consists of one node. ((00)0) is a two-node
tree in which the root has a left child, and the left child is a leaf. And ((00)(00)) is a 
three-node tree, with a root, a left and a right child.
Write a function that takes as input such a string, and returns -1 if the string is malformed, 
and the depth of the tree if the string is well-formed.
For instance:
  find_depth('(00)') -> 0 
  find_depth('((00)0)') -> 1 
  find_depth('((00)(00))') -> 1 
  find_depth('((00)(0(00)))') -> 2 
  find_depth('((00)(0(0(00))))') -> 3 
  find_depth('x') -> -1 
  find_depth('0') -> -1 
  find_depth('()') -> -1 
  find_depth('(0)') -> -1 
  find_depth('(00)x') -> -1 
  find_depth('(0p)') -> -1
 */

public class FindDepth {
	
	//Recursive solution, O(nh) time and O(h) space
	//Can be optimized using memoization
	private static int findDepth(String s, int start, int end) {
		if (start < 0 || end >= s.length() || start > end) {
			return -2;
		}
		if (end == start) {
			return s.charAt(start) == '0' ? -1 : -2;
		} else if (s.charAt(start) != '(' || s.charAt(end) != ')') {
			return -2;
		}
		int leftParenNum = 0, rightParenNum = 0;
		int i = start + 1;
		for (; i <= end - 1; ++i) {
			char c = s.charAt(i);
			if (c == '(') {
				leftParenNum++;
			} else if (c == ')') {
				rightParenNum++;
			} else if (c != '0') {
				return -2;
			}
			if (leftParenNum == rightParenNum) {
				break;
			}
		}
		int leftDepth = findDepth(s, start + 1, i);
		if (leftDepth == -2) {
			return -2;
		}
		int rightDepth = findDepth(s, i + 1, end - 1);
		if (rightDepth == -2) {
			return -2;
		}
		return Math.max(leftDepth, rightDepth) + 1;
	}

	public static int findDepth(String s) {
		int depth = findDepth(s, 0, s.length() - 1);
		return depth < 0 ? -1 : depth;
	}

	//Another iterative solution based on replacing "(00)" with "0" to remove 
	//all leaves in each iteration until the string becomes "0" or some invalid letters
	//are detected. The number of iteration is the result.
	//http://wxx5433.github.io/find-depth.html
	//O(nh) time and O(n) space. 
	
	public static void main(String[] args) {
		String[] strs = {
			"(00)",
			"((00)0)",
			"((00)(00))",
			"((00)(0(00)))",
			"((00)(0(0(00))))",
			"x",
			"0",
			"()",
			"(0)",
			"(00)x",
			"(0p)",
			"",
			")(00)0",
			"(00))(00)"
		};
		int[] vals = {0, 1, 1, 2, 3, -1, -1, -1, -1, -1, -1, -1, -1, -1};
		for (int i = 0; i < strs.length; ++i) {
			int testVal = findDepth(strs[i]);
			System.out.print(strs[i] + ": " + testVal);
			if (testVal != vals[i]) {
				System.out.println(" Wrong! Should be " + vals[i]);
			} else {
				System.out.println(" Correct!");
			}
		}
	}
}