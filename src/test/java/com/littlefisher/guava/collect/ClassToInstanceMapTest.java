package com.littlefisher.guava.collect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;
import com.littlefisher.guava.model.Person;

public class ClassToInstanceMapTest {

    private static Logger logger = LogManager.getLogger(ClassToInstanceMapTest.class);

    /**
     * 暂时未想到应用场景
     */
    @Test
    public void test() {
        ClassToInstanceMap<Person> classToInstanceMap = MutableClassToInstanceMap.create();

        Person person = new Person(1, 20, "abc", "46464", 1);

        classToInstanceMap.putInstance(Person.class, person);

        //        logger.debug("string:" + classToInstanceMap.getInstance(String.class));
        //        logger.debug("integer:" + classToInstanceMap.getInstance(Integer.class));

        Person instanceMapToClassPerson = classToInstanceMap.getInstance(Person.class);
        logger.debug("person age: {}", instanceMapToClassPerson::getAge);
        logger.debug(instanceMapToClassPerson.toString());
    }
}

