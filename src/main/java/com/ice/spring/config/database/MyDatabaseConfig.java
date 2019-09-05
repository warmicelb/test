package com.ice.spring.config.database;

import com.ice.spring.dao.PersonDao;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;
import java.beans.PropertyVetoException;

/**
 * @ClassName MyDatabaseConfig
 * @Description TODO
 * @Author liubin
 * @Date 2019/9/5 7:21 PM
 **/
@Configuration
@Import(PersonDao.class)
/**
 * 开启事物支持的注解
 */
@EnableTransactionManagement
public class MyDatabaseConfig {

    /**
     * 配置数据源
     * @return
     * @throws PropertyVetoException
     */
    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("abcd1234");
        comboPooledDataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://47.75.240.173:3306/practice?useUnicode=true&characterEncoding=utf8&useSSL=false&useOldAliasMetadataBehavior=true&autoReconnect=true&serverTimezone=GMT%2B8&zeroDateTimeBehavior=CONVERT_TO_NULL");
        return comboPooledDataSource;
    }

    /**
     * 配置jdbcTemplate
     * @return
     * @throws PropertyVetoException
     */
    @Bean
    public JdbcTemplate jdbcTemplate() throws PropertyVetoException {
        return new JdbcTemplate(dataSource());
    }

    /**
     * 注册事务管理器
     * @return
     * @throws PropertyVetoException
     */
    @Bean
    public PlatformTransactionManager transactionManager() throws PropertyVetoException {
        return new DataSourceTransactionManager(dataSource());
    }

}
