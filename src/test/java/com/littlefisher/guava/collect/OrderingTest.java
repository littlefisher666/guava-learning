package com.littlefisher.guava.collect;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.littlefisher.guava.model.Person;

public class OrderingTest {

    private static Logger logger = LogManager.getLogger(OrderingTest.class);

    /**
     * Ordering进行排序
     */
    @Test
    public void test1() {
        List<Integer> numbers = Lists.newArrayList(30, 20, 60, 80, 10);
        //10,20,30,60,80
        logger.debug(Ordering.natural()
                .sortedCopy(numbers));
        //80,60,30,20,10
        logger.debug(Ordering.natural()
                .reverse()
                .sortedCopy(numbers));
        //10
        logger.debug(Ordering.natural()
                .min(numbers));
        //80
        logger.debug(Ordering.natural()
                .max(numbers));

        Lists.newArrayList(30, 20, 60, 80, null, 10);
        //10, 20,30,60,80,null
        logger.debug(Ordering.natural()
                .nullsLast()
                .sortedCopy(numbers));
        //null,10,20,30,60,80
        logger.debug((Ordering.natural()
                .nullsFirst()
                .sortedCopy(numbers)));
    }

    @Test
    public void test2() {
        List<Person> personList = Lists.newArrayList(new Person(3, 1, "abc", "46546", 0),
                new Person(2, 3, "ab", "46546", 0), new Person(5, 12, "ade", "46546", 0),
                new Person(1, 4, "a", "46546", 1), new Person(6, 41, "acc", "46546", 1),
                new Person(4, 2, "aef", "46546", 1), new Person(7, 31, "add", "46546", 0));

        Ordering<Person> byAge = new Ordering<Person>() {
            public int compare(Person left, Person right) {
                return right.getAge() - left.getAge();
            }
        };

        byAge.immutableSortedCopy(personList)
                .forEach(logger::debug);
    }

    @Test
    public void test3() {
        List<String> list = Lists.newArrayList();
        list.add("peida");
        list.add("jerry");
        list.add("harry");
        list.add("eva");
        list.add("jhon");
        list.add("neron");

        logger.debug("list: {}", list);

        Ordering<String> naturalOrdering = Ordering.natural();
        Ordering<Object> usingToStringOrdering = Ordering.usingToString();
        Ordering<Object> arbitraryOrdering = Ordering.arbitrary();

        // 使用Comparable类型的自然顺序， 例如：整数从小到大，字符串是按字典顺序;
        logger.debug("naturalOrdering: {}", naturalOrdering.sortedCopy(list));
        // 使用toString()返回的字符串按字典顺序进行排序；
        logger.debug("usingToStringOrdering: {}", usingToStringOrdering.sortedCopy(list));
        // 返回一个所有对象的任意顺序， 即compare(a, b) == 0 就是 a == b (identity equality)。
        // 本身的排序是没有任何含义， 但是在VM的生命周期是一个常量。
        logger.debug("arbitraryOrdering: {}", arbitraryOrdering.sortedCopy(list));
    }

    /**
     * reverse(): 返回与当前Ordering相反的排序:
     * 　　	   nullsFirst(): 返回一个将null放在non-null元素之前的Ordering，其他的和原始的Ordering一样；
     * 　　             nullsLast()：返回一个将null放在non-null元素之后的Ordering，其他的和原始的Ordering一样；
     * 　　            //俗话说如果当前ordering比较结果是平局, 用另外一个Comparator做加时赛.
     * compound(Comparator)：返回一个使用Comparator的Ordering，Comparator作为第二排序元素，例如对bug列表进行排序，先根据bug的级别，再根据优先级进行排序；
     * 　　            lexicographical()：返回一个按照字典元素迭代的Ordering；
     * 　　            onResultOf(Function)：将function应用在各个元素上之后, 在使用原始ordering进行排序；
     * 　　            greatestOf(Iterable iterable, int k)：返回指定的第k个可迭代的最大的元素，按照这个从最大到最小的顺序。是不稳定的。
     * 　　            leastOf(Iterable<E> iterable,int k)：返回指定的第k个可迭代的最小的元素，按照这个从最小到最大的顺序。是不稳定的。
     * 　　            isOrdered(Iterable)：是否有序，Iterable不能少于2个元素。
     * 　　            isStrictlyOrdered(Iterable)：是否严格有序。请注意，Iterable不能少于两个元素。
     * 　　            sortedCopy(Iterable)：返回指定的元素作为一个列表的排序副本。
     */
    @Test
    public void test4() {
        List<String> list = Lists.newArrayList();
        list.add("peida");
        list.add("jerry");
        list.add("harry");
        list.add("eva");
        list.add("jhon");
        list.add("neron");

        logger.debug("list: {}", list);

        Ordering<String> naturalOrdering = Ordering.natural();
        logger.debug("naturalOrdering: {}", naturalOrdering.sortedCopy(list));

        List<Integer> listReduce = Lists.newArrayList();
        for (int i = 9; i > 0; i--) {
            listReduce.add(i);
        }

        List<Integer> numberList = Lists.newArrayList();
        numberList.add(1);
        numberList.add(1);
        numberList.add(1);
        numberList.add(2);

        Ordering<Integer> naturalIntReduceOrdering = Ordering.natural();

        logger.debug("numberList: {}", numberList);
        logger.debug(naturalIntReduceOrdering.isOrdered(numberList));
        logger.debug(naturalIntReduceOrdering.isStrictlyOrdered(numberList));

        logger.debug("naturalIntReduceOrdering:" + naturalIntReduceOrdering.sortedCopy(listReduce));
        logger.debug("listReduce:" + listReduce);

        logger.debug(naturalIntReduceOrdering.isOrdered(
                naturalIntReduceOrdering.sortedCopy(listReduce)));
        logger.debug(naturalIntReduceOrdering.isStrictlyOrdered(
                naturalIntReduceOrdering.sortedCopy(listReduce)));

        Ordering<String> natural = Ordering.natural();

        List<String> abc = ImmutableList.of("a", "b", "c");
        logger.debug(natural.isOrdered(abc));
        logger.debug(natural.isStrictlyOrdered(abc));

        logger.debug("isOrdered reverse :" + natural.reverse()
                .isOrdered(abc));

        List<String> cba = ImmutableList.of("c", "b", "a");
        logger.debug(natural.isOrdered(cba));
        logger.debug(natural.isStrictlyOrdered(cba));
        logger.debug(cba = natural.sortedCopy(cba));

        logger.debug("max:" + natural.max(cba));
        logger.debug("min:" + natural.min(cba));

        logger.debug("leastOf:" + natural.leastOf(cba, 2));
        logger.debug("naturalOrdering:" + naturalOrdering.sortedCopy(list));
        logger.debug("leastOf list:" + naturalOrdering.leastOf(list, 3));
        logger.debug("greatestOf:" + naturalOrdering.greatestOf(list, 3));
        logger.debug("reverse list :" + naturalOrdering.reverse()
                .sortedCopy(list));
        logger.debug("isOrdered list :" + naturalOrdering.isOrdered(list));
        logger.debug("isOrdered list :" + naturalOrdering.reverse()
                .isOrdered(list));
        list.add(null);
        logger.debug(" add null list:" + list);
        logger.debug("nullsFirst list :" + naturalOrdering.nullsFirst()
                .sortedCopy(list));
        logger.debug("nullsLast list :" + naturalOrdering.nullsLast()
                .sortedCopy(list));
    }
}

