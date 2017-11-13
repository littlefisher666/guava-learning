package com.littlefisher.guava.base;

import junit.framework.TestCase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.google.common.base.Optional;
import com.littlefisher.guava.base.ObjectsTest.Student;

public class OptionalTest {
    
    private static Logger logger = LogManager.getLogger(OptionalTest.class);

    @Test
    public void test4() {
        // 创建Optional对象
        Optional<String> op1 = Optional.absent();
        logger.debug(op1); //Optional.absent()
        Optional<String> op2 = Optional.fromNullable(null);
        logger.debug(op2);//Optional.absent()
        Optional<String> op3 = Optional.of(null);
        logger.debug(op3);//创建指定引用的Optional实例，若引用为null则快速失败

        // Optional的非静态方法：
        // boolean isPresent()	如果Optional包含非null的引用（引用存在），返回true
        // T get()	返回Optional所包含的引用，若引用缺失，则抛出java.lang.IllegalStateException
        // T or(T)	返回Optional所包含的引用，若引用缺失，返回指定的值
        // T orNull()	返回Optional所包含的引用，若引用缺失，返回null
        // Set<T> asSet()	返回Optional所包含引用的单例不可变集，如果引用存在，返回一个只有单一元素的集合，如果引用缺失，返回一个空集合。
    }

    /**
     * null会带来很多问题，从开始有null开始有无数程序栽在null的手里，
     * null的含义是不清晰的，检查null在大多数情况下是不得不做的，
     * 而我们又在很多时候忘记了对null做检查，在我们的产品真正投入使用的时候，
     * 空指针异常出现了，这是一种讨厌的情况。
     *
     * 鉴于此google的guava库中提供了Optional接口来使null快速失败，
     * 即在可能为null的对象上做了一层封装，在使用Optional静态方法of时，
     * 如果传入的参数为null就抛出NullPointerException异常。
     */
    @Test
    public void test1() {
        Optional<Student> possibleNull = Optional.of(null);
        possibleNull.get();
    }

    @Test
    public void test2() {
        Optional<Student> possibleNull = Optional.absent();
        Student jim = possibleNull.get();
    }

    /**
     * 举个例子
     * 普通青年和文艺青年分别写一段SayHello的代码，要求如下，当用户名为null时则称呼“火星人”
     */
    @Test
    public void test3() {
        //jdk
        String name = null;
        if (name == null) {
            name = "火星人";
        }
        logger.debug("普通青年说：Hello, " + name);

        name = Optional.fromNullable(name).or("火星人");
        logger.debug("文艺青年说：Hello, " + name);
    }

    /**
     * 测试
     */
    @Test
    public void test() {
        logger.debug(Optional.of(1).of(2).of(3).asSet().size());
    }

    /*
     * 这样使用也会有异常出来，那Optional到底有什么意义呢？

     使用Optional除了赋予null语义，增加了可读性，最大的优点在于它是一种傻瓜式的防护。
     Optional迫使你积极思考引用缺失的情况，因为你必须显式地从Optional获取引用。
     直接使用null很容易让人忘掉某些情形，尽管FindBugs可以帮助查找null相关的问题，
     但是我们还是认为它并不能准确地定位问题根源。

     如同输入参数，方法的返回值也可能是null。和其他人一样，你绝对很可能会忘记别人写
     的方法method(a,b)会返回一个null，就好像当你实现method(a,b)时，
     也很可能忘记输入参数a可以为null。将方法的返回类型指定为Optional，
     也可以迫使调用者思考返回的引用缺失的情形。
     */
}
