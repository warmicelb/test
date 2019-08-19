package com.ice.effective.enums;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

/**
 * @ClassName Test
 * @Description TODO
 * @Author liubin
 * @Date 2019/4/28 5:12 PM
 **/
public class Test {
    public static void main(String[] args) {
//        RoundingMode.CEILING
        CalculateEnum plus = CalculateEnum.valueOf("PLUS");
        System.out.println(plus);
        Long salary = SalaryDailyEnum.FRIDAY.getSalary(6, 7);
        System.out.println(salary);

        //values()返回枚举数组的拷贝（调用clone（方法）），浅拷贝
        CalculateEnum[] values = CalculateEnum.values();
        CalculateEnum[] values1 = CalculateEnum.values();
        System.out.println(values.hashCode());
        System.out.println(values1.hashCode());
        //数组的拷贝
        //一维数组：深克隆(枚举和String比较特殊)；（重新分配空间，并将元素复制过去）
        //二维数组：浅克隆。（只传递引用）
        Integer[] arr = new Integer[]{555,666,777};
        Integer[] clone = arr.clone();
        Integer[] clone3 = Arrays.copyOf(arr, arr.length);
        System.out.println(arr);
        System.out.println(clone);
        System.out.println(arr[1].hashCode());
        System.out.println(clone[1].hashCode());
        System.out.println(clone3[1].hashCode());

        String[] a = new String[]{"aaa","bbb","ccc"};
        String[] clone1 = a.clone();
        String[] clone2 = new String[a.length];
        System.arraycopy(a,0,clone2,0,a.length);
        System.out.println("string clone前后同一元素的hash值");
        System.out.println(a[1].hashCode());
        System.out.println(clone1[1].hashCode());
        System.out.println(clone2[1].hashCode());
    }
}
