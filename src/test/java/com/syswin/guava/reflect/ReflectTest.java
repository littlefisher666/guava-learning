package com.syswin.guava.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

import junit.framework.TestCase;

import com.google.common.collect.Lists;
import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;

public class ReflectTest extends TestCase{
	
	/**
	 * guava反射包中的TypeToken类是用来解决java运行时泛型类型被擦除的问题的，
	 * 有点不好理解，我们通过一个例子来认识什么是泛型的运行时类型擦除。
	 */
	public void test1(){
		ArrayList<String> stringList = Lists.newArrayList();
        ArrayList<Integer> intList = Lists.newArrayList();
        System.out.println("intList type is " + intList.getClass());
        System.out.println("stringList type is " + stringList.getClass());
        //判断list的类型是否是同一个类型
        System.out.println(stringList.getClass().isAssignableFrom(intList.getClass()));
        
        
        TypeToken<ArrayList<String>> typeToken = new TypeToken<ArrayList<String>>() {};
        TypeToken<?> genericTypeToken = typeToken.resolveType(ArrayList.class.getTypeParameters()[0]);
        System.out.println(genericTypeToken.getType());
	}

	
	/**
	 * jdk的动态代理
	 * guava的动态代理
	 */
	public void test2(){
		InvocationHandler invocationHandler = new MyInvocationHandler();

        // Guava Dynamic Proxy implement
        IFoo foo = Reflection.newProxy(IFoo.class, invocationHandler);
        foo.doSomething();
        
        //jdk Dynamic proxy implement
        IFoo jdkFoo = (IFoo) Proxy.newProxyInstance(
                IFoo.class.getClassLoader(),
                new Class<?>[]{IFoo.class},
                invocationHandler);
        jdkFoo.doSomething();
	}
	
	public static class MyInvocationHandler implements InvocationHandler{
	    public Object invoke(Object proxy, Method method, Object[] args)
	                throws Throwable {
	            System.out.println("proxy println something");
	            return null;
	        }
	    }

	    public static interface IFoo {
	        void doSomething();
	    }
	}

