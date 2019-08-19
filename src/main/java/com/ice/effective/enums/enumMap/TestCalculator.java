package com.ice.effective.enums.enumMap;

import com.ice.effective.enums.CalculateEnum;

import java.util.Collection;

/**
 * @ClassName TestCalculator
 * @Description TODO
 * @Author liubin
 * @Date 2019/5/8 7:53 PM
 **/
public class TestCalculator {
    public static void main(String[] args) {
        test(CalculatorEnum.class);
    }

    //方式一：传入类型令牌
    public static <T extends Enum<T> & Operation> void test(Class<T> tClass){
        for (Operation operation:tClass.getEnumConstants()
             ) {
            int calculate = operation.calculate(1, 2);
            System.out.println(calculate);
        }
    }
    //方式二：传入集合对象
    public static <T extends Enum<T> & Operation> void test(Collection<T> tCollection){
        for (Operation operation:tCollection
             ) {
            operation.calculate(1,2);
        }
    }
}
