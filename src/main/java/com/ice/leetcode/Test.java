package com.ice.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Test
 * @Description TODO
 * @Author liubin
 * @Date 2019/4/15 7:12 PM
 **/
public class Test {
    public static void main(String[] args) {
        int[] ints = twoSumHash(new int[]{2, 3, 7, 6, 5}, 9);
        System.out.println(ints);
    }

    /**
     * 暴力解法
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        for(int i=0;i<nums.length-2;i++){
            int param1 = nums[i];
            for(int j=i+1;j<nums.length-1;j++){
                if(param1+nums[j]==target){
                    int[] result = new int[]{param1,nums[j]};
                    return result;
                }
            }
        }
        return null;
    }

    /**
     * 利用hash的快速访问
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSumHash(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(target-nums[i])&&map.get(target-nums[i])!=i){
                return new int[]{map.get(target-nums[i]),i};
            }
            map.put(nums[i],i);
        }
        throw new IllegalArgumentException("输入的参数非法");
    }
}
