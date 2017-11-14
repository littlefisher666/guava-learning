package com.littlefisher.guava.collect;

import java.util.Collection;

import junit.framework.TestCase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * Multimap一个key可以对应一个List的Map
 * TODO: 可记录
 */
public class MutliMapTest {

    private static Logger logger = LogManager.getLogger(MutliMapTest.class);

    @Test
    public void test1() {
        Multimap<String, String> plantMultimap = ArrayListMultimap.create();
        // 添加键值对
        plantMultimap.put("Fruits", "Bannana");
        //给Fruits元素添加另一个元素
        plantMultimap.put("Fruits", "Apple");
        plantMultimap.put("Fruits", "Pear");
        plantMultimap.put("Vegetables", "Carrot");

        // 获得multimap的size
        int size = plantMultimap.size();
        logger.debug("multimap.size: {}", size);  // 4

        // 获得Fruits对应的所有的值
        Collection<String> fruits = plantMultimap.get("Fruits");
        logger.debug("fruits: {}", fruits); // [Bannana, Apple, Pear]

        Collection<String> vegetables = plantMultimap.get("Vegetables");
        logger.debug("vegetables: {}", vegetables); // [Carrot]

        // 遍历Mutlimap
        plantMultimap.values().forEach(logger::debug);

        // Removing a single value
        plantMultimap.remove("Fruits", "Pear");
        logger.debug(plantMultimap.get("Fruits")); // [Bannana, Pear]

        // Remove all values for a key
        plantMultimap.removeAll("Fruits");
        logger.debug(plantMultimap.get("Fruits")); // [] (Empty Collection!)
    }
}
