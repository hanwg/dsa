package top.hanwg.dsa.trees;

import org.junit.jupiter.api.Assertions;

// #1448 medium
// Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.
// Return the number of good nodes in the binary tree.
public class CountGoodNodesInBinaryTree {

    int count = 0;
    public int goodNodes(TreeNode root) {
        count = 0;
        if (root == null) {
            return 0;
        }

        goodNodes(root, root.val);
        return count;
    }

    private void goodNodes(TreeNode root, int largestValue) {
        if (root == null) {
            return;
        }

        if (root.val >= largestValue) {
            count++;
        }

        goodNodes(root.left, Math.max(root.val, largestValue));
        goodNodes(root.right, Math.max(root.val, largestValue));
    }

    public static void main(String[] args) {
        CountGoodNodesInBinaryTree solution = new CountGoodNodesInBinaryTree();
        TreeNode node = TreeNode.TreeNodeBuilder.build(new Integer[]{3, 1, 4, 3, null, 1, 5});
        Assertions.assertEquals(4, solution.goodNodes(node));

        node = TreeNode.TreeNodeBuilder.build(new Integer[]{3,3,null,4,2});
        Assertions.assertEquals(3, solution.goodNodes(node));

        node = TreeNode.TreeNodeBuilder.build(new Integer[]{1});
        Assertions.assertEquals(1, solution.goodNodes(node));
    }
}
