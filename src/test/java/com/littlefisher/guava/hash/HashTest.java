package com.littlefisher.guava.hash;

import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class HashTest {

    private static Logger logger = LogManager.getLogger(HashTest.class);

    /**
     * 通过md5函数来获取散列值
     */
    @Test
    public void test1() {
        Long id = 12L;
        String name = "baoq";
        HashFunction function = Hashing.md5();
        HashCode hc = function.newHasher().putLong(id).putString(name, Charsets.UTF_8).hash();
        logger.debug(hc.hashCode());
    }

    /**
     * 将对象类型封装成原生字段值，从而写入PrimitiveSink。
     */
    @Test
    public void test2() {
        Funnel<Person> personFunnel = getPerson();
        Person person = new Person(1, "bao", "qiang", 21);
        HashFunction function = Hashing.md5();
        HashCode hc = function.newHasher().putObject(person, personFunnel).hash();
        logger.debug(hc.hashCode());
    }

    private Funnel<Person> getPerson() {
        return (Funnel<Person>) (from, into) -> {
            into.putInt(from.id);
            into.putString(from.firstName, Charsets.UTF_8);
            into.putString(from.lastName, Charsets.UTF_8);
            into.putInt(from.birthYear);
        };
    }

    /**
     * 布鲁姆过滤器
     */
    @Test
    public void test3() {
        Person baoq = new Person(1, "bao", "qiang", 21);
        List<Person> friendsList = Lists.newArrayList(new Person(2, "bao3", "qiang", 21),
                new Person(1, "bao32", "qiang", 21));
        friendsList.add(baoq);
        Funnel<Person> personFunnel = getPerson();
        BloomFilter<Person> friends = BloomFilter.create(personFunnel, 500, 0.01);
        friendsList.forEach(friends::put);

        // 很久以后
        if (friends.mightContain(baoq)) {
            //dude不是朋友还运行到这里的概率为1%
            //在这儿，我们可以在做进一步精确检查的同时触发一些异步加载
        }
    }

}

class Person {

    Person(int id, String firstName, String lastName, int birthYear) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
    }

    int id;

    String firstName;

    String lastName;

    int birthYear;
}
