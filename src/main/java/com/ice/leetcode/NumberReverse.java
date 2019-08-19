package com.ice.leetcode;

import java.math.BigDecimal;

/**
 * @ClassName NumberReverse
 * @Description TODO
 * @Author liubin
 * @Date 2019/6/21 4:13 PM
 **/
public class NumberReverse {
    public static void main(String[] args) {
        String rate = "0.00011%";
        System.out.println(getDayRate(rate,"day"));
    }
    public void reverseNum(){

    }

    public static String getDayRate(String rate, String rateType) {
        String returnVal = "";
        rate = rate.replace("%", "").replace(" ", "").replace("％", "").replace("‰", "");
        try {
            BigDecimal bigDecimal = new BigDecimal(rate);
            if(rateType.equalsIgnoreCase("day")){
                bigDecimal = bigDecimal.multiply(new BigDecimal(10000));
            }
            if (rateType.equalsIgnoreCase("month")) {
                bigDecimal = bigDecimal.multiply(new BigDecimal(10000)).divide(new BigDecimal(30), BigDecimal.ROUND_DOWN);
            }
            if (rateType.equalsIgnoreCase("year")) {
                bigDecimal = bigDecimal.multiply(new BigDecimal(10000)).divide(new BigDecimal(365), BigDecimal.ROUND_DOWN);
            }
            returnVal =  bigDecimal + "";
            int index = returnVal.lastIndexOf(".");
            return returnVal.substring(0,index>0?index:returnVal.length());
        } catch (Exception e) {
            return new BigDecimal(0L) + "";
        }
    }
}
