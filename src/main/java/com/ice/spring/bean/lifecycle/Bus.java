package com.ice.spring.bean.lifecycle;

import lombok.Data;
import lombok.ToString;

/**
 * @ClassName Daughter
 * @Description TODO
 * @Author liubin
 * @Date 2019/9/2 4:40 PM
 **/
@Data
@ToString
public class Bus {
    public Bus() {
        System.out.println("这里创建了一个Bus对象");
    }

    public void init(){
        System.out.println("这里调用Bus的初始化方法");
    }

    public void destroy(){
        System.out.println("这里调用Bus的销毁方法");
    }
}
