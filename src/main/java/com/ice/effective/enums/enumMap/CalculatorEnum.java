package com.ice.effective.enums.enumMap;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @ClassName Calculator
 * @Description TODO
 * @Author liubin
 * @Date 2019/5/8 7:48 PM
 **/
public enum CalculatorEnum implements Operation {
    PLUS("+"){
        @Override
        public int calculate(int x, int y) {
            return x+y;
        }
    },
    ABSTRACT("-") {
        @Override
        public int calculate(int x, int y) {
            return x-y;
        }
    },
    DIVIDE("/") {
        @Override
        public int calculate(int x, int y) {
            return new BigDecimal(x).divide(new BigDecimal(y)).setScale(0, RoundingMode.HALF_UP).intValue();
        }
    },
    MULTIPLY("*") {
        @Override
        public int calculate(int x, int y) {
            return x*y;
        }
    };

    private String code;

    CalculatorEnum(String code) {
        this.code = code;
    }
}
