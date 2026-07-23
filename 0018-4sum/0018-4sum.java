import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>>result=new ArrayList<>();
        if(nums==null || nums.length<4){
            return result;
        }
        Arrays.sort(nums);
        int n=nums.length;
        for(int i=0;i<n-3;i++){
            if(i>0 && nums[i]== nums[i-1]){
                continue;
            }
            for(int j=i+1;j<n-2;j++){
                if(j>i+1&&nums[j]==nums[j-1]){
                    continue;
                }
                int Left=j+1;
                int right=n-1;
                while(Left<right){
                    long sum=(long)nums[i]+nums[j]+nums[Left]+nums[right];
                    if(sum==target){
                        result.add(Arrays.asList(nums[i],nums[j],nums[Left],nums[right]));
                        while(Left<right && nums[Left]==nums[Left+1]){
                            Left++;
                        }
                        while(Left<right && nums[right]==nums[right-1]){
                            right--;
                        }
                        Left++;
                        right--;
                    }else if(sum<target){
                        Left++;
                    }else{
                        right--;
                    }
                }
            }
        }
        return result;
    }
}