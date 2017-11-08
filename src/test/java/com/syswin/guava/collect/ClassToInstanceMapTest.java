package com.syswin.guava.collect;

import junit.framework.TestCase;

import com.google.common.base.Objects;
import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;

public class ClassToInstanceMapTest extends TestCase{

	public void test(){
		ClassToInstanceMap<Person> classToInstanceMap =MutableClassToInstanceMap.create();

		Person person= new Person(1,20,"abc","46464",1);

		classToInstanceMap.putInstance(Person.class,person);


//		 System.out.println("string:"+classToInstanceMap.getInstance(String.class));
//		 System.out.println("integer:" + classToInstanceMap.getInstance(Integer.class));

		Person person1=classToInstanceMap.getInstance(Person.class);
		System.out.println(person1.toString());
	}
}

class Person{
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
	Person(int id,int age,String name,String tel,int sex){
		this.id = id;
		this.age = age;
		this.tel = tel;
		this.sex = sex;
		this.name = name;
	}
	 public String toString() {
         return Objects.toStringHelper(this.getClass())
                 .add("id", id)
                 .add("name", name)
                 .add("age", age)
                 .add("sex", sex)
                 .add("tel", tel)
                 .omitNullValues().toString(); //忽略空值
     }
}
