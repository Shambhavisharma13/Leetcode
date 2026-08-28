class Solution {
    public int numTrees(int n) {

        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = 1;

        for (int nodes = 2; nodes <= n; nodes++) {

            for (int left = 0; left < nodes; left++) {

                int right = nodes - 1 - left;

                dp[nodes] += dp[left] * dp[right];
            }
        }

        return dp[n];
    }
}