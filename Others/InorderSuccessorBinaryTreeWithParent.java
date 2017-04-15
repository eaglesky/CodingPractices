import java.util.*;

//http://techieme.in/inorder-successor-of-node-in-binary-tree/

public class InorderSuccessorBinaryTreeWithParent {
	private static class TreeNodeWithParent extends TreeNode {
    	private TreeNode parent;
    	TreeNodeWithParent(int val) {
    		super(val);
    		parent = null;
    	}

		public TreeNode parentNode() {
			return parent;
		}

		public void setParentNode(TreeNode node) {
			parent = node;
		}

    	static TreeNode createFromArray(Integer[] array) {
	        if (array.length == 0) {
	            return null;
	        }
	        int i = 0;
	        TreeNodeWithParent root = new TreeNodeWithParent(array[i]);
	        Deque<TreeNodeWithParent> q = new ArrayDeque<>();
	        q.offer(root);
	        while(!q.isEmpty() && i < array.length) {
	            TreeNode curNode = q.poll();
	            if (++i < array.length && array[i] != null) {
	                TreeNodeWithParent leftNode = new TreeNodeWithParent(array[i]);
	                curNode.left = leftNode;
	                leftNode.setParentNode(curNode);
	                q.offer(leftNode);
	            }
	            if (++i < array.length && array[i] != null) {
	                TreeNodeWithParent rightNode = new TreeNodeWithParent(array[i]);
	                curNode.right = rightNode;
	                rightNode.setParentNode(curNode);
	                q.offer(rightNode);
	            }
	        }
	        return root;
	    }
	}

	//O(h) time and O(1) space, h is the height of the tree.
	//If the node has a right sub-tree, then inorder successor 
	//is the left most node of the right sub-tree.
	//If the node doesn’t have a right sub-tree, then it is the first ancestor
	//(when we move up from node to root) whose left sub-tree contains this node.
	public static TreeNode inorderSuccessor(TreeNode root, TreeNode curNode) {
		if (curNode == null) {
			return null;
		}
		TreeNode nextNode = curNode.right;
		if (nextNode != null) {
			for(; nextNode.left != null; nextNode = nextNode.left);
			return nextNode;
		}
		nextNode = curNode.parentNode();
		for(; nextNode != null; curNode = nextNode, nextNode = nextNode.parentNode()) {
			if (nextNode.left == curNode) {
				return nextNode;
			}
		}
		return nextNode;
	}

	public static void main(String[] args) {
		Integer[] t1 = new Integer[] {
			1, 2, 3, null, null, 4, 5, null, 6, null, null, 7
		};
		TreeNode root = TreeNodeWithParent.createFromArray(t1);
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