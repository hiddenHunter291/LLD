package com.lld.cache.evictionpolicy;

public interface EvictionPolicy<T> {
    T evict();
    void keyAccessed(T key);
}
