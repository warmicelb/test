package com.ice.effective.method;

/**
 * @ClassName Play
 * @Description TODO
 * @Author liubin
 * @Date 2019/5/9 5:30 PM
 **/
public class Play {

    public static void main(String[] args) {

    }

    /**
     * 这里只需要传递一个参数card
     * @param card
     */
    public static void testParam(Card card){
        System.out.println(card.color+card.number);
    }

    /**
     * 内部类，封装纸牌的属性
     */
    public static class Card{
        private String color;
        private Integer number;

    }
}
