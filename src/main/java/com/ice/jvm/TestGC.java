package com.ice.jvm;

/**
 * @ClassName TestGC
 * @Description TODO 测试新生代minorGC老年代fullGC
 * @Author liubin
 * @Date 2019/11/26 11:20 AM
 **/
public class TestGC {

    /**-Xmx20m -Xms20m 设置堆大小20m,新生代大小-Xmn2m，-XX:SurvivorRatio=2设置新生代eden区和survivor大小比例为2：1：1()
     * -Xmx20m -Xms20m -XX:+PrintGCDetails -Xmn2m -XX:SurvivorRatio=2
     * @param args
     */
    public static void main(String[] args) {
        byte[] a = new byte[1024*1024];
        System.out.println("allocate");
        byte[] a1 = new byte[1024*1024];
        System.out.println("allocate");
        byte[] a2 = new byte[1024*1024];
        System.out.println("allocate");
        byte[] a3 = new byte[1024*1024];
        System.out.println("allocate");
        byte[] a4 = new byte[1024*1024];
        System.out.println("allocate");
        byte[] a5 = new byte[1024*1024];
        System.out.println("allocate");
        byte[] a6 = new byte[1024*1024];
        System.out.println("allocate");
        byte[] a7 = new byte[1024*1024];
        System.out.println("allocate");
        byte[] a8 = new byte[1024*1024];
        System.out.println("allocate");
        byte[] a9 = new byte[1024*1024];
        System.out.println("allocate");
    }
}
