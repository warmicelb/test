package com.ice.redis.vote;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Article
 * @Description TODO
 * @Author liubin
 * @Date 2019/8/12 4:34 PM
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private Long userId;
    private String title;
    private String content;
    private Integer likenum;
}
