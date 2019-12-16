package com.ice.se;

import lombok.Data;

/**
 * @ClassName TestParentSonField
 * @Description TODO 测试子类父类属性重名时的情况
 * @Author liubin
 * @Date 2019/11/25 10:56 AM
 **/
public class TestParentSonField {
    public static void main(String[] args) {
        B b = new B();
        A a = new B();
        A aa = new A();
//        System.out.println(b.isExist());
        System.out.println(a.isExist());
        System.out.println(aa.isExist());

    }
}
@Data
class A{
    private boolean exist = false;
}
class B extends A{
    private boolean exist = true;

    @Override
    public boolean isExist() {
        System.out.println(super.isExist());
        return exist;
    }
}
