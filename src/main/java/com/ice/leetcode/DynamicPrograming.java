package com.ice.leetcode;

/**
 * 动态规划
 * 计算一个数组中，不相邻数字能组成的最大的和（比如这里是9，4，1组成最大和）
 * @ClassName DynamicPrograming
 * @Description TODO
 * @Author liubin
 * @Date 2019/12/17 9:58 AM
 **/
public class DynamicPrograming {
    private int[] nums =new int[]{1,1,9,1,4,3,1};
    private int[] result = new int[nums.length];
    public static void main(String[] args) {
        DynamicPrograming entity = new DynamicPrograming();
        int sum = entity.maxSum(entity.nums.length);
        System.out.println(sum);
        //计算结果存储，空间换时间
        entity.maxSumDB(entity.nums.length);
        for (int i : entity.result) {
            System.out.println(i);
        }
//        System.out.println(entity.result[entity.result.length-1]);
    }

    /**
     * 这里运用递归调用实现
     * @param length
     * @return
     */
    public int maxSum(int length){
        if(length==1){
            return nums[0];
        }
        if(length==2){
            return Math.max(nums[1],nums[0]);
        }
        return Math.max(nums[length-1]+maxSum(length-2),maxSum(length-1));
    }

    /**
     * 这里直接计算结果存储起来,后面调用直接可以取结果（在过程中，会出现重复子问题，这时候将结果存储，可以节省时间）
     * @param length
     * @return
     */
    public void maxSumDB(int length){
        result[0] = nums[0];
        result[1] = Math.max(nums[1],nums[0]);
        for(int i=2;i<length;i++){
            result[i] = Math.max(nums[i]+result[i-2],result[i-1]);
        }
    }
}
