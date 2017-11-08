package com.syswin.guava.base;

import junit.framework.TestCase;

import com.google.common.base.Preconditions;

public class PreconditionsTest extends TestCase{

	/**
	 * google guava的base包中提供的Preconditions类用来方便的做参数的校验
	 * 
	 *  checkArgument 接受一个boolean类型的参数和一个可选的errorMsg参数，这个方法用来判断参数是否符合某种条件，符合什么条件google guava不关心，在不符合条件时会抛出IllegalArgumentException异常
		checkState 和checkArgument参数和实现基本相同，从字面意思上我们也可以知道这个方法是用来判断状态是否正确的，如果状态不正确会抛出IllegalStateException异常
		checkNotNull方法用来判断参数是否不是null，如果为null则会抛出NullPointerException空指针异常
		checkElementIndex方法用来判断用户传入的数组下标或者list索引位置，是否是合法的，如果不合法会抛出IndexOutOfBoundsException
		checkPositionIndexes方法的作用和checkElementIndex方法相似，只是此方法的索引范围是从0到size包括size，而上面的方法不包括size。
	 */
	
	public void test1(){
		doSomething(null, 19, "hello world, hello java");
	}
	
	public void doSomething(String name, int age, String desc) {
        
		//只支持%s指示符
		Preconditions.checkNotNull(name, "name %s may not be null","baoq");
        Preconditions.checkArgument(age >= 18 && age < 99, "age must in range (18,99)");
        Preconditions.checkArgument(desc !=null && desc.length() < 10, "desc too long, max length is ", 10);
        Preconditions.checkElementIndex(10, 1);
        //do things
    }
}
