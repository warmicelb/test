package com.ice.leetcode;

/**
 * 在由若干 0 和 1  组成的数组 A 中，有多少个和为 S 的非空子数组。
 *
 *  
 *
 * 示例：
 *
 * 输入：A = [1,0,1,0,1], S = 2
 * 输出：4
 * 解释：
 * 如下面黑体所示，有 4 个满足题目要求的子数组：
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 *  
 *
 * 提示：
 *
 * A.length <= 30000
 * 0 <= S <= A.length
 * A[i] 为 0 或 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-subarrays-with-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ClassName SubarraysWithSum
 * @Description TODO
 * @Author liubin
 * @Date 2019/12/16 4:57 PM
 **/
public class SubarraysWithSum {

    public static void main(String[] args) {
        int[] nums = {0, 1, 1, 0, 0, 1, 0, 1};
        int sum = 2;
        numSubarraysWithSum(nums,sum);
    }

    public static int numSubarraysWithSum(int[] arr, int sum) {
        int result = 0;
        for(int i=0;i<arr.length;i++){
            int curr = 0;
            for(int j=i;j<arr.length;j++){
                curr+=arr[j];
                if(curr<sum){
                    continue;
                }else if(curr==sum){
                    result++;
//                    getArray(i,j);
                }else{
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 根据数组起始下标获取子数组
     * @param start
     * @param end
     */
    public static void getArray(int start,int end){
        System.out.println("起始位置:"+start+",结束位置:"+end);
    }
}
