package com.littlefisher.guava.collect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;

public class ClassToInstanceMapTest {

    private static Logger logger = LogManager.getLogger(ClassToInstanceMapTest.class);

    @Test
    public void test() {
        ClassToInstanceMap<Person> classToInstanceMap = MutableClassToInstanceMap.create();

        Person person = new Person(1, 20, "abc", "46464", 1);

        classToInstanceMap.putInstance(Person.class, person);

//        logger.debug("string:" + classToInstanceMap.getInstance(String.class));
//        logger.debug("integer:" + classToInstanceMap.getInstance(Integer.class));

        Person person1 = classToInstanceMap.getInstance(Person.class);
        logger.debug("person age: {}", person1::getAge);
        logger.debug(person1.toString());
    }
}

class Person {

    private int id;

    private int age;

    private String name;

    private String tel;

    private int sex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    Person(int id, int age, String name, String tel, int sex) {
        this.id = id;
        this.age = age;
        this.tel = tel;
        this.sex = sex;
        this.name = name;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this.getClass()).add("id", id).add("name", name)
                .add("age", age).add("sex", sex).add("tel", tel).omitNullValues().toString(); //忽略空值
    }
}
