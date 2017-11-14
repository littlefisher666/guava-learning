package com.littlefisher.guava.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.google.common.base.CharMatcher;

public class CharMatcherTest {

    private static Logger logger = LogManager.getLogger(CharMatcherTest.class);

    @Test
    public void test1() {
        String str = "this is 1 2 3 hello world  test data! Tom!\r\n";
        logger.debug("str: {}", str);
        // 移除control字符，例如\r \n等等
        String noControl = CharMatcher.javaIsoControl().removeFrom(str);
        logger.debug("noControl: {}", noControl);
        // 只保留数字字符
        String onlyDigits = CharMatcher.inRange('0', '9').retainFrom(str);
        logger.debug("onlyDigits: {}", onlyDigits);
        // 去除两端的空格，并把中间的连续空格替换成单个空格
        String spaced = CharMatcher.whitespace().trimAndCollapseFrom(str, ' ');
        logger.debug("spaced: {}", spaced);
        // 用*号替换所有数字
        String noDigits = CharMatcher.inRange('0', '9').replaceFrom(str, "*");
        logger.debug("noDigits: {}", noDigits);
        // 只保留数字和小写字母
        String lowerAndDigit = CharMatcher.inRange('0', '9').or(CharMatcher.inRange('a', 'z'))
                .retainFrom(str);
        logger.debug("lowerAndDigit: {}", lowerAndDigit);
    }
}
