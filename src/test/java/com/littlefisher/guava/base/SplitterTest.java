package com.littlefisher.guava.base;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class SplitterTest {
    
    private static Logger logger = LogManager.getLogger(SplitterTest.class);

    /**
     * 通过正则表达式进行拆分
     */
    @Test
    public void test1() {
        String str = "this is my java program!";
        Splitter split = Splitter.onPattern("[o| ]+"); //支持正则
        Iterable<String> it = split.split(str);

        split.omitEmptyStrings(); //排除空字符
        split.trimResults(); //对截取后字符串进行trim
        for (String temp : it) {
            logger.debug(temp);
        }
    }

    /**
     * Splitter做二次拆分
     */
    @Test
    public void test2() {
        String toSplitString = "a=b;c=d,e=f";
        Map<String, String> kvs = Splitter.onPattern("[,;]{1,}").withKeyValueSeparator('=')
                .split(toSplitString);
        for (Map.Entry<String, String> entry : kvs.entrySet()) {
            logger.debug(String.format("%s=%s", entry.getKey(), entry.getValue()));
        }
    }

    /**
     *
     */
    @Test
    public void test3() {
        //limit(int) 限制拆分出来的数量
        String str = "hello is world";
        List<String> list = Lists.newArrayList(Splitter.on(" ").limit(2).split(str));
        logger.debug(list);
        list = Splitter.on(" ").limit(3).splitToList(str);
        String[] strArr = Iterables.toArray(Splitter.on(" ").limit(3).split(str), String.class);
        logger.debug(list);
    }

    /**
     *
     */
    @Test
    public void test4() {
        String[] strArr = ",a,,b,".split(",");
        logger.debug(strArr);
    }

    /**
     *
     */
    @Test
    public void test5() {

    }

    @Test
    public void test6() {

    }

    @Test
    public void test7() {

    }

}
