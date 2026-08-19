/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return createBST(nums,0,nums.length-1);
    }

public TreeNode createBST(int[]nums,int st, int end){

if(st>end){
    return null;
}
//find the middle of the array
int mid=(st+end)/2;

//created the root
TreeNode root =new TreeNode(nums[mid]);

  //creation of left node
  root.left=createBST(nums,st,mid-1);

  //creation of right node
  root.right=createBST(nums,mid+1,end);

  return root;

 }
}