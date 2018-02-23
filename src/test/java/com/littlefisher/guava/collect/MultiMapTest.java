package com.littlefisher.guava.collect;

import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * MultiMap一个key可以对应一个List的Map
 * TODO: 可记录
 */
public class MultiMapTest {

    private static Logger logger = LogManager.getLogger(MultiMapTest.class);

    @Test
    public void test1() {
        Multimap<String, String> plantMultiMap = ArrayListMultimap.create();
        // 添加键值对
        plantMultiMap.put("Fruits", "Banana");
        //给Fruits元素添加另一个元素
        plantMultiMap.put("Fruits", "Apple");
        plantMultiMap.put("Fruits", "Pear");
        plantMultiMap.put("Vegetables", "Carrot");

        // 获得multiMap的size
        int size = plantMultiMap.size();
        // 4
        logger.debug("multiMap.size: {}", size);

        // 获得Fruits对应的所有的值
        Collection<String> fruits = plantMultiMap.get("Fruits");
        // [Banana, Apple, Pear]
        logger.debug("fruits: {}", fruits);

        Collection<String> vegetables = plantMultiMap.get("Vegetables");
        // [Carrot]
        logger.debug("vegetables: {}", vegetables);

        // 遍历MultiMap
        plantMultiMap.values()
                .forEach(logger::debug);

        // Removing a single value
        plantMultiMap.remove("Fruits", "Pear");
        // [Banana, Pear]
        logger.debug(plantMultiMap.get("Fruits"));

        // Remove all values for a key
        plantMultiMap.removeAll("Fruits");
        // [] (Empty Collection!)
        logger.debug(plantMultiMap.get("Fruits"));
    }
}
