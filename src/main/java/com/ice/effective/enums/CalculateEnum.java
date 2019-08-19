package com.ice.effective.enums;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用javac，javap查看编译后的类信息（public abstract class CalculateEnum 以及对应实现类）
 * 针对枚举的方法，每个实例表现不同的行为实现。
 * @ClassName CalculateEnum
 * @Description TODO
 * @Author liubin
 * @Date 2019/4/28 5:32 PM
 **/
public enum CalculateEnum {
    PLUS("+"){
        @Override
        int calculate(int x, int y) {
            return x+y;
        }
    },
    ABSTRACT("-") {
        @Override
        int calculate(int x, int y) {
            return x-y;
        }
    },
    DIVIDE("/") {
        @Override
        int calculate(int x, int y) {
            return new BigDecimal(x).divide(new BigDecimal(y)).setScale(0, RoundingMode.HALF_UP).intValue();
        }
    },
    MULTIPLY("*") {
        @Override
        int calculate(int x, int y) {
            return x*y;
        }
    };
    //注意顺序，这个在上述实例生成之前，所以构造器也是无法访问定义的静态域的
    private static final Map<String ,CalculateEnum> typeEnums = new HashMap<>(16);

    static {
        for(CalculateEnum type:CalculateEnum.values()){
            typeEnums.put(type.code,type);
        }
    }
    /**
     * 算法标识符
     */
    String code;

    CalculateEnum(String code) {
        this.code = code;
    }

    abstract int calculate(int x, int y);

    //统计静态域typeEnumsMap集合，可以快速查询出对应的枚举值
    public static CalculateEnum codeOf(String code){
        return typeEnums.get(code);
    }

}
