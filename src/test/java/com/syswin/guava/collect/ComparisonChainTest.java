package com.syswin.guava.collect;

import junit.framework.TestCase;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

public class ComparisonChainTest extends TestCase{

}

class Student implements Comparable<Student>{

	private String lastName;
	private String firstName;
	private int zipCode;

	//jdk
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

