package com.ice.effective.enums;

import lombok.Data;

import java.util.*;

/**
 * 测试用or位运算常量合并到一个集合，称作位域
 * @ClassName TestIntEnum
 * @Description TODO
 * @Author liubin
 * @Date 2019/5/7 8:29 PM
 **/
public class TestIntEnum {
    private static final int TRIANGLE = 1<<0;

    private static final int RECTANGULAR = 1<<1;

    private static final int CIRCULAR = 1<<2;

    public static void main(String[] args) {
        //通过位运算将三种或条件集中在一起
        int collection = TRIANGLE|RECTANGULAR|CIRCULAR;
        //通过位与运算即可判断是否成立
        System.out.println(collection&TRIANGLE);
    }

    /**
     * 源码很叼
     */
    public static void testEnumSet(){
        EnumSet<CalculateEnum> anAbstract = EnumSet.of(CalculateEnum.ABSTRACT);
    }

    /**
     * 使用enumMap进行枚举类型的分类（这样的好处是类型安全）
     */
    public static void testEnumMap(){
        List<Calculator> list = new ArrayList<>();
        EnumMap<CalculateEnum, Set<Calculator>> calculateEnumSetEnumMap = new EnumMap<>(CalculateEnum.class);
        for (CalculateEnum item:CalculateEnum.values()
             ) {
            calculateEnumSetEnumMap.put(item,new HashSet<>());
        }
        for (Calculator c :list
                ) {
            calculateEnumSetEnumMap.get(c.getCalculateEnum()).add(c);
        }
    }
}
@Data
class Calculator{
    private CalculateEnum calculateEnum;
}
