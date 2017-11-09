package com.littlefisher.guava.base;

import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public class SplitterTest extends TestCase {

    /**
     * 通过正则表达式进行拆分
     */
    public void test1() {
        String str = "this is my java program!";
        Splitter split = Splitter.onPattern("[o| ]+"); //支持正则
        Iterable<String> it = split.split(str);

        split.omitEmptyStrings(); //排除空字符
        split.trimResults(); //对截取后字符串进行trim
        for (String temp : it) {
            System.out.println(temp);
        }
    }

    /**
     * Splitter做二次拆分
     */
    public void test2() {
        String toSplitString = "a=b;c=d,e=f";
        Map<String, String> kvs = Splitter.onPattern("[,;]{1,}").withKeyValueSeparator('=')
                .split(toSplitString);
        for (Map.Entry<String, String> entry : kvs.entrySet()) {
            System.out.println(String.format("%s=%s", entry.getKey(), entry.getValue()));
        }
    }

    /**
     *
     */
    public void test3() {
        //limit(int) 限制拆分出来的数量
        String str = "hello is world";
        List<String> list = Lists.newArrayList(Splitter.on(" ").limit(2).split(str));
        System.out.println(list);
        list = Splitter.on(" ").limit(3).splitToList(str);
        System.out.println(list);
    }

    /**
     *
     */
    public void test4() {

    }

    /**
     *
     */
    public void test5() {

    }

    public void test6() {

    }

    public void test7() {

    }

}
