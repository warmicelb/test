package com.ice.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.Tuple;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.*;

/**
 * Hello world!
 *
 */
public class TestRedis
{
    public static void main( String[] args )
    {
        testJedisLocal();
    }

    /**
     * nio
     * @throws IOException
     */
    public void select() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Selector selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.socket().bind(new InetSocketAddress(8080));
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        while(true) {
            Set selectedKeys = selector.selectedKeys();
        }

    }
    public void charSet(){
        String s = "1我a";
        byte[] bytes = s.getBytes();
        System.out.println(bytes.length);
        new String(s.getBytes());
    }

    /**
     * 测试jedis链接redis数据库
     */
    public static void testJedis(){
        Jedis jedis = new Jedis("47.75.240.173",6379);
        jedis.auth("ice");
        //1.字符串
        jedis.set("java","success");
        //2.hash
        jedis.hmset("001", Collections.singletonMap("name","ice"));
        //3list 从最左边向左插入
        jedis.lpush("language","java","c","php");
        //去除list元素
        List<String> strList = jedis.lrange("language",0,3);
        for (String s:strList
             ) {
            System.out.println(s);
        }
        System.out.println(jedis.get("java"));
    }

    /**
     * 管道
     */
    public static void testPipelinet() {
        Jedis jedis = new Jedis("47.75.240.173", 6379);
        Pipeline pipelined = jedis.pipelined();
        pipelined.del("a");
        pipelined.del("b");
        pipelined.exec();
    }

    /**
     * redis事务
     */
    public static void testTransaction(){
        Jedis jedis = new Jedis("47.75.240.173", 6379);
        Transaction multi = jedis.multi();
        multi.discard();
    }

    /**
     * 测试jedis链接redis数据库
     */
    public static void testJedisLocal(){
        Jedis jedis = new Jedis("localhost",6379);
        //1.字符串（计数器，incr）
        jedis.set("java","success");
        System.out.println(jedis.get("java"));
        jedis.set("view","1");
        jedis.incr("view");
        System.out.println(jedis.get("view"));
        //2.hash
        jedis.hmset("001", Collections.singletonMap("name","ice"));
        //3.list(存放时间轴的数据) 从最左边向左插入
        jedis.del("language");
        jedis.lpush("language","java","c","php");
        //从右向右插入
        jedis.rpush("language","js","py");
        //取出list元素
        List<String> strList = jedis.lrange("language",0,-1);
        for (String s:strList
        ) {
            System.out.println(s);
        }
        //4.set(集合，适合集合操作，共同标签，兴趣)
        jedis.sadd("favourite1","footable","footable","pingpong","tennis");
        jedis.sadd("favourite2","footable","pingpong","basketball");
        Set<String> sinter = jedis.sinter("favourite1", "favourite2");
        Set<String> sdiff = jedis.sdiff("favourite1", "favourite2");
        //5.sortedSet(有序集合，适合排行榜等的操作)
        Map<String,Double> map = new HashMap<>();
        map.put("java",5.0);
        map.put("js",2.0);
        map.put("c++",4.0);
        map.put("c++",3.0);
        jedis.zadd("rank",map);
        //集合大小
        Long size = jedis.zcard("rank");
        System.out.println("count is "+size);
        //统计分数区间数量
        System.out.println("4.0-5.0 range count is "+jedis.zcount("rank",4.0,5.0));
        //为某个元素执行增量操作
        jedis.zincrby("rank",5.0,"java");
        System.out.println("java in set rank "+jedis.zrevrank("rank","java")+1);
        //移除有序集合中给定的分数区间的所有成员
        jedis.zremrangeByScore("rank",4.0,4.5);
//        指定区间内，带有分数值(可选)的有序集成员的列表(通过索引)
        Set<Tuple> rankSet = jedis.zrevrangeWithScores("rank", 0, -1);
        for (Tuple tuple : rankSet) {
            System.out.println(tuple.getElement()+":"+tuple.getScore());
        }

    }
}
