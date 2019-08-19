package com.ice.effective.provider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务管理
 * --服务的提供者
 * @author: ice
 * @create: 2019/3/21
 **/
public class ServiceManager {

    private static final Map<String,Provider> providers = new ConcurrentHashMap<>();

    private static final String DEFAULT_NAME = "DEFAULT";

    public static void registerDefault(Provider provider){
        providers.put(DEFAULT_NAME,provider);
    }

    public static void register(String name,Provider provider){
        providers.put(name,provider);
    }

    public static Service getDefaultService(){
        return getService(DEFAULT_NAME);
    }

    private static Service getService(String name) {
        Provider provider = providers.get(name);
        if(provider==null){
            throw new RuntimeException("找不到对应的服务,请确保已注册");
        }
        return provider.getService();
    }

}
