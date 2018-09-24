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
        int[] res = {-1,-1};
        int result1 = -1;
        return result;
    }
    public int[] searchRange(int[] nums, int target) {
        int result = -1;
        int end=nums.length-1;
        int[] res = {-1,-1};
        if(end<8){
            for(int i=0;i<nums.length;i++){
                if(nums[i]==target){
                    if(res[0]==-1){
                        res[0]=i;
                        res[1]=i;
                    }else{
                        res[1]=i;
                    }
                }
            }
        }else{
            int start=0,mid=end/2;
            while(true){
                // System.out.println(start+"-"+mid+"-"+end);
                if(target==nums[mid]){
                    result = mid;
                    break;
                }else if(end-start<=2){
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
            if(result==-1){
                return res;
            }
            res[0]=result;
            res[1]=result;
            start=result;
            end=result;
            while(true){
                boolean opt = false;
                if(start>=0&&nums[start]==target){
                    res[0]=start;
                    start--;
                    opt=true;
                }
                if(end<nums.length&&nums[end]==target){
                    res[1]=end;
                    end++;
                    opt=true;
                }
                if(!opt){
                    break;
                }
            }
        }
        return res;
    }
}

/**
 * 别人的答案就很好了，优雅又简洁，
 * 但我觉得findLast方法其实还可以再改进下，可以不从0开始，而是从start开始
 */
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) return new int[]{-1,-1};

        int first = findFirst(nums, target);
        int last = findLast(nums, target);

        return new int[]{first, last};
    }

    private int findFirst(int[] nums, int target) {
        int start = 0;
        int end = nums.length-1;

        while(start+1<end) {
            int mid = start+(end-start)/2;
            if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (nums[start] == target) {
            return start;
        } else if (nums[end] == target) {
            return end;
        }

        return -1;
    }

    private int findLast(int[] nums, int target) {
        int start = 0;
        int end = nums.length-1;

        while(start+1<end) {
            int mid = start+(end-start)/2;
            if (nums[mid] <= target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (nums[end] == target) {
            return end;
        } else if (nums[start] == target) {
            return start;
        }

        return -1;
    }
}
