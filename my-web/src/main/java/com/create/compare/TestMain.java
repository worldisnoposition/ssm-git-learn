package com.create.compare;

import java.util.ArrayList;
import java.util.Collection;

public class TestMain {
    public int[] twoSum(int[] nums, int target) {
        for(int i=0;i<nums.length;i++){
            for(int j=i;i<nums.length;j++){
                if((nums[i]+nums[j])==target){
                    return new int[]{nums[i],nums[j]};
                }
            }
        }
        return null;
    }
    public static void main(String[] args) {
        Test1 test1 = new Test1();
        Test2 test2 = new Test2();
        System.out.println(test1.keysCache);
        System.out.println(test1.fieldsCache);
        Collection<TestBean1> newData = new ArrayList<>();
        newData.add(new TestBean1("id1","hello1.1",1));
        newData.add(new TestBean1("id2","hello2",1));
        newData.add(new TestBean1("id3","hello3",1));
        Collection<TestBean1> oldData = new ArrayList<>();
        oldData.add(new TestBean1("id0","hello0",1));
        oldData.add(new TestBean1("id1","hello1",1));
        oldData.add(new TestBean1("id2","hello2",1));
        CompareResult<TestBean1> compareResult = test1.compare2(oldData,newData);
        System.out.println(compareResult);
        System.out.println("再测测");
    }
}
