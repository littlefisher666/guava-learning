package com.littlefisher.guava.collect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.ObjectArrays;
import com.google.common.collect.Sets;

/**
 * TODO: 可记录
 */
public class CollectionTest {

    private static Logger logger = LogManager.getLogger(CollectionTest.class);

    /**
     * 集合的创建
     */
    @Test
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
    @Test
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

    @Test
    public void test3() {
        String str1 = "1";
        String str2 = null;
        String str3 = "3";
        List<String> strList = Lists.newArrayList(str1, str2, str3);
        strList = Lists.newArrayList(Collections2.filter(strList, input -> input != null && !"".equals(input)));
        logger.debug("strList: {}", strList);
    }
}
