package com.ice.spring.config;

import com.ice.spring.bean.Person;
import com.ice.spring.config.extend.MyTypeFilter;
import com.ice.spring.dao.PersonDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Service;

/**
 * 配置类（基于注解的方式）
 * @ClassName Config
 * @Description TODO
 * @Author liubin
 * @Date 2019/9/2 2:40 PM
 **/
//配置类
@Configuration
//配置扫描的组件包路径
//1.includeFilters配置扫描包含(useDefaultFilters置为false，防止默认直接都扫描(只要带@Component的组件都会扫描,而@service等注解也被@Component注解作用，所以都会扫描),include不生效)
//2.excludeFilters配置扫描排除(这里includeFilters不配置时，应该将useDefaultFilters置为true)
//类型：
// ANNOTATION,
//    ASSIGNABLE_TYPE,
//    ASPECTJ,
//    REGEX,
//    CUSTOM
// )
@ComponentScan(value = "com.ice.spring",includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Service.class}),@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = {PersonDao.class}),@ComponentScan.Filter(type = FilterType.CUSTOM,classes = {MyTypeFilter.class})},useDefaultFilters = false)
public class MyConfig {

    /**
     * 基于注解的方式，配置bean，这里的bean的name就是方法名(ps:可以通过@bean注解中指定bean的名称)
     * @return
     */
    @Bean("person")
    public Person person111(){
        return new Person("ice","female");
    }
}
