package com.hz.crud.service.impl;

import com.hz.crud.service.IRedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.*;

import java.util.Set;

/**
 * redis公共服务实现类
 */
@Service
public class RedisServiceImpl implements IRedisService {

    private static final Logger loger = LoggerFactory.getLogger(RedisServiceImpl.class);

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    @Override
    public String set(String key, String value) {
        loger.info("key:{},value:{},添加redis",key,value);
        ShardedJedis shardedJedis = null;
        try {

            shardedJedis = shardedJedisPool.getResource();


            return shardedJedis.set(key, value);
        }finally {
            if (null != shardedJedis) {
                // 关闭，检测连接是否有效，有效则放回到连接池中，无效则重置状态
                shardedJedis.close();
            }
        }
    }

    @Override
    public String get(String key) {
        loger.info("请求参数,key:{}从redis取值",key);
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.get(key);
        }finally {
            if (null != shardedJedis) {
                // 关闭，检测连接是否有效，有效则放回到连接池中，无效则重置状态
                shardedJedis.close();
            }
        }

    }

    @Override
    public Set<String> hkeys(String pattern) {
        loger.info("请求参数,pattern:{}模糊查询redis的key",pattern);
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.hkeys(pattern);
        }finally {
            if (null != shardedJedis) {
                // 关闭，检测连接是否有效，有效则放回到连接池中，无效则重置状态
                shardedJedis.close();
            }
        }
    }

    @Override
    public Set<String> getKeysFromLocal(String pattern) {
        Jedis jedis = null;
        try{
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            //设置最大链接
            jedisPoolConfig.setMaxTotal(50);
            //构建连接池
            JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);
            jedis = jedisPool.getResource();
            return jedis.keys(pattern);
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
    }
}
