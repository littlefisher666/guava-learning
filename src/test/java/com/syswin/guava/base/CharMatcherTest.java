package com.syswin.guava.base;

import junit.framework.TestCase;

import com.google.common.base.CharMatcher;

public class CharMatcherTest extends TestCase{

	public void test1(){
		String str = "this is 1 2 3 hello world  test data!";
		//移除control字符
		String noControl = CharMatcher.JAVA_ISO_CONTROL.removeFrom(str); 
		System.out.println(noControl);
		//只保留数字字符
		String theDigits = CharMatcher.DIGIT.retainFrom(str); 
		System.out.println(theDigits);
		//去除两端的空格，并把中间的连续空格替换成单个空格
		String spaced = CharMatcher.WHITESPACE.trimAndCollapseFrom(str, ' ');
		System.out.println(spaced);
		//用*号替换所有数字
		String noDigits = CharMatcher.JAVA_DIGIT.replaceFrom(str, "*"); 
		System.out.println(noDigits);
		// 只保留数字和小写字母
		String lowerAndDigit = CharMatcher.JAVA_DIGIT.or(CharMatcher.JAVA_LOWER_CASE).retainFrom(str);
		System.out.println(lowerAndDigit);
	}
}
