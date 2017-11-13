package com.littlefisher.guava.base;

import com.google.common.base.Strings;

import junit.framework.TestCase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class StringsTest {
    
    private static Logger logger = LogManager.getLogger(StringsTest.class);

    /**
     * 将""转换成null
     */
    @Test
    public void test1() {
        String str = "";
        logger.debug(Strings.emptyToNull(str));
    }

    /**
     * 将null转换成""
     */
    @Test
    public void test2() {
        String str = null;
        logger.debug(Strings.nullToEmpty(str));
    }

    /**
     * 判读是否为空
     */
    @Test
    public void test3() {
        String str = null;
        logger.debug(Strings.isNullOrEmpty(str));

        str = "";
        logger.debug(Strings.isNullOrEmpty(str));
    }

    /**
     * 获取两个字符串相同的前缀或后缀
     */
    @Test
    public void test4() {
        String str1 = "this is java crazy";
        String str2 = "this are java crazy";
        logger.debug(Strings.commonPrefix(str1, str2));
        logger.debug(Strings.commonSuffix(str1, str2));
    }

    /**
     * 补全字符串
     */
    @Test
    public void test5() {
        String str1 = "34";
        String padStartString = Strings.padStart(str1, 4, '0');
        logger.debug(padStartString);

        String padEndString = Strings.padEnd(str1, 6, '5');
        logger.debug(padEndString);
    }

    /**
     * 将某个字符串进行重复
     */
    @Test
    public void test6() {
        String str1 = "34";
        String str2 = Strings.repeat(str1, 2);
        logger.debug(str2);

        //		str1 = null;
        //		str2 = Strings.repeat(str1, 2);
        //		logger.debug(str2);
        //
        //		str1 = "";
        //		str2 = Strings.repeat(str1, 2);
        //		logger.debug(str2);
    }
}
