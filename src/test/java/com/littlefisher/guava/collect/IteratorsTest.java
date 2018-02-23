package com.littlefisher.guava.collect;

import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.littlefisher.guava.model.Student;

public class IteratorsTest {

    private static Logger logger = LogManager.getLogger(IteratorsTest.class);

    /**
     * Iterators.all
     * Iterators.any
     * Iterators.get
     *
     * all方法的第一个参数是Iterator，第二个参数是Predicate<String>的实现，
     * 这个方法的意义是不需要我们自己去写while循环了，他的内部实现中帮我们做了循环，
     * 把循环体中的条件判断抽象出来了。
     */
    @Test
    public void test1() {
        List<String> list = Lists.newArrayList("Apple", "Pear", "Peach", "Banana");

        boolean allIsStartsWithP = Iterators.all(list.iterator(),
                input -> input != null && input.startsWith("P"));
        boolean anyIsStartsWithP = Iterators.any(list.iterator(),
                input -> input != null && input.startsWith("P"));
        logger.debug("all result == {}", allIsStartsWithP);
        logger.debug("any result == {}", anyIsStartsWithP);

        String secondElement = Iterators.get(list.iterator(), 1);
        logger.debug("secondElement: {}", secondElement);
    }

    /**
     *
     * Iterators.filter
     *
     * filter方法过滤符合条件的项
     */
    @Test
    public void test2() {
        List<String> list = Lists.newArrayList("Apple", "Pear", "Peach", "Banana");
        Iterator<String> startPElements = Iterators.filter(list.iterator(),
                input -> input != null && input.startsWith("P"));
        String secondElement = Iterators.get(startPElements, 1);
        logger.debug("secondElement: {}", secondElement);
    }

    /**
     * Iterators.find
     *
     * find方法返回符合条件的第一个元素
     * 该方法有重载方法，入参为3个，第三个为默认值。如果使用不带默认值的方法，未查到结果时会报异常
     */
    @Test
    public void test3() {
        List<String> list = Lists.newArrayList("Apple", "Pear", "Peach", "Banana");
        String length5Element = Iterators.find(list.iterator(),
                input -> input != null && input.length() == 5, null);

        logger.debug("length5Element: {}", length5Element);
    }

    /**
     * Iterators.transform
     *
     * 最常用的方式，是把{@link List<Student>}这样的集合，仅提取{@link Student}的name，这样的操作
     * 实际应用例如{@code List<CreditCreditApply>}提取creditApplyId集合为{@link List<String>}
     *
     * transform方法，对迭代器元素做转换
     */
    @Test
    public void test4() {
        List<Student> studentList = Lists.newArrayList(new Student("Apple", 11, "male"),
                new Student("Pear", 12, "female"), new Student("Peach", 13, "male"),
                new Student("Banana", 14, "female"));

        Iterator<String> studentNameIterator = Iterators.transform(studentList.iterator(),
                Student::getName);

        // 获取子集合
        List<String> studentNameList = Lists.newArrayList(studentNameIterator);
        logger.debug("studentNameList: {}", studentNameList);

        String secondStudentName = Iterators.get(studentNameIterator, 1);
        logger.debug("secondStudentName: {}", secondStudentName);
    }

    /**
     *
     * Iterators.removeIf
     *
     * 迭代器中删除元素。原生写法是不能在for循环中删除元素的
     */
    @Test
    public void test5() {
        List<Student> studentList = Lists.newArrayList(new Student("Apple", 11, "male"),
                new Student("Pear", 12, "female"), new Student("Peach", 13, "male"),
                new Student("Banana", 14, "female"));

        Iterators.removeIf(studentList.iterator(),
                student -> student != null && "female".equals(student.getSex()));
    }

}
