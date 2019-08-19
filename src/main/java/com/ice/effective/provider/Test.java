package com.ice.effective.provider;

/**
 * 服务提供者框架
 * @author: ice
 * @create: 2019/3/22
 **/
public class Test {
    public static void main(String[] args) {
        ServiceManager.registerDefault(new Provider() {
            @Override
            public Service getService() {
                return null;
            }
        });
        Service defaultService = ServiceManager.getDefaultService();
    }
}
