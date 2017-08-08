package com.xxp.blog.util;

import net.spy.memcached.MemcachedClient;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.support.SimpleValueWrapper;

public class MemcacheCache
        implements Cache {
    private MemcachedClient client;
    private String name;

    public MemcacheCache() {
    }

    public MemcacheCache(String name, MemcachedClient client) {
        this.client = client;
        this.name = name;
    }

    private static String objectToString(Object object) {
        if (object == null)
            return null;
        if ((object instanceof String)) {
            return DigestUtils.md5Hex((String) object);
        }
        return DigestUtils.md5Hex(object.toString());
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getNativeCache() {
        return this.client;
    }

    public Cache.ValueWrapper get(Object key) {
        Object value = this.client.get(objectToString(key));
        return value != null ? new SimpleValueWrapper(value) : null;
    }

    public <T> T get(Object key, Class<T> type) {
        throw new IllegalArgumentException("未实现的方法");
    }

    public void put(Object key, Object value) {
        if (value == null) {
            return;
        }
        this.client.set(objectToString(key), 3600, value);
    }

    public Cache.ValueWrapper putIfAbsent(Object key, Object value) {
        throw new IllegalArgumentException("未实现的方法");
    }

    public void evict(Object key) {
        this.client.delete(objectToString(key));
    }

    public void clear() {
        throw new IllegalArgumentException("未实现的方法");
    }

    public MemcachedClient getClient() {
        return this.client;
    }

    public void setClient(MemcachedClient client) {
        this.client = client;
    }
}