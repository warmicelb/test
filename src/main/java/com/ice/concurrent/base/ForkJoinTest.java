package com.ice.concurrent.base;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 分而治之的思想
 * 测试java fork/join框架--同步等待返回结果
 * @ClassName ForkJoinTest
 * @Description TODO
 * @Author liubin
 * @Date 2019/7/2 10:53 AM
 **/
public class ForkJoinTest {
    public static void main(String[] args) {
        int[] nums = generateArray(100000);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        long start = System.currentTimeMillis();
        CalculateTask calculateTask = new CalculateTask(nums, 0, nums.length);
        forkJoinPool.invoke(calculateTask);
        //这里调用join让task先执行
        Long sum = calculateTask.join();
        long end = System.currentTimeMillis();
        System.out.println("fork/join执行时间："+(end-start)+"ms,执行结果:"+sum);
        long start1 = System.currentTimeMillis();
        Long sum1 = sumSingle(nums);
        long end1 = System.currentTimeMillis();
        System.out.println("single执行时间："+(end1-start1)+"ms,执行结果:"+sum1);
    }
    public static int[] generateArray(int size){
        Random random = new Random();
        int[] nums = new int[size];
        for(int i=0;i<size;i++){
            nums[i] = random.nextInt(size*3);
        }
        return nums;
    }
    public static Long sumSingle(int[] nums){
        Long sum =0L;
        for(int i=0;i<nums.length;i++){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sum+=nums[i];
        }
        return sum;
    }
}
class CalculateTask extends RecursiveTask<Long>{

    private int[] nums;
    private int from;
    private int to;

    public CalculateTask(int[] nums, int from, int to) {
        this.nums = nums;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Long compute() {
        //这里以1000个为计算单位，如果大于一千，则进行拆分
        if(to-from>1000){
            CalculateTask leftPart = new CalculateTask(nums, from, (to+from)/2);
            CalculateTask rigthPart = new CalculateTask(nums, (to+from)/2, to);
            invokeAll(leftPart,rigthPart);
            return leftPart.join()+rigthPart.join();
        }else{
            Long sum =0L;
            for(int i=from;i<to;i++){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sum+=nums[i];
            }
            return sum;
        }
    }
}
