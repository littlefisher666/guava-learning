package com.littlefisher.guava.collect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.junit.Test;

import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.ObjectArrays;
import com.google.common.collect.Sets;
import com.littlefisher.guava.model.Person;
import com.littlefisher.guava.model.Student;

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
        Map<String, Student> map = new HashMap<>();
        List<Student> list = new ArrayList<>();

        //guava
        Map<String, Student> map4Guava = Maps.newHashMap();
        List<Student> list4Guava = Lists.newArrayList();
        Set<Student> set4Guava = Sets.newHashSet();
        List<Student> List4Guava = Lists.newLinkedList();
        Map<String, Person> personMap = Maps.newHashMap();
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

        // guava
        Set<String> set4Guava = Sets.newHashSet("one", "two", "three");
        List<String> list4Guava = Lists.newArrayList("one", "two", "three");
        // 不可变
        Map<String, String> map4Guava = ImmutableMap.of("ON", "TRUE", "OFF", "FALSE");

    }

    /**
     * 通过Collections2.filter进行集合过滤
     */
    @Test
    public void test3() {
        String str1 = "1";
        String str2 = null;
        String str3 = "3";
        List<String> strList = Lists.newArrayList(str1, str2, str3);
        strList = Lists.newArrayList(Collections2.filter(strList, Strings::isNotBlank));
        logger.debug("strList: {}", strList);
    }
}
