package com.ice.base;

/**
 * @ClassName Test
 * @Description TODO
 * @Author liubin
 * @Date 2019/10/28 10:08 AM
 **/
public class Test {
    private int age =10;
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public static void main(String[] args) {
        Test a = new A();
        Test test = new Test();
        System.out.println(a.getAge());
        System.out.println(test.getAge());
    }

}

/**
 * 这里如果子类定义个父类已有的字段，当定义子类实例时，返回的是子类实例的字段（又实际实例类型决定）
 */
class A extends Test{
    private int age = 20;

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }
}
