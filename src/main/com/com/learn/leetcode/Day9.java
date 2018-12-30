package com.learn.leetcode;

import java.util.*;

public class Day9 {
    /**
     * 这么简单居然是个中级题，我写的也是够丑，够暴力
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        String result = "";
        while(num>0){
            if(num>=1000){
                num-=1000;
                result+="M";
            }else if(num>=900){
                num-=900;
                result+="CM";
            }else if(num>=500){
                num-=500;
                result+="D";
            }else if(num>=400){
                num-=400;
                result+="CD";
            }else if(num>=100){
                num-=100;
                result+="C";
            }else if(num>=90){
                num-=90;
                result+="XC";
            }else if(num>=50){
                num-=50;
                result+="L";
            }else if(num>=40){
                num-=40;
                result+="XL";
            }else if(num>=10){
                num-=10;
                result+="X";
            }else if(num>=9){
                num-=9;
                result+="IX";
            }else if(num>=5){
                num-=5;
                result+="V";
            }else if(num>=4){
                num-=4;
                result+="IV";
            }else if(num>=1){
                num-=1;
                result+="I";
            }
        }
        return result;
    }

    /**
     * 这个初级题，我觉得比上一道中级题要难
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        String[] dic = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        int[] dicInt = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        int i = 0 ;
        int result = 0;
        while(true){
            // System.out.println(result+"-"+s);
            if(s.indexOf(dic[i])!=0){
                i++;
            }else{
                result+=dicInt[i];
                if(i%2==1){
                    if(s.length()==2){
                        break;
                    }
                    s=s.substring(2);
                }else{
                    if(s.length()==1){
                        break;
                    }
                    s=s.substring(1);
                }
            }
        }
        return result;
    }

    /**
     * 做题时不知怎么，脑子很不清楚，还是个初级题
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if(strs.length==0||strs[0].length()==0){
            return "";
        }
        String temp=strs[0];
        String result =temp.charAt(0)+"";
        int j = 0;
        for(int i=0;j<=temp.length();i++){
            // System.out.println("++++"+i+"-"+j+result);
            if(j==0){
                temp=temp.length()<strs[i].length()?temp:strs[i];
            }
            if(strs[i].indexOf(result)!=0){
                if(j==0){
                    return "";
                }
                result = temp.substring(0,j-1);
                // System.out.println("+++"+i+"-"+j);
                break;
            }
            if(i==strs.length-1){
                i=-1;
                j++;
                if(j>temp.length()){
                    // System.out.println(""+i+"-"+j+result);
                    break;
                }
                result = temp.substring(0,j);
            }
            // System.out.println(result);
        }
        return result;
    }

    /**
     * 别人家的答案，顺便学习了一个Ararys.sort函数
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length-2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            if (i > 0 && nums[i] == nums[i - 1])
                continue; // 去掉重复的起点
            while (left < right) {
                int sum = nums[left] + nums[right] + nums[i];
                if (sum == 0) {
                    result.add(Arrays.asList(new Integer[]{nums[i], nums[left], nums[right]}));
                    while (left < right && nums[left] == nums[left + 1])
                        left++; // 去掉重复的左点
                    while (left < right && nums[right] == nums[right - 1])
                        right--; // 去掉重复的右点
                    right--; // 进入下一组左右点判断
                    left++;
                } else if (sum > 0) {
                    right--; // sum>0 ,说明和过大了，需要变小，所以移动右边指针
                } else {
                    left++; // 同理，需要变大，移动左指针
                }
            }
        }
        return result;
    }

    /**
     * 中级题我居然没做出来，我的答案超时了，这个答案确实精巧，先排序，再用两个指针从左右向中间移动，减少比较次数
     * @param nums
     * @return
     */
     public List<List<Integer>> threeSum1(int[] nums) {
         if(nums.length==0){
             return new ArrayList();
         }
         int[][] temp = new int[nums.length-1][nums.length-1];
         Map<Integer,Node> map = new HashMap();
         List<List<Integer>> result = new ArrayList();
         for(int i=0;i<nums.length-1;i++){
             for(int j=i+1;j<nums.length;j++){
                 int key = nums[i]+nums[j];
                 Node node;
                 if((node=map.get(key))==null){
                     map.put(nums[i]+nums[j],nums[i]<nums[j]?new Node(i,j):new Node(j,i));
                 }else{
                     Node node1 = nums[i]<nums[j]?new Node(i,j):new Node(j,i);
                     node1.next = node;
                     map.put(key,node1);
                 }
             }
         }
         // System.out.println(map);
         for(int k=0;k<nums.length;k++){
             Node node;
             if((node=map.get(-nums[k]))!=null){
                 while(node!=null){
                     if(k<node.i&&k<node.j){
                         // System.out.print(k+"-"+node.i+"-"+node.j);
                         List<Integer> toAdd = new ArrayList();
                         if(nums[k]<nums[node.i]){
                             toAdd.add(nums[k]);
                             toAdd.add(nums[node.i]);
                             toAdd.add(nums[node.j]);
                         }else if(nums[k]>nums[node.j]){
                             toAdd.add(nums[node.i]);
                             toAdd.add(nums[node.j]);
                             toAdd.add(nums[k]);
                         }else{
                             toAdd.add(nums[node.i]);
                             toAdd.add(nums[k]);
                             toAdd.add(nums[node.j]);
                         }
                         if(!result.contains(toAdd)){
                             result.add(toAdd);
                         }
                     }
                     node=node.next;
                 }
             }
         }
         return result;
     }
     class Node{
         int i;
         int j;
         public Node(int i,int j){
             this.i=i;
             this.j=j;
         }
         Node next;
         public String toString(){
             return "{i="+i+",j="+j+",next="+this.next+"}";
         }
     }
}
