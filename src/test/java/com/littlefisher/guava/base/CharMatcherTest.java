package com.littlefisher.guava.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.google.common.base.CharMatcher;

public class CharMatcherTest {

    private static Logger logger = LogManager.getLogger(CharMatcherTest.class);

    @Test
    public void test1() {
        String str = "this is 1 2 3 hello world  test data!";
        // 移除control字符
        String noControl = CharMatcher.JAVA_ISO_CONTROL.removeFrom(str);
        logger.debug(noControl);
        // 只保留数字字符
        String theDigits = CharMatcher.DIGIT.retainFrom(str);
        logger.debug(theDigits);
        // 去除两端的空格，并把中间的连续空格替换成单个空格
        String spaced = CharMatcher.WHITESPACE.trimAndCollapseFrom(str, ' ');
        logger.debug(spaced);
        // 用*号替换所有数字
        String noDigits = CharMatcher.JAVA_DIGIT.replaceFrom(str, "*");
        logger.debug(noDigits);
        // 只保留数字和小写字母
        String lowerAndDigit = CharMatcher.JAVA_DIGIT.or(CharMatcher.JAVA_LOWER_CASE)
                .retainFrom(str);
        logger.debug(lowerAndDigit);
    }
}
