package com.littlefisher.guava.model;

import com.google.common.base.MoreObjects;

/**
 *
 * @author jinyn22648
 * @version $$Id: Student.java, v 0.1 2018/2/23 上午9:48 jinyn22648 Exp $$
 */
public class Student implements Comparable<Student> {

    private Integer id;

    private String name;

    private Integer age;

    private String sex;

    private String lastName;

    private String firstName;

    private int zipCode;

    public Student() {
    }

    public Student(String name, Integer age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this.getClass()).add("id", id).add("name", name)
                .add("age", age).omitNullValues().toString(); //忽略空值
    }

    //jdk
    @Override
    public int compareTo(Student other) {
        int cmp = lastName.compareTo(other.lastName);
        if (cmp != 0) {
            return cmp;
        }
        cmp = firstName.compareTo(other.firstName);
        if (cmp != 0) {
            return cmp;
        }
        return Integer.compare(zipCode, other.zipCode);
    }

    //	//guava比较优雅
    //	public int compareTo(Student other) {
    //		return ComparisonChain.start()
    //	            .compare(this.lastName, other.lastName)
    //	            .compare(this.firstName, other.firstName)
    //	            .compare(this.zipCode, other.zipCode, Ordering.natural().nullsLast())
    //	            .result();
    //
    //	}
}
