package com.littlefisher.guava.collect;

import java.util.Collection;

import junit.framework.TestCase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class MutliMapTest {

    private static Logger logger = LogManager.getLogger(MutliMapTest.class);

    @Test
    public void test1() {
        Multimap<String, String> myMultimap = ArrayListMultimap.create();
        // 添加键值对
        myMultimap.put("Fruits", "Bannana");
        //给Fruits元素添加另一个元素
        myMultimap.put("Fruits", "Apple");
        myMultimap.put("Fruits", "Pear");
        myMultimap.put("Vegetables", "Carrot");

        // 获得multimap的size
        int size = myMultimap.size();
        logger.debug(size);  // 4

        // 获得Fruits对应的所有的值
        Collection<String> fruits = myMultimap.get("Fruits");
        logger.debug(fruits); // [Bannana, Apple, Pear]

        Collection<String> vegetables = myMultimap.get("Vegetables");
        logger.debug(vegetables); // [Carrot]

        // 遍历Mutlimap
        for (String value : myMultimap.values()) {
            logger.debug(value);
        }

        // Removing a single value
        myMultimap.remove("Fruits", "Pear");
        logger.debug(myMultimap.get("Fruits")); // [Bannana, Pear]

        // Remove all values for a key
        myMultimap.removeAll("Fruits");
        logger.debug(myMultimap.get("Fruits")); // [] (Empty Collection!)
    }
}
