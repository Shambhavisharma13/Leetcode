class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int curSum = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            // Either extend the previous subarray or start fresh at nums[i]
            curSum = Math.max(nums[i], curSum + nums[i]);
            maxSum = Math.max(maxSum, curSum);
        }
        
        return maxSum;
    }
}