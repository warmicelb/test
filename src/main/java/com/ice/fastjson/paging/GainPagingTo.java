package com.ice.fastjson.paging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页信息 :: 分页条件
 * -- Statistical 全局统计 :: 数据统计.
 * -- StatisticalChannel 渠道统计 :: 渠道统计.
 * -- ChannelTimezone 时区统计 :: 数据统计.
 * @ClassName: GainPagingTo
 * @DetaTime 2019-01-19 09:00:32
 * @author 花花
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(fluent = false, chain = true, prefix = "")
public class GainPagingTo extends Mapper implements Serializable {

    /**
     * 统计日期 :: 全局统计 || 渠道统计 || 时区统计
     */
    private String statisticalTime;

    /**
     * 应用标识 :: 全局统计 || 渠道统计
     * -- 应用唯一性标识（ UID ）.
     */
    private String appUid;

    /**
     * 渠道代码 :: 渠道统计 || 时区统计
     * -- 与渠道的关联字段.
     */
    private String channelCode;


    /**
     * 统计时区
     * -- 即一天 24 小时中的单个小时 , 时区区间 0 - 23.
     */
    private Integer statisticalTimezone;

    /**
     * 渠道停用时间
     */
    private String disableDate;
}
