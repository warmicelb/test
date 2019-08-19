package com.ice.fastjson.paging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Mapper 映射器
 * -- Mapper 分页.
 * @ClassName: Mapper
 * @DetaTime 2019-01-18 17:00:32
 * @author 花花
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(fluent = false, chain = true, prefix = "")
public class Mapper extends Paging<Sorting> implements Serializable {

    /**
     * 下标
     * -- 开始下标
     */
    private Integer subscript;

    /**
     * 验证赋值
     */
    public void validate() {
        Integer size = formatNull(this.getSize());
        this.setSubscript(formatNull(this.getPage(), size)).setSize(size);
    }

    /**
     * Null 式化
     * @param value Integer
     * @param factor Integer
     * @return 处理结果
     */
    private static Integer formatNull(Integer value, Integer factor) {
        return (isNotEmpty(value) ? ((value - 1) * factor) : 0);
    }

    /**
     * Null 式化
     * @param value Integer
     * @return 处理结果
     */
    private static Integer formatNull(Integer value) {
        return (isNotEmpty(value) ? value : 10);
    }

    /**
     * 验证 Integer
     * @param value Integer
     * @return 处理结果
     */
    private static Boolean isNotEmpty(Integer value) {
        return (value != null && value >= 1);
    }

}
