package com.ice.effective.method;

import java.util.Date;

/**
 * 防止参数被篡改，构造方法里可以进行保护性的拷贝
 * @ClassName Peroid
 * @Description TODO
 * @Author liubin
 * @Date 2019/5/9 4:42 PM
 **/
public class Peroid {
    private final Date from;

    private final Date to;

    /**
     * 这里防止篡改，可以进行保护性的拷贝
     * @param from
     * @param to
     */
    public Peroid(Date from, Date to) {
        this.from = new Date(from.getTime());
        this.to = new Date(to.getTime());
    }
}