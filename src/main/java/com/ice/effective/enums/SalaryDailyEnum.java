package com.ice.effective.enums;

/**
 *
 * @ClassName SalaryDailyEnum
 * @Description TODO 计算一周中某天的工资（正常工资和加班工资）
 * @Author liubin
 * @Date 2019/4/30 9:55 AM
 **/
public enum SalaryDailyEnum {
    MONDAY(PayType.WEEKDAY),
    TUESDAY(PayType.WEEKDAY),
    WEDNESDAY(PayType.WEEKDAY),
    THURSDAY(PayType.WEEKDAY),
    FRIDAY(PayType.WEEKDAY),
    SATURDATY(PayType.WEEKEND),
    SUNDAY(PayType.WEEKEND);

    private PayType payType;

    SalaryDailyEnum(PayType payType) {
        this.payType = payType;
    }

    public Long getSalary(long hours,long prize){
        return payType.pay(hours,prize);
    }

    /**
     * 计算方式，工作日或者周末
     */
    private enum PayType{
        WEEKDAY{
            @Override
            public long overTimePay(Long hours, Long prize) {
                return hours>NORMAL_HOURS?(hours-NORMAL_HOURS)*prize/2:0;
            }
        },
        WEEKEND {
            @Override
            public long overTimePay(Long hours, Long prize) {
                return hours*prize/2;
            }
        };

        private static final int NORMAL_HOURS = 8;

        private Long pay(Long hours,Long prize){
            return hours*prize+overTimePay(hours,prize);
        }

        public abstract long overTimePay(Long hours, Long prize);


    }
}
