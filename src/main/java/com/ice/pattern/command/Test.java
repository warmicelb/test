package com.ice.pattern.command;

/**
 * 模拟客户端调用
 * @author: ice
 * @create: 2019/3/29
 **/
public class Test {
    public static void main(String[] args) {
        //将handler调用转化为command调用（抽象接口）。
        Command command = new HotCommand(new HotHandler());
        //客户端只需要执行统一的调用方式，即通过command对象调用execute方法。
        command.execute();
    }
}
