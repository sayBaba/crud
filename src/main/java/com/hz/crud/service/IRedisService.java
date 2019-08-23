package com.hz.crud.service;

import java.util.Set;

/**
 * redis公共服务
 */
public interface IRedisService {

    /**
     * 往redis添加值
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value);

    /**
     * 根据key从redis中取值
     * @param key
     * @return
     */
    public String get(String key);


    /**
     * 模糊查询redis的key
     */
    public Set<String> hkeys(String pattern);


    /**
     * 单机查询redis
     * @param pattern
     * @return
     */
    public Set<String> getKeysFromLocal(String pattern);

}
