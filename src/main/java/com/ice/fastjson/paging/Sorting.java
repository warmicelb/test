package com.ice.fastjson.paging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Sorting 排序
 * @ClassName: Sorting
 * @DetaTime 2019-01-18 17:00:32
 * @author 花花
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(fluent = false, chain = true, prefix = "")
public class Sorting implements Serializable {

    /**
     * 属性名称
     */
    private String name;

    /**
     * 排序方式
     */
    private String manner;

    /**
     * Sorting 集合
     * @param name 名称
     * @param manner 方式
     * @return 处理结果
     */
    public List<Sorting> builder(String name, String manner) {
        return Collections.singletonList(new Sorting(name, manner));
    }

}
