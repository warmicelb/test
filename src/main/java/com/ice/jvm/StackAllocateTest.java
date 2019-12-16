package com.ice.jvm;

/**
 * 运行时设置vm参数 -server -Xmx10m -Xms10m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:+EliminateAllocations -XX:-UseTLAB
 * -server JVM运行的模式之一, server模式才能进行逃逸分析， JVM运行的模式还有mix/client
 * -Xmx10m和-Xms10m：堆的大小
 * -XX:+DoEscapeAnalysis：启用逃逸分析(默认打开)
 * -XX:+PrintGC：打印GC日志
 * -XX:+EliminateAllocations：标量替换(默认打开)
 * -XX:-UseTLAB 关闭本地线程分配缓冲
 * TLAB： ThreadLocalAllocBuffer，具体解释参见下文《虚拟机中的对象---对象的分配----2）》
 * 对栈上分配发生影响的参数就是三个，-server、-XX:+DoEscapeAnalysis和-XX:+EliminateAllocations，任何一个发生变化都不会发生栈上分配，因为启用逃逸分析和标量替换默认是打开的，所以，在我们的例子中，JVM的参数只用-server一样可以有栈上替换的效果(以Mark老师机器上JDK1.8为例，其他版本JDK请自行尝试)。
 *
 * @ClassName StackAllocateTest
 * @Description TODO 测试栈上分配
 * @Author liubin
 * @Date 2019/11/21 4:40 PM
 **/
public class StackAllocateTest {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        test();
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }

    public static void test(){
        for(int i=0;i<100000;i++){
            User user = new User("发顺丰撒打算飞洒",1L);
            System.out.println(user.toString());
        }
    }
}
