package com.ice.spring.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName Person
 * @Description TODO
 * @Author liubin
 * @Date 2019/9/2 2:25 PM
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {
    private String name;
    private String sex;
}
