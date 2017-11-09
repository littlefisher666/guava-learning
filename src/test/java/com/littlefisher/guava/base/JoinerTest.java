package com.littlefisher.guava.base;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import com.google.common.base.Joiner;

public class JoinerTest extends TestCase {

    /**
     * 通过空格将hello和world合并起来
     */
    public void test1() {
        String joinResult = Joiner.on(" ").join(new String[] { "hello", "world" });
        System.out.println(joinResult);
    }

    /**
     * 通过Joiner进行二次合并
     */
    public void test2() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("a", "b");
        map.put("c", "d");
        String mapJoinResult = Joiner.on(",").withKeyValueSeparator("=").join(map);
        System.out.println(mapJoinResult);
    }

    /**
     * 每当我想起将string数组或集合打印出来需要拼接逗号，就感觉特别恶心
     * 有了guava以后，就变得easy多了
     */
    public void test3() {
        String[] strings = new String[] { "ad", "s", "e", null, "ds" };
        //		String str = Joiner.on(",").join(strings);
        //		System.out.println(str);

        //对于null进行打印NA来替换
        //		String str = Joiner.on(",").useForNull("NA").join(strings);
        //		System.out.println(str);

        //把null滤掉:
        String str = Joiner.on(",").skipNulls().join(strings);
        System.out.println(str);
    }
}
