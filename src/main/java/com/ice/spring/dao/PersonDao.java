package com.ice.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName PersonDAO
 * @Description TODO
 * @Author liubin
 * @Date 2019/9/2 3:08 PM
 **/
@Repository
public class PersonDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 事物注解
     */
    @Transactional
    public void select(){
        jdbcTemplate.update("update user set status=status+1 where id=1");
        System.out.println(1/0);
    }
}
