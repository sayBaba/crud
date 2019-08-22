package com.hz.crud.test;

import com.google.gson.Gson;
import com.hz.crud.model.UserBean;
import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class TestJedis {

    /**
     * jedis连接单机
     */
    public static  void testJedis(){

        //构建jedis对象
//        Jedis jedis = new Jedis("127.0.0.1", 6379);
//        // 向redis中添加数据
//        jedis.set("name","大鹏");
//        jedis.set("name","大张");
//        System.out.println(jedis.get("name"));
//        jedis.close();
        //构建连接池配置信息
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //设置最大链接
        jedisPoolConfig.setMaxTotal(50);
        //构建连接池
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"127.0.0.1",6379);
        Jedis jedis = jedisPool.getResource();

        Gson gson = new Gson();
        ArrayList<UserBean> userBeans = new ArrayList<>();
        HashMap<String, UserBean> stringUserBeanHashMap = new HashMap<String, UserBean>();
        HashSet<UserBean> userBeanHashSet = new HashSet<UserBean>();

        UserBean userBean = null;
        for(int i=0;i<10;++i){
            userBean = new UserBean();
            userBean.setUserName("xiuzhang");
            userBean.setAddress("如花姐");
            userBean.setEmail("历日李亚");
            userBean.setEmail("123456@qq.com");
            userBeanHashSet.add(userBean);
            userBeans.add(userBean);
            stringUserBeanHashMap.put("user"+i,userBean);
        }
//        String user = gson.toJson(userBean);

        jedis.set("user",);
        jedis.set("set",gson.toJson(userBeanHashSet));
        jedis.set("list",gson.toJson(userBeans));
        jedis.set("hashmap",gson.toJson(stringUserBeanHashMap));
        //读取数据
        System.out.println(jedis.get("hashmap"));
        jedisPool.returnResource(jedis);
        jedisPool.close();

    }

    /**
     * 集群式的连接池
     */
    public static void testJedisShard(){
        //连接池的配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(30);
        // 定义集群信息
        List<JedisShardInfo> shardInfoList = new ArrayList<JedisShardInfo>();
        JedisShardInfo shardInfo = new JedisShardInfo("127.0.0.1",6379);
        JedisShardInfo shardInfo1 = new JedisShardInfo("192.168.1.170",6379);
        shardInfoList.add(shardInfo);
        shardInfoList.add(shardInfo1);

        //定义集群连接池
        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(config,shardInfoList);

        ShardedJedis shardedJedis = shardedJedisPool.getResource();

//      String xx = shardedJedis.get("test");

        shardedJedis.set("say","谷歌");

        String xx = shardedJedis.get("say");
        System.out.println("======"+xx);

        shardedJedis.close();
        shardedJedisPool.close();

    }


    public static void main(String[] args) {

        testJedis();
        TestJedis.testJedisShard();

    }
}
