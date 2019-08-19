package com.ice.effective.enums;

/**
 * @ClassName Phrase
 * @Description TODO
 * @Author liubin
 * @Date 2019/5/8 10:48 AM
 **/
public enum Phase {
    SOLID,LIQUID,GAS;
    public enum Transition{
        MELT,FREEZE,BOIL,CONDENSE,SUBLIME,DEPOSIT;

        public static final Transition[][] TRANSITIONS ={{null,MELT,SUBLIME},{FREEZE,null,BOIL},{DEPOSIT,CONDENSE,null}};

        /**
         * 通过传入前后状态，获取是否存在转换过程
         * @param from
         * @param to
         * @return
         */
        public static Transition from(Transition from,Transition to){
            return TRANSITIONS[from.ordinal()][to.ordinal()];
        }
    }
}
