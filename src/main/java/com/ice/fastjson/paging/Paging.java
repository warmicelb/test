package com.ice.fastjson.paging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * Paging 分页
 * @ClassName: Paging
 * @DetaTime 2019-01-18 17:00:32
 * @author 花花
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = false, chain = true, prefix = "")
public class Paging<T extends Sorting> implements Serializable {

    /**
     * 页码
     */
    private Integer page;

    /**
     * 条数
     */
    private Integer size;

    /**
     * 排序
     */
    private List<T> sorting;

}
