package com.littlefisher.guava.collect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.ObjectArrays;
import com.google.common.collect.Sets;

public class CollectionTest extends TestCase {

    /**
     * 集合的创建
     */
    public void test1() {
        //jdk
        Map<String, Map<String, String>> map = new HashMap<>();
        List<List<Map<String, String>>> list = new ArrayList<>();

        //guava
        Map<String, Map<String, String>> map_cp = Maps.newHashMap();
        List<List<Map<String, String>>> list_cp = Lists.newArrayList();
        Set<String> set = Sets.newHashSet();
        List<String> List = Lists.newLinkedList();
        Map<String, String> personMap = Maps.newHashMap();
        Integer[] intArrays = ObjectArrays.newArray(Integer.class, 10);
    }

    /**
     * 集合的初始化
     */
    public void test2() {
        //jdk
        Set<String> set = new HashSet<>();
        set.add("one");
        set.add("two");
        set.add("three");

        //guava
        Set<String> set_cp = Sets.newHashSet("one", "two", "three");
        List<String> list = Lists.newArrayList("one", "two", "three");
        Map<String, String> map = ImmutableMap.of("ON", "TRUE", "OFF", "FALSE"); //不可变

    }
}
