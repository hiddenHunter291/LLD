package com.lld.cache;

import com.lld.cache.evictionpolicy.EvictionPolicy;

import java.util.HashMap;
import java.util.Map;

public class Cache<K, V> {
    private final int capacity;
    private final Map<K, V> map;
    private final EvictionPolicy<K> evictionPolicy;

    public Cache(final int capacity, final EvictionPolicy<K> evictionPolicy) {
        this.capacity = capacity;
        this.evictionPolicy = evictionPolicy;
        map = new HashMap<>();
    }

    public void set(final K key, final V value) {
        if(map.size() == capacity) {
            final K evictedKey = evictionPolicy.evict();
            map.remove(evictedKey);
        }
        map.put(key, value);
        evictionPolicy.keyAccessed(key);
    }

    public V get(final K key) {
        evictionPolicy.keyAccessed(key);
        return map.get(key);
    }
}
