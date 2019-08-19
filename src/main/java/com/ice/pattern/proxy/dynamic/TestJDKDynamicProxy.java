package com.ice.pattern.proxy.dynamic;

/**
 * @author: ice
 * @create: 2019/3/6
 **/
public class TestJDKDynamicProxy {

    public static void main(String[] args) {
        IceCompany iceCompany = new IceCompany();
        //设置代购公司
        iceCompany.setFactory(new SManFactory());
        //寻找设置当前适合的代理
        SFactory proxy = (SFactory) iceCompany.createProxyInstance();
        //代购一波
        proxy.saleTool(1);

        //设置另一个代购公司
        iceCompany.setFactory(new SWomenFactory());
        SFactory proxy1 = (SFactory) iceCompany.createProxyInstance();
        proxy1.saleTool(222);
    }
}
