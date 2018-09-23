package com.leetcode.week4;

public class Day1 {
    public int search(int[] nums, int target) {
        int result = -1,end;
        if((end=nums.length-1)<8){
            for(int i=0;i<nums.length;i++){
                if(nums[i]==target){
                    result = i;
                    break;
                }
            }
        }else{
            int start=0,mid=end/2;
            while(true){
                // System.out.println(start+"-"+mid+"-"+end);
                if(end-start<=2){
                    if(target==nums[start]){
                        result = start;
                    }else if(target==nums[end]){
                        result = end;
                    }else if(nums[mid]==target){
                        result = mid;
                    }
                    break;
                }else if(nums[mid]>nums[0]){
                    if(nums[mid]<target){
                        //右边
                        start = mid;
                        mid=(end+mid)/2;
                    }else{
                        if(target<nums[start]){
                            //右边
                            start=mid;
                            mid=(end+mid)/2;
                            mid=mid<start?start:mid;
                        }else{
                            //左边
                            end=mid;
                            mid=(start+end)/2;
                        }
                    }
                }else{
                    if(nums[mid]>target){
                        //zuo//左边
                        end=mid;
                        mid=(start+end)/2;
                    }else{
                        if(target<nums[end]){
                            //右边
                            start=mid;
                            mid=(end+mid)/2;
                            mid=mid>end?end:mid;
                        }else{
                            //左边
                            end=mid;
                            mid=(start+end)/2;
                        }
                    }

                }
            }
        }
        return result;
    }
}
