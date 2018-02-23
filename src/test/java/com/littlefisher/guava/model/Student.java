package com.littlefisher.guava.model;

/**
 *
 * @author jinyn22648
 * @version $$Id: Student.java, v 0.1 2018/2/23 上午9:48 jinyn22648 Exp $$
 */
public class Student implements Comparable<Student> {

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
        return "Student{" + "name='" + name + '\'' + ", age=" + age + ", sex='" + sex + '\'' + '}';
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
