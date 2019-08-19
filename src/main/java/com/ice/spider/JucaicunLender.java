package com.ice.spider;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

/**
 * @ClassName JucaicunLender
 * @Description TODO
 * @Author liubin
 * @Date 2019/8/1 2:52 PM
 **/
@Data
@AllArgsConstructor
public class JucaicunLender {
    private String nickName;
    private String company;
    private String province;
    private String city;
    private String addr;
    String postName;
    private String contact;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JucaicunLender that = (JucaicunLender) o;
        return Objects.equals(nickName, that.nickName) &&
                Objects.equals(company, that.company) &&
                Objects.equals(contact, that.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickName, company, contact);
    }
}
