package com.ice.redis.vote;

import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * 用户可以发表文章,发表时默认给自己的文章投了一票
 * 用户在查看网站时可以按评分进行排列查看
 * 用户也可以按照文章发布时间进行排序
 * 为节约内存，一篇文章发表后，7天内可以投票,7天过后就不能再投票了
 * 为防止同一用户多次投票，用户只能给一篇文章投一次票
 * @ClassName ArticleService
 * @Description TODO
 * @Author liubin
 * @Date 2019/8/12 4:42 PM
 **/
public class ArticleService {

    private static long EXPIRE_TIME_MILLS = 7*24*60*60*1000;
    private static double DEFAULT_VOTE_SCORE = 100;
    /**
     * 发布文章
     */
    public static void publishArticle(String userId){
        Jedis jedis = JedisTool.getConnection();
        //自增获取文章的id标识
        Long id = jedis.incr("articles:");
        Map<String,String> article = new HashMap<>();
        article.put("userId",userId);
        article.put("name","震惊！某花季少女10年前竟然才不到10岁");
        article .put("content","fsdfa");
        article .put("publishTime",String.valueOf(System.currentTimeMillis()*1000));
        article.put("likenum","0");
        //1保存文章
        jedis.hmset(VoteConstant.ARTILE_INFO+id,article);
        //2文章发布时间
        jedis.sadd(VoteConstant.ARTILE_TIME+id,String.valueOf(System.currentTimeMillis()));
        //3.文章分数
        jedis.zadd(VoteConstant.ARTILE_SCORE+"scores",DEFAULT_VOTE_SCORE,String.valueOf(id));
        //4.投票记录
        if(jedis.sadd(VoteConstant.ARTILE_VOTE+id,userId)==1){
            jedis.hincrBy(VoteConstant.ARTILE_INFO+id,"likenum",1);
            //4.文章分数
            jedis.zincrby(VoteConstant.ARTILE_SCORE+"scores",DEFAULT_VOTE_SCORE,String.valueOf(id));
        }else{
            System.out.println("投票失败");
        }
    }

    /**
     * 投票
     * @param userId
     * @param articleId
     */
    public static void vote(String userId,String articleId){
        Jedis jedis = JedisTool.getConnection();
        long publishTime = Long.valueOf(jedis.hget(VoteConstant.ARTILE_INFO + articleId, "publishTime"));
        //判断文章是否过期
        if(System.currentTimeMillis()-publishTime<EXPIRE_TIME_MILLS){
            if(jedis.sadd(VoteConstant.ARTILE_VOTE+articleId,userId)==1){
                jedis.hincrBy(VoteConstant.ARTILE_INFO+articleId,"likenum",1);
                //4.文章分数
                jedis.zincrby(VoteConstant.ARTILE_SCORE+"scores",DEFAULT_VOTE_SCORE,String.valueOf(articleId));
            }else{
                System.out.println("投票失败");
            }
        }
    }

    /**
     * 按照分数倒序查询
     * @param page
     * @return
     */
    public static List<Map<String, String>> getPageByRecScore(int page){
        Jedis jedis = JedisTool.getConnection();
        int start = (page-1)*10;
        int end = start + 10;
        Set<String> zrevrange = jedis.zrevrange(VoteConstant.ARTILE_SCORE + "scores", start, end);
        List<Map<String,String>> articleList = new ArrayList<>();
        for (String articleId : zrevrange) {
            articleList.add(jedis.hgetAll(VoteConstant.ARTILE_INFO+articleId));
        }
        return articleList;
    }

    /**
     * 获取redis连接
     */
    private static class JedisTool{
        public static Jedis getConnection() {
            return new Jedis("localhost",6379);
        }
    }
}
