package com.ice.reg;

/**
 * @ClassName RegTest
 * @Description TODO
 * @Author liubin
 * @Date 2019/9/5 10:43 AM
 **/
public class RegTest {
    public static void main(String[] args) {
        testRemoveInvisible();
    }

    /**
     * 去除不可见字符
     */
    public static void testRemoveInvisible(){
        String phone = "15859700119\u202C";
        System.out.println(phone.length());
        String all = phone.replaceAll("\\p{C}", "");
        System.out.println(all.length());
        String t  = phone.replace("\\p{C}", "");
        System.out.println(t.length());
    }
}
