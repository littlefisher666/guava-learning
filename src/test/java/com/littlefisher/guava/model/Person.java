package com.littlefisher.guava.model;

import com.google.common.base.MoreObjects;

/**
 *
 * @author jinyn22648
 * @version $$Id: Person.java, v 0.1 2018/2/23 上午9:51 jinyn22648 Exp $$
 */
public class Person {

    private int id;

    private int age;

    private String name;

    private String tel;

    private int sex;

    private String firstName;

    private String lastName;

    private int birthYear;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Person(int id, int age, String name, String tel, int sex) {
        this.id = id;
        this.age = age;
        this.tel = tel;
        this.sex = sex;
        this.name = name;
    }

    public Person(int id, String firstName, String lastName, int birthYear) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this.getClass())
                .add("id", id)
                .add("name", name)
                .add("age", age)
                .add("sex", sex)
                .add("tel", tel)
                .omitNullValues()
                .toString(); //忽略空值
    }
}
