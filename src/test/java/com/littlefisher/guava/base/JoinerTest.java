package com.littlefisher.guava.base;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;

public class JoinerTest {

    private static Logger logger = LogManager.getLogger(JoinerTest.class);

    /**
     * 通过空格将hello和world合并起来
     */
    @Test
    public void test1() {
        String joinResult = Joiner.on(" ").join(new String[] { "hello", "world" });
        logger.debug("joinResult: {}", joinResult);
    }

    /**
     * 通过Joiner进行二次合并
     */
    @Test
    public void test2() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("a", "b");
        Map<String, Object> map2 = Maps.newHashMap();
        map2.put("c", "d");
        map.put("e", map2);
        String mapJoinResult = Joiner.on(",").withKeyValueSeparator("=").join(map);
        logger.debug("mapJoinResult: {}", mapJoinResult);
    }

    /**
     * 每当我想起将string数组或集合打印出来需要拼接逗号，就感觉特别恶心
     * 有了guava以后，就变得easy多了
     */
    @Test
    public void test3() {
        String[] strings = new String[] { "ad", "s", "e", null, "ds" };
        logger.debug("str: {}", strings);
        // 无法针对于null的数据做处理
//        String str1 = Joiner.on(",").join(strings);
//        logger.debug("str1: {}", str1);

        //对于null进行打印NA来替换
        String str2 = Joiner.on(",").useForNull("NA").join(strings);
        logger.debug("str2: {}", str2);

        //把null滤掉:
        String str3 = Joiner.on(",").skipNulls().join(strings);
        logger.debug("str3: {}", str3);
    }
}
