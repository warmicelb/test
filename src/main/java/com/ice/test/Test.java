package com.ice.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @ClassName Test
 * @Description TODO
 * @Author liubin
 * @Date 2019/5/27 11:37 AM
 **/
public class Test {
    public static void main(String[] args) {
        String s = "https://activity.kongapi.com/zxcx_channel/#/?id=%d&channelRemark=%s";
        String s1 = s.replaceAll("(.*?)(id=.*)", "http://172.100.8.7:8081/#/$2");
        System.out.println(s1);
        class Rap{
            private String name;
            private Integer remitId;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Integer getRemitId() {
                return remitId;
            }

            public void setRemitId(Integer remitId) {
                this.remitId = remitId;
            }

            public Rap(String name, Integer remitId) {
                this.name = name;
                this.remitId = remitId;
            }
        }
        List<Rap> list = new ArrayList<>();
        list.add(new Rap("aa",1));
        list.add(new Rap("bb",2));
        list.add(new Rap("cc",1));
        list.add(new Rap("dd",null));
        Map<Integer, List<Rap>> aaa = list.stream().collect(Collectors.groupingBy(Rap::getRemitId));
        System.out.println("结束");
    }
    private static String getNonce(Long userDeviceAdId) {
        StringBuilder sb = new StringBuilder(userDeviceAdId.toString());
        for(int i=32-sb.length();i>0;i--){
            Random random = new Random();
            char r = (char) (random.nextInt(26)+65);
            int index = random.nextInt(sb.length()-1);
            sb.replace(index,index,new String(new char[]{r}));
        }
        return sb.toString();
    }

}
