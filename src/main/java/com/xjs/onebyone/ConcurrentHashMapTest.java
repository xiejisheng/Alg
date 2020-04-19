package com.xjs.onebyone;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @description
 * @auther xjs
 * @date
 */
public class ConcurrentHashMapTest {
    @Test
    public void testCon() {
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<Integer, Integer>(15, 0.75f, 1);
        map.put(1, 1);
    }
}
