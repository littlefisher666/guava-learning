package com.littlefisher.guava.base;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;

/**
 * TODO: 可记录
 */
public class SplitterTest {

    private static Logger logger = LogManager.getLogger(SplitterTest.class);

    /**
     * 通过正则表达式进行拆分
     */
    @Test
    public void test1() {
        String str = "this is my java program!";
        Splitter split = Splitter.onPattern("[o| ]+"); //支持正则
        Iterable<String> it = split
                // 排除空字符
                .omitEmptyStrings()
                // 对截取后字符串进行trim
                .trimResults().split(str);

        for (String tempStr : it) {
            logger.debug("tempStr: {}", tempStr);
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
        List<String> list = Splitter.on(" ").limit(2).splitToList(str);
        logger.debug("list: {}", list);
        list = Splitter.on(" ").limit(3).splitToList(str);
        String[] strArr = Iterables.toArray(Splitter.on(" ").limit(3).split(str), String.class);
        logger.debug("list: {}", list);
    }

    /**
     * 使用jdk中的split会出现奇异的地方，例如下面的，在最后一个逗号后面应该也要拆分出来一个空字符串
     */
    @Test
    public void test4() {
        String[] strArr = ",a,,b,".split(",");
        for (String s : strArr) {
            logger.debug("s: {}", s);
        }
    }

}
