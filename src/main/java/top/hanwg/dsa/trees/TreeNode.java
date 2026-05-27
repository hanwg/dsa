package top.hanwg.dsa.trees;


public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public String toString() {
        return val + "";
    }

    public static class TreeNodeBuilder {
        public static TreeNode build(Integer[] values) {

            return insertLevelOrder(values, 0);
        }

        public static TreeNode insertLevelOrder(Integer[] values, int i) {
            TreeNode root = null;
            // Base case for recursion
            if (i < values.length) {
                if (values[i] != null) {
                    root = new TreeNode(values[i]);

                    // insert left child
                    root.left = insertLevelOrder(values, 2 * i + 1);

                    // insert right child
                    root.right = insertLevelOrder(values, 2 * i + 2);
                }
            }
            return root;
        }
    }
}
