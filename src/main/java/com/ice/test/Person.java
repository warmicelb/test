package com.ice.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName Person
 * @Description TODO
 * @Author liubin
 * @Date 2019/5/29 5:28 PM
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = false, chain = true, prefix = "")
public class Person {
    private String name;
    private Integer id;
    private String pwd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
