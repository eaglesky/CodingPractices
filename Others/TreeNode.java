import java.util.*;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }

    public TreeNode parentNode() {
        return null;
    }

    public void setParentNode(TreeNode node) {
        return;
    }

    static TreeNode createFromArray(Integer[] array) {
        if (array.length == 0) {
            return null;
        }
        int i = 0;
        TreeNode root = new TreeNode(array[i]);
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while(!q.isEmpty() && i < array.length) {
            TreeNode curNode = q.poll();
            if (++i < array.length && array[i] != null) {
                TreeNode leftNode = new TreeNode(array[i]);
                curNode.left = leftNode;
                q.offer(leftNode);
            }
            if (++i < array.length && array[i] != null) {
                TreeNode rightNode = new TreeNode(array[i]);
                curNode.right = rightNode;
                q.offer(rightNode);
            }
        }
        return root;
    }

    static Map<Integer, TreeNode> printLevels(TreeNode root) {
        Map<Integer, TreeNode> result = new HashMap<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()) {
            TreeNode curNode = q.poll();
            if (curNode == null) {
                continue;
            } else {
                System.out.println(curNode + ": " + curNode.left + ", " + curNode.right);
            }
            result.put(curNode.val, curNode);
            q.offer(curNode.left);
            q.offer(curNode.right);
        }
        return result;
    }

    public static void main(String[] args) {
        Integer[] t1 = new Integer[]{5, 3, 6, 2, 4, null, 7, null, null, null, 8, 9, null};
        TreeNode root = createFromArray(t1);
        printLevels(root);
    }
}