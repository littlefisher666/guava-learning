package com.littlefisher.guava.collect;

import junit.framework.TestCase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * 双向Map。可以通过key获取value，同时也可以通过value获取key
 * 但是要求不管是key还是value，在map中都是唯一的
 */
public class BiMapTest {
    
    private static Logger logger = LogManager.getLogger(BiMapTest.class);

    @Test
    public void test1() {
        BiMap<String, String> weekNameMap = HashBiMap.create();
        weekNameMap.put("星期一", "Monday");
        weekNameMap.put("星期二", "Tuesday");
        weekNameMap.put("星期三", "Wednesday");
        weekNameMap.put("星期四", "Thursday");
        weekNameMap.put("星期五", "Friday");
        weekNameMap.put("星期六", "Saturday");
        weekNameMap.put("星期日", "Sunday");

        logger.debug("星期日的英文名是" + weekNameMap.get("星期日"));
        logger.debug("Sunday的中文是" + weekNameMap.inverse().get("Sunday"));
    }
}
