class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n=nums.length;
        int[] ans=new int[n];
        Stack<Integer> st=new Stack<>();
        //Traverse twice from right to left
        for(int i=2*n-1;i>=0;i--){

            int curr=nums[i%n];
            //Remove smaller or equal elements
            while(!st.isEmpty()&& st.peek()<=curr){
                st.pop();
            }
            //Fill answer only for first pass
            if(i<n){
                ans[i]=st.isEmpty()?-1:st.peek();
            }
            st.push(curr);
        }
        return ans;
    }
}