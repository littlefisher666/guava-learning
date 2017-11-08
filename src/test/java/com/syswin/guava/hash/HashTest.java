package com.syswin.guava.hash;

import java.util.List;

import junit.framework.TestCase;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.hash.PrimitiveSink;

public class HashTest extends TestCase{

	/**
	 * 通过md5函数来获取散列值
	 */
	public void test1(){
		Long id = 12l;
		String name = "baoq";
		HashFunction function = Hashing.md5();
		HashCode hc = function.newHasher().putLong(id).putString(name, Charsets.UTF_8).hash();
		System.out.println(hc.hashCode());
	}
	
	/**
	 * 将对象类型封装成原生字段值，从而写入PrimitiveSink。
	 */
	public void test2(){
		Funnel<Person> personFunnel = new Funnel<Person>() {
			public void funnel(Person from, PrimitiveSink into) {
				into.putInt(from.id);
				into.putString(from.firstName, Charsets.UTF_8);
				into.putString(from.lastName, Charsets.UTF_8);
				into.putInt(from.birthYear);
			}
		};
		Person person = new Person(1,"bao","qiang",21);
		HashFunction function = Hashing.md5();
		HashCode hc = function.newHasher().putObject(person, personFunnel).hash();
		System.out.println(hc.hashCode());
	}
	
	/**
	 * 布鲁姆过滤器
	 */
	public void test3(){
		Person baoq = new Person(1,"bao","qiang",21);
		List<Person> friendsList = Lists.newArrayList(new Person(2,"bao3","qiang",21),new Person(1,"bao32","qiang",21));
		friendsList.add(baoq);
		Funnel<Person> personFunnel = new Funnel<Person>() {
			public void funnel(Person from, PrimitiveSink into) {
				into.putInt(from.id);
				into.putString(from.firstName, Charsets.UTF_8);
				into.putString(from.lastName, Charsets.UTF_8);
				into.putInt(from.birthYear);
			}
		};
		BloomFilter<Person> friends = BloomFilter.create(personFunnel, 500, 0.01);
		for(Person friend : friendsList) {
		    friends.put(friend);
		}
		// 很久以后
		if (friends.mightContain(baoq)) {
		    //dude不是朋友还运行到这里的概率为1%
		    //在这儿，我们可以在做进一步精确检查的同时触发一些异步加载
		}
	}
	
	
}

class Person{
	Person(int id,String firstName,String lastName,int birthYear){
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
