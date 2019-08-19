package com.ice.effective.enums.enumMap;

import javax.security.auth.callback.Callback;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * 用enum代替序数索引（两重序数索引）
 * @ClassName Phrase
 * @Description TODO
 * @Author liubin
 * @Date 2019/5/8 10:48 AM
 **/
public enum Phase {
    SOLID,LIQUID,GAS;
    public enum Transition{
        MELT(SOLID,LIQUID),FREEZE(LIQUID,SOLID),BOIL(LIQUID,GAS),CONDENSE(GAS,LIQUID),SUBLIME(SOLID,GAS),DEPOSIT(GAS,SOLID);

        private static Map<Phase, Map<Phase,Transition>> transitions = new EnumMap(Phase.class);

        static {
            for (Phase phase:Phase.values()
            ) {
                transitions.put(phase,new EnumMap<>(Phase.class));
            }
            for (Transition transition:Transition.values()
                 ) {
                transitions.get(transition.from).put(transition.to,transition);
            }
        }

        private Phase from;
        private Phase to;

        Transition(Phase from, Phase to) {
            this.from = from;
            this.to = to;
        }

        /**
         * 通过传入前后状态，获取是否存在转换过程
         * @param from
         * @param to
         * @return
         */
        public static Transition from(Phase from,Phase to){
            return transitions.get(from).get(to);
        }
    }

}
