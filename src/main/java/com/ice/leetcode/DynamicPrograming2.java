package com.ice.leetcode;

/**
 * 动态规划（这类问题都可以从起始位置，选和不选两种情况考虑）
 * 计算一个数组中，一组子数组的和能组成一个数字
 * @ClassName DynamicPrograming
 * @Description TODO
 * @Author liubin
 * @Date 2019/12/17 9:58 AM
 **/
public class DynamicPrograming2 {
    private int[] nums =new int[]{2,5,4};
    private boolean[][] result = new boolean[nums.length][10];
    public static void main(String[] args) {
        DynamicPrograming2 entity = new DynamicPrograming2();
//        boolean b = entity.hasSubArray(3,16);
//        System.out.println(b);
        entity.hasSubArrayDb();
        for (boolean[] booleans : entity.result) {
            for (boolean aBoolean : booleans) {
                System.out.print(aBoolean+" ");
            }
            System.out.println();
        }
    }

    /**
     *
     * @param index
     * @param sum
     * @return
     */
    public boolean hasSubArray(int index,int sum){
        if(index==0){
            return nums[0]==sum;
        }
        if(nums[index]==sum){
            return true;
        }
        if(sum<0){
            return false;
        }
        return hasSubArray(index-1,sum-nums[index])||hasSubArray(index-1,sum);
    }


    /**
     * 存储计算结果
     * ps:这里存储
     * @return
     */
    public void hasSubArrayDb(){
        for(int i=0;i<10;i++){
            result[0][i]=nums[0]==i;
        }
        for(int m=1;m<nums.length;m++){
            for(int n=0;n<10;n++){
                if(nums[m]==n){
                    result[m][n]=true;
                }else if(nums[m]>n) {
                    result[m][n] = result[m-1][n];
                }else{
                    result[m][n] = result[m-1][n-nums[m]]||result[m-1][n];
                }

            }
        }
    }

}
