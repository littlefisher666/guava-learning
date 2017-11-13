package com.littlefisher.guava.collect;

import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

public class IteratorsTest  {

    private static Logger logger = LogManager.getLogger(IteratorsTest.class);

    /**
     * all方法的第一个参数是Iterator，第二个参数是Predicate<String>的实现，
     * 这个方法的意义是不需要我们自己去写while循环了，他的内部实现中帮我们做了循环，
     * 把循环体中的条件判断抽象出来了。
     */
    @Test
    public void test1() {
        List<String> list = Lists.newArrayList("Apple", "Pear", "Peach", "Banana");

        Predicate<String> condition = new Predicate<String>() {

            public boolean apply(String input) {
                return input.startsWith("P");
            }

        };
        boolean allIsStartsWithP = Iterators.all(list.iterator(), condition);
        //        boolean allIsStartsWithP = Iterators.any(list.iterator(), condition);
        logger.debug("all result == " + allIsStartsWithP);

        String secondElement = Iterators.get(list.iterator(), 1);
        logger.debug(secondElement);
    }

    /**
     * filter方法过滤符合条件的项
     */
    @Test
    public void test2() {
        List<String> list = Lists.newArrayList("Apple", "Pear", "Peach", "Banana");
        Iterator<String> startPElements = Iterators
                .filter(list.iterator(), new Predicate<String>() {

                    public boolean apply(String input) {
                        return ((String) input).startsWith("P");
                    }
                });

        String secondElement = Iterators.get(startPElements, 1);
        logger.debug(secondElement);
    }

    /**
     * find方法返回符合条件的第一个元素
     */
    @Test
    public void test3() {
        List<String> list = Lists.newArrayList("Apple", "Pear", "Peach", "Banana");
        String length5Element = Iterators.find(list.iterator(), new Predicate<String>() {
            public boolean apply(String input) {
                return input.length() == 5;
            }
        });

        logger.debug(length5Element);
    }

    /**
     * transform方法，对迭代器元素做转换
     */
    @Test
    public void test4() {
        List<String> list = Lists.newArrayList("Apple", "Pear", "Peach", "Banana");
        Iterator<Integer> countIterator = Iterators
                .transform(list.iterator(), new Function<String, Integer>() {
                    public Integer apply(String input) {
                        return input.length();
                    }
                });

        int secondElement = Iterators.get(countIterator, 1);
        logger.debug(secondElement);
    }
}
