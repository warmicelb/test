package com.ice.leetcode;

/**
 * 一个整型数组里除了两个数字外，其它数字都出现了两次，请找出这两个只出现一次的数字，要求时间复杂度是O（n），空间复杂度是O（1）。比如输入数组：2,4,3,6,3,2,5,5，输出为4和6，因为只有4和6只出现一次，其它数字都出现了两次。
 * https://blog.csdn.net/MOU_IT/article/details/88836681
 * @ClassName UniqueNumber
 * @Description TODO
 * @Author liubin
 * @Date 2019/10/29 5:50 PM
 **/
public class UniqueNumber {

    public static void main(String[] args) {
//        int[] nums = {1,1,3,4,5,4,6,8,8,3};
//        unique2Num(nums);
        int[] nums2 =
                {4,4,5,4,6,8,6,5,6,5};
        uniqueNum(nums2);
    }

    /**
     * Q1
     *     可以先考虑这样一个问题：数组中只有一个数字出现了一次，而其它数字都出现了两次，那么怎么找出这个数字？我们想到了异或运算的性质：任何一个数字异或它自己都等于0，任何一个数字和0异或等于它本身。也就是说如果我们从头到尾一次异或数组中的每个数字，那么最终的结果刚好是那个只出现一次的数字，因为那些成对出现的数字在数组中全部在异或中抵消了。
     *     现在我们考虑原始问题，我们从头到尾异或每个数字，那么最终得到的结果是数组中两个只出现一次的数字异或的结果，因为其它数字在数组中都出现了两次，全部抵消了。由于这两个数组肯定不相同，那么它们的以后值肯定不为0，在这个结果的二进制中肯定有一个位为1。我们找出这个为1位的位置，把它记为n。现在我们以第n为是否为1把原数组分为两个数组，第一个子数组中所有元素的第n位为1，第二个数组中第n位为0。这样划分以后，出现了两次的数字肯定被划分到一个子数组，而每个子数组都包含一个只出现一次的数字。这样划分之后，我们对每个子数组的所有元素求异或运算，得到的结果就是两个只出现一次的数字。
     */
    public static void unique2Num(int[] nums){
        int result = 0;
        for(int i=0;i<nums.length;i++){
            result = result^nums[i];
        }
        int sep = (int) Math.pow(2, findIndex(result));
        int num1 =0,num2=0;
        for(int i=0;i<nums.length;i++){
            if((nums[i]&sep)>0){
                num1 = num1^nums[i];
            }else{
                num2 = num2^nums[i];
            }
        }
        System.out.println("仅出现一次的两个数字分别为:num1:"+num1+",num2:"+num2);
    }

    private static int findIndex(int result) {
        int index = 0;
        int temp = result&1;
        while (temp!=1){
            result = result >> 1;
            temp = result&1;
            index++;
        }
        return index;
    }
// Q2    扩展在一个数组中除了一个数字出现一次外，其它数字都出现了三次，请找出那个只出现一次的数字。这个题目就不能用异或运算的思路来解决了，因为出现三次的数字异或之后结果还是为其本身，但是我们还是可以用二进制位的思路解决这个问题。因为数组中出了一个数字以外，其它数字都出现了三次，我们可以考虑把所有的数对应的二进制位加起来，如果某一位的二进制和能被三整除，那说明只出现一次的数字的这个二进制位为0，如果不能被三整除，那说明只出现一次的数字的这个二进制位为1。

    /**
     * 这里考虑是正整数的情况，如果是负数，直接循环32次（int4个字节的bit数）
     * @param nums
     */
    public static void uniqueNum(int[] nums){
        int max = findMax(nums);
        int length = (int) (Math.log(max)/Math.log(2))+1;
        int result = 0;
        for(int i =0;i<length;i++){
            int bitNum = 0;
            for (int num : nums) {
                bitNum+=(num>>i)&1;
            }
            result+=bitNum%3==1?Math.pow(2,i):0;
        }
        System.out.println("只出现一次的数字为:"+result);
    }

    private static int findMax(int[] nums) {
        int max = 0;
        for (int num : nums) {
            if(num>max){
                max= num;
            }
        }
        return max;
    }
}
