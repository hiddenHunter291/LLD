package com.lld.cache;

import com.lld.cache.evictionpolicy.EvictionPolicy;
import com.lld.cache.evictionpolicy.LRUEvictionPolicy;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Cache<K, V> {
    private final int capacity;
    private final Map<K, V> map;
    private final EvictionPolicy<K> evictionPolicy;

    public Cache(@NotNull final EvictionPolicy<K> evictionPolicy,
                 final int capacity) {
        this.capacity = capacity;
        this.evictionPolicy = evictionPolicy;
        map = new HashMap<>();
    }

    public void set(@NotNull final K key,
                    @NotNull final V value) {
        if(map.size() == capacity) {
            final K evictedKey = evictionPolicy.evict();
            map.remove(evictedKey);
        }
        map.put(key, value);
        evictionPolicy.keyAccessed(key);
    }

    public V get(@NotNull final K key) {
        evictionPolicy.keyAccessed(key);
        return map.get(key);
    }

    public Set<K> getAllKeys() {
        return map.keySet();
    }

    /**static-factory**/

    public static <K, V> Cache<K, V> lru(final int capacity) {
        return new Cache<>(new LRUEvictionPolicy<>(), capacity);
    }
}
