package com.ice.jvm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName User
 * @Description TODO
 * @Author liubin
 * @Date 2019/11/21 6:55 PM
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    private Long id;
}
