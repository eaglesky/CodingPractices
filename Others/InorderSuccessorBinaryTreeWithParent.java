import java.util.*;

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

	public static void main(String[] args) {
		Integer[] t1 = new Integer[] {
			1, 2, 3, null, null, 4, 5, null, 6, null, null, 7
		};
		TreeNode root = TreeNodeWithParent.createFromArray(t1);
		Map<Integer, TreeNode> valToNode = TreeNode.printLevels(root);
	}
}