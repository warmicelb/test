package com.ice.spring.config;

import com.ice.spring.bean.Father;
import com.ice.spring.bean.Person;
import com.ice.spring.config.extend.MyFactoryBean;
import com.ice.spring.config.extend.MyImportBeanDefinitionRegistrar;
import com.ice.spring.config.extend.MyImportSelector;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @ClassName MyImportConfig
 * @Description TODO
 * @Author liubin
 * @Date 2019/9/2 5:38 PM
 **/
@Configuration
//这里通过import注解导入类Son并注入实例(@import可以传入多个class)
//也可以指定ImportSelector的实现类定制化去导入自定的类到容器
@Import({Person.class, MyImportSelector.class,MyImportBeanDefinitionRegistrar.class})
public class MyImportConfig {

    /**
     * 给容器注册bean的方式：
     * 1.类上加上@Bean（下面是例子）
     * 2.包扫描加组件标注注解（@ComponentScan：@Controller，@Service，@Repository，@Component）
     * 3.@import注解，
     * a.可以给容器导入一个组件，导入的bean的id为全类名：:导入第三方的类或jar包时，需要在ioc容器中使用
     * b.也可以通过ImportSelector接口,需要返回导入到容器的全类名数组（添加到@Import注解中）
     * c.也可以通过ImportBeanDefinitionRegistrar：可以手动注册添加bean到ioc容器
     * 4.使用Spring提供的FactoryBean（工厂Bean）进行注册
     * @return
     */
    @Bean
    public Person person(){
        return new Person("importPerson","female");
    }

    /**
     *  通过FactoryBean进行bean的注册
     * @return
     */
    @Bean
    public FactoryBean<Father> father(){
        return new MyFactoryBean();
    }
}
