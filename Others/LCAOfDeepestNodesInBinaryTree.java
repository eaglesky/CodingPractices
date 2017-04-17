/*
Find the lowest common ancestor of all the deepest nodes in a binary tree.
If there is only one deepest node, return the node itself.
The solution should be able to generalized to n-ary tree as well.
*/

public class LCAOfDeepestNodesInBinaryTree {
	private static class MyTreeNode {
		TreeNode node;
		int maxDepth;
		MyTreeNode(TreeNode node, int depth) {
			this.node = node;
			this.maxDepth = depth;
		}
	}

	//O(n) time and O(h) space
	private static MyTreeNode dfs(TreeNode node, int curDepth) {
		if (node == null) {
			return new MyTreeNode(null, curDepth - 1);
		}
		MyTreeNode leftLCA = dfs(node.left, curDepth + 1);
		MyTreeNode rightLCA = dfs(node.right, curDepth + 1);
		if (leftLCA.maxDepth == rightLCA.maxDepth) {
			return new MyTreeNode(node, leftLCA.maxDepth);
		} else if (leftLCA.maxDepth < rightLCA.maxDepth) {
			return rightLCA;
		} else {
			return leftLCA;
		}
	}

	public static TreeNode lcaOfDeepestNodesInBinaryTree(TreeNode root) {
		return dfs(root, 0).node;
	}

	//The above solution can be implemented iteratively, using postorder iterative
	//traversal. Need to maintain a map of node to <lca, max hight>. We can delete
	//the entry corresponding to the left and right child after reaching the current
	//node, to save memory. 

	//The above solutions are optimal, which can also be easily generalized to n-ary
	//trees. There are also some other ideas. 
	//We can first find out the deepest nodes using DFS/BFS, and then find out
	//the LCA of leftmost and rightmost deepest nodes. This requires two passes
	//of all the nodes, which is slower than the previous one-pass solution.
	//We can also build a map of node to its parent during traversal, and then
	//finding the LCA of leftmost and rightmost deepest nodes would take O(h).
	//However the space usage is high due to the queue used by BFS and the map.
	//The run time is still higher than the optimal solution.

	//References: 
	//http://www.1point3acres.com/bbs/thread-199739-2-1.html
	//http://www.1point3acres.com/bbs/thread-148413-1-1.html

	public static void main(String[] args) {
		Integer[][] tests = new Integer[][]{
			new Integer[]{1, 2, 3, 4, 5, null, 6, null, null, 7, null, 8, null},
			new Integer[]{1, 2, 3, null, null, 4, 5, null, 6, 7, null},
			new Integer[]{1, 2, 3, null, 4, null, null}
		};
		for (int i = 0; i < tests.length; ++i) {
			Integer[] test = tests[i];
			TreeNode root = TreeNode.createFromArray(test);
			System.out.println("Tree " + (i + 1));
			TreeNode.printLevels(root);
			TreeNode lca = lcaOfDeepestNodesInBinaryTree(root);
			System.out.println("LCA = " + lca.val);
			System.out.println("");
		}
		//Expected LCAs for all of the tests should be:
		//1, 3, 4.
	}
}