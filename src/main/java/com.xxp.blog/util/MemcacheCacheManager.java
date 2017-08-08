package com.xxp.blog.util;

import java.util.Collection;

import net.spy.memcached.MemcachedClient;
import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;

public class MemcacheCacheManager extends AbstractCacheManager {
    private Collection<Cache> caches;
    private MemcachedClient client = null;

    public MemcacheCacheManager() {
    }

    public MemcacheCacheManager(MemcachedClient client) {
        setClient(client);
    }

    protected Collection<? extends Cache> loadCaches() {
        return this.caches;
    }

    public void setCaches(Collection<Cache> caches) {
        this.caches = caches;
    }

    public void setClient(MemcachedClient client) {
        this.client = client;
        updateCaches();
    }

    public Cache getCache(String name) {
        checkState();

        Cache cache = super.getCache(name);
        if (cache == null) {
            cache = new MemcacheCache(name, this.client);
            addCache(cache);
        }
        return cache;
    }

    private void checkState() {
        if (this.client == null)
            throw new IllegalStateException("MemcacheClient must not be null.");
    }

    private void updateCaches() {
        if (this.caches != null)
            for (Cache cache : this.caches)
                if ((cache instanceof MemcacheCache)) {
                    MemcacheCache memcacheCache = (MemcacheCache) cache;
                    memcacheCache.setClient(this.client);
                }
    }
}