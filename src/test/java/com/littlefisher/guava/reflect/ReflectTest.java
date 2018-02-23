package com.littlefisher.guava.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;

public class ReflectTest {

    private static Logger logger = LogManager.getLogger(ReflectTest.class);

    /**
     * guava反射包中的TypeToken类是用来解决java运行时泛型类型被擦除的问题的，
     * 有点不好理解，我们通过一个例子来认识什么是泛型的运行时类型擦除。
     */
    @Test
    public void test1() {
        ArrayList<String> stringList = Lists.newArrayList();
        ArrayList<Integer> intList = Lists.newArrayList();
        logger.debug("intList type is " + intList.getClass());
        logger.debug("stringList type is " + stringList.getClass());
        //判断list的类型是否是同一个类型
        logger.debug(stringList.getClass()
                .isAssignableFrom(intList.getClass()));

        TypeToken<ArrayList<String>> typeToken = new TypeToken<ArrayList<String>>() {
        };
        TypeToken<?> genericTypeToken = typeToken.resolveType(
                ArrayList.class.getTypeParameters()[0]);
        logger.debug(genericTypeToken.getType());
    }

    /**
     * jdk的动态代理
     * guava的动态代理
     */
    @Test
    public void test2() {
        InvocationHandler invocationHandler = new MyInvocationHandler();

        // Guava Dynamic Proxy implement
        IFoo foo = Reflection.newProxy(IFoo.class, invocationHandler);
        foo.doSomething();

        //jdk Dynamic proxy implement
        IFoo jdkFoo = (IFoo) Proxy.newProxyInstance(IFoo.class.getClassLoader(),
                new Class<?>[] { IFoo.class }, invocationHandler);
        jdkFoo.doSomething();
    }

    public static class MyInvocationHandler implements InvocationHandler {

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            logger.debug("proxy println something");
            return null;
        }
    }

    public interface IFoo {

        void doSomething();
    }
}

