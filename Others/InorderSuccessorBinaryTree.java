import java.util.*;

public class InorderSuccessorBinaryTree {

	//O(n) time and O(h) space. n is the number of node and h is the height.
	//Tested on Leetcode OJ.
	private static TreeNode dfs(TreeNode curNode, TreeNode testNode, TreeNode[] prevNode) {
		if (curNode == null) {
			return null;
		}
		TreeNode node = dfs(curNode.left, testNode, prevNode);
		if (node != null) {
			return node;
		}
		if (prevNode[0] == testNode) {
			return curNode;
		}
		prevNode[0] = curNode;
		return dfs(curNode.right, testNode, prevNode);
	}

	public static TreeNode inorderSuccessor(TreeNode root, TreeNode curNode) {
		return dfs(root, curNode, new TreeNode[1]);
	}

	//Another easier way is to do inorder traversal iteratively using a stack.

	public static void main(String[] args) {
		Integer[] t1 = new Integer[] {
			1, 2, 3, null, null, 4, 5, null, 6, null, null, 7
		};
		TreeNode root = TreeNode.createFromArray(t1);
		Map<Integer, TreeNode> valToNode = TreeNode.printLevels(root);
		for (int i = 1; i <= 7; ++i) {
			TreeNode testNode = valToNode.get(i);
			TreeNode nextNode = inorderSuccessor(root, testNode);
			Integer nextVal = nextNode == null ? null : nextNode.val;
			System.out.println("Successor of node " + testNode.val
				+ " is " + nextVal);
		}
		//Expected output: 4, 1, 5, 7, null, 3, 6.
	}
}