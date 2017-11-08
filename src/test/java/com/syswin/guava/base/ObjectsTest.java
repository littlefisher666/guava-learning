package com.syswin.guava.base;

import junit.framework.TestCase;

import com.google.common.base.Objects;


public class ObjectsTest extends TestCase{

	/**
	 * 我们在开发中经常会需要比较两个对象是否相等，
	 * 这时候我们需要考虑比较的两个对象是否为null，
	 * 然后再调用equals方法来比较是否相等，
	 * google guava库的com.google.common.base.Objects类
	 * 提供了一个静态方法equals可以避免我们自己做是否为空的判断，示例如下：
	 */
	public void test1(){
		Object a = null;
        Object b = new Object();
        boolean aEqualsB = Objects.equal(a, b);
        System.out.println(aEqualsB);
	}
	
	/**
	 * 提供了方便的重写toString()方法的机制
	 */
	public void test2(){
		Student bq = new Student();
		bq.setId(1);
		bq.setName("bq");
		bq.setAge(22);
        System.out.println(bq.toString());
	}
	
	public static class Student {
        private int id;
        private String name;
        private int age;

        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }

        public String toString() {
            return Objects.toStringHelper(this.getClass())
                    .add("id", id)
                    .add("name", name)
                    .add("age", age)
                    .omitNullValues().toString(); //忽略空值
        }
    }
}
