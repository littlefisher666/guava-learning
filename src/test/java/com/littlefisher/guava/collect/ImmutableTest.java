package com.littlefisher.guava.collect;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.google.common.collect.ImmutableSet;

/**
 * 注意：不可变集合的使用和普通集合一样，只是不能使用他们的add，remove等修改集合的方法。
 */
public class ImmutableTest {

    private static Logger logger = LogManager.getLogger(ImmutableTest.class);

    /**
     * 使用builder进行创建
     */
    @Test
    public void test1() {
        Set<String> immutableNamedColors = ImmutableSet.<String>builder()
                .add("red", "green", "black", "white", "grey").build();
        // 一旦不可变集合进行了创建就不可以在操作了
        // immutableNamedColors.add("yellow");
        for (String color : immutableNamedColors) {
            logger.debug(color);
        }
    }

    /**
     * 使用of静态方法进行创建
     */
    @Test
    public void test2() {
        Set<String> immutableNamedColors = ImmutableSet
                .of("red", "green", "black", "white", "grey");
        for (String color : immutableNamedColors) {
            logger.debug(color);
        }
    }

    /**
     * 第三种方法使用copyOf静态方法创建：
     */
    @Test
    public void test3() {
        Set<String> immutableNamedColors = ImmutableSet
                .copyOf(new String[] { "red", "green", "black", "white", "grey" });
        for (String color : immutableNamedColors) {
            logger.debug(color);
        }
    }
}
