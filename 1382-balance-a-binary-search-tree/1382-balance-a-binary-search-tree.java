class Solution {

    // Store inorder traversal
    public void getInorder(TreeNode root, ArrayList<Integer> inorder) {

        if (root == null) {
            return;
        }

        getInorder(root.left, inorder);

        inorder.add(root.val);

        getInorder(root.right, inorder);
    }

    // Create balanced BST from sorted ArrayList
    public TreeNode createBST(ArrayList<Integer> inorder, int start, int end) {

        if (start > end) {
            return null;
        }

        int mid = start + (end - start) / 2;

        TreeNode root = new TreeNode(inorder.get(mid));

        root.left = createBST(inorder, start, mid - 1);

        root.right = createBST(inorder, mid + 1, end);

        return root;
    }

    public TreeNode balanceBST(TreeNode root) {

        // Step 1: Get sorted inorder
        ArrayList<Integer> inorder = new ArrayList<>();

        getInorder(root, inorder);

        // Step 2: Create balanced BST
        return createBST(inorder, 0, inorder.size() - 1);
    }
}