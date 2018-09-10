package com.leetcode;

import java.util.Arrays;

public class Day10 {
    /**
     * 别人的算法，其实昨天做过一个也是从数组中取三个数的题目，怎么就没学习思想呢，这个思想对这类问题感觉非常契合
     * * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest1(int[] nums, int target) {
        int sum = nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);

        for(int i = 0; i < nums.length-2; i++)
        {
            int l = i+1, r = nums.length-1;
            while(l < r)
            {
                int curSum = nums[i] + nums[l] + nums[r];
                if(Math.abs(curSum-target) < Math.abs(sum - target))
                {
                    sum = curSum;
                }

                if(target == curSum)
                {
                    return target;
                }
                else if(target > curSum)
                {
                    l++;
                }
                else
                {
                    r--;
                }
            }
        }

        return sum;
    }
    /**
     * 求与目标值最接近的三个数的和，我的算法巨烂无比
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Integer result = null;
        int cha=Integer.MAX_VALUE;
        for(int i=0;i<nums.length-2;i++){
            for(int j=i+1;j<nums.length-1;j++){
                for(int k=j+1;k<nums.length;k++){
                    int temp = nums[i]+nums[j]+nums[k];
                    if(result==null){
                        result = temp;
                        cha=target>temp?target-temp:temp-target;
                    }else{
                        int cha1=target>temp?target-temp:temp-target;
                        if(cha>cha1){
                            result = temp;
                            cha=cha1;
                        }
                    }
                }

            }
        }
        return result;
    }
}
