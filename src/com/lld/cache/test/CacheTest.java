package com.lld.cache.test;

import com.lld.cache.Cache;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class CacheTest {
    @Test
    public void testCase1() {
        final Cache<Integer, Integer> cache = Cache.lru(3);
        cache.set(1, 100);
        cache.set(2, 200);
        cache.set(3, 300);
        cache.set(4, 400);
        Set<Integer> keySet = cache.getAllKeys();
        Assert.assertFalse("key:1 should have been evicted", keySet.contains(1));
        Assert.assertTrue("key:2 should be present", keySet.contains(2));
        Assert.assertTrue("key:3 should be present", keySet.contains(3));
        Assert.assertTrue("key:4 should be present", keySet.contains(4));
        cache.get(2);
        cache.set(5, 500);
        keySet = cache.getAllKeys();
        Assert.assertFalse("key:3 should have been evicted", keySet.contains(3));
        Assert.assertTrue("key:2 should be present", keySet.contains(2));
        Assert.assertTrue("key:4 should be present", keySet.contains(4));
        Assert.assertTrue("key:5 should be present", keySet.contains(5));
    }

    @Test
    public void testCase2() {
        final Cache<Integer, Integer> cache = Cache.lru(1);
        cache.set(1, 100);
        cache.set(2, 200);
        cache.set(3, 300);
        final Set<Integer> keySet = cache.getAllKeys();
        Assert.assertEquals("size of the cache should be 1", 1, keySet.size());
        Assert.assertTrue("key:3 should be present", keySet.contains(3));
    }
}
