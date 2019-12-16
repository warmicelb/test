package com.ice.jvm;

import java.nio.ByteBuffer;

/**
 * -Xmx10m -XX:MaxDirectMemorySize=10M
 * 报错信息：Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
 * @ClassName DirectMemoryTest
 * @Description TODO 直接内存溢出测试
 * @Author liubin
 * @Date 2019/11/22 10:59 AM
 **/
public class DirectMemoryTest {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024*1024*16);
    }
}
