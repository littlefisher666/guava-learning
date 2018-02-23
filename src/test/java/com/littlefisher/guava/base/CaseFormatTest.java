package com.littlefisher.guava.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.google.common.base.CaseFormat;

public class CaseFormatTest {

    private static Logger logger = LogManager.getLogger(CaseFormatTest.class);

    /**
     * 把字符串按照命名要求进行大小写转换
     * 命名要求有如下几类
     * LOWER_HYPHEN：例如aaa-bbb-ccc
     * LOWER_UNDERSCORE：例如aaa_bbb_ccc
     * LOWER_CAMEL：例如aaaBbbCcc
     * UPPER_CAMEL：例如AaaBbbCcc
     * UPPER_UNDERSCORE：例如AAA_BBB_CCC
     *
     */
    @Test
    public void test1() {
        String result = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, "aaaBbbCccDdd");

        // result: AAA_BBB_CCC_DDD
        logger.debug("result: {}", result);
    }
}
