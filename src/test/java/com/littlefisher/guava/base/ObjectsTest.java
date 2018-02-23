package com.littlefisher.guava.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.google.common.base.Objects;
import com.littlefisher.guava.model.Student;

public class ObjectsTest {

    private static Logger logger = LogManager.getLogger(ObjectsTest.class);

    /**
     * 我们在开发中经常会需要比较两个对象是否相等，
     * 这时候我们需要考虑比较的两个对象是否为null，
     * 然后再调用equals方法来比较是否相等，
     * google guava库的com.google.common.base.Objects类
     * 提供了一个静态方法equals可以避免我们自己做是否为空的判断，示例如下：
     */
    @Test
    public void test1() {
        Object a = null;
        Object b = new Object();
        boolean aEqualsB = Objects.equal(a, b);
        logger.debug("aEqualsB: {}", aEqualsB);
    }

    /**
     * 提供了方便的重写toString()方法的机制
     */
    @Test
    public void test2() {
        Student bq = new Student();
        bq.setId(1);
        bq.setName("bq");
        bq.setAge(22);
        logger.debug(bq.toString());
    }

}
