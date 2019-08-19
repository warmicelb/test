package com.ice.fastjson.paging;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @author: ice
 * @create: 2019/4/9
 **/
public class Test {
    public static void main(String[] args) {
        String params = "{'page':1,'size':10}";
        GainPagingTo target = new GainPagingTo();
        BeanUtils.copyProperties(JSON.parseObject(params, target.getClass()),target);
        System.out.println(JSON.toJSONString(target));
    }
}
