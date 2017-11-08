package com.syswin.guava.collect;

import java.util.List;

import junit.framework.TestCase;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

public class OrderingTest extends TestCase{

	/**
	 * Ordering进行排序
	 */
	public void test1(){
		List<Integer> numbers = Lists.newArrayList(30, 20, 60, 80, 10);

		System.out.println(Ordering.natural().sortedCopy(numbers)); //10,20,30,60,80

		System.out.println(Ordering.natural().reverse().sortedCopy(numbers)); //80,60,30,20,10

		System.out.println(Ordering.natural().min(numbers)); //10

		System.out.println(Ordering.natural().max(numbers)); //80

		Lists.newArrayList(30, 20, 60, 80, null, 10);

		System.out.println(Ordering.natural().nullsLast().sortedCopy(numbers)); //10, 20,30,60,80,null

		System.out.println(Ordering.natural().nullsFirst().sortedCopy(numbers)); //null,10,20,30,60,80
	}
	
	public void test2(){
		List<Person> personList=Lists.newArrayList(
			new Person(3, 1, "abc", "46546", 0),
			new Person(2, 3, "ab", "46546", 0),
			new Person(5, 12, "ade", "46546",0),
			new Person(1, 4, "a", "46546", 1),
			new Person(6, 41, "acc", "46546", 1),
			new Person(4, 2, "aef", "46546", 1),
			new Person(7, 31, "add", "46546",0)
			);

		Ordering<Person> byAge=new Ordering<Person>() {
			public int compare( Person left, Person right) {
				return right.getAge()-left.getAge();
			}
		};

		for(Person p: byAge.immutableSortedCopy(personList))
		{
			System.out.println(p);
		}
	}
	
	public void test3(){
		List<String> list = Lists.newArrayList();
        list.add("peida");
        list.add("jerry");
        list.add("harry");
        list.add("eva");
        list.add("jhon");
        list.add("neron");
        
        System.out.println("list:"+ list);

        Ordering<String> naturalOrdering = Ordering.natural();        
        Ordering<Object> usingToStringOrdering = Ordering.usingToString();
        Ordering<Object> arbitraryOrdering = Ordering.arbitrary();
        
        //使用Comparable类型的自然顺序， 例如：整数从小到大，字符串是按字典顺序;
        System.out.println("naturalOrdering:"+ naturalOrdering.sortedCopy(list));     
        //使用toString()返回的字符串按字典顺序进行排序；
        System.out.println("usingToStringOrdering:"+ usingToStringOrdering.sortedCopy(list));        
        //返回一个所有对象的任意顺序， 即compare(a, b) == 0 就是 a == b (identity equality)。 
        //本身的排序是没有任何含义， 但是在VM的生命周期是一个常量。
        System.out.println("arbitraryOrdering:"+ arbitraryOrdering.sortedCopy(list));
	}
	
	/**
	 * reverse(): 返回与当前Ordering相反的排序:
　　	   nullsFirst(): 返回一个将null放在non-null元素之前的Ordering，其他的和原始的Ordering一样；
　　             nullsLast()：返回一个将null放在non-null元素之后的Ordering，其他的和原始的Ordering一样；
　　            //俗话说如果当前ordering比较结果是平局, 用另外一个Comparator做加时赛.       
  	   compound(Comparator)：返回一个使用Comparator的Ordering，Comparator作为第二排序元素，例如对bug列表进行排序，先根据bug的级别，再根据优先级进行排序；
　　            lexicographical()：返回一个按照字典元素迭代的Ordering；
　　            onResultOf(Function)：将function应用在各个元素上之后, 在使用原始ordering进行排序；
　　            greatestOf(Iterable iterable, int k)：返回指定的第k个可迭代的最大的元素，按照这个从最大到最小的顺序。是不稳定的。
　　            leastOf(Iterable<E> iterable,int k)：返回指定的第k个可迭代的最小的元素，按照这个从最小到最大的顺序。是不稳定的。
　　            isOrdered(Iterable)：是否有序，Iterable不能少于2个元素。
　　            isStrictlyOrdered(Iterable)：是否严格有序。请注意，Iterable不能少于两个元素。
　　            sortedCopy(Iterable)：返回指定的元素作为一个列表的排序副本。
	 */
	public void test4(){
		List<String> list = Lists.newArrayList();
        list.add("peida");
        list.add("jerry");
        list.add("harry");
        list.add("eva");
        list.add("jhon");
        list.add("neron");
        
        System.out.println("list:"+ list);
        
        Ordering<String> naturalOrdering = Ordering.natural();
        System.out.println("naturalOrdering:"+ naturalOrdering.sortedCopy(list));    

        List<Integer> listReduce= Lists.newArrayList();
        for(int i=9;i>0;i--){
            listReduce.add(i);
        }
        
        List<Integer> listtest= Lists.newArrayList();
        listtest.add(1);
        listtest.add(1);
        listtest.add(1);
        listtest.add(2);
        
        
        Ordering<Integer> naturalIntReduceOrdering = Ordering.natural();
        
        System.out.println("listtest:"+ listtest);
        System.out.println(naturalIntReduceOrdering.isOrdered(listtest));
        System.out.println(naturalIntReduceOrdering.isStrictlyOrdered(listtest));
        
        
        System.out.println("naturalIntReduceOrdering:"+ naturalIntReduceOrdering.sortedCopy(listReduce));
        System.out.println("listReduce:"+ listReduce);
        
        
        System.out.println(naturalIntReduceOrdering.isOrdered(naturalIntReduceOrdering.sortedCopy(listReduce)));
        System.out.println(naturalIntReduceOrdering.isStrictlyOrdered(naturalIntReduceOrdering.sortedCopy(listReduce)));
        

        Ordering<String> natural = Ordering.natural();
              
        List<String> abc = ImmutableList.of("a", "b", "c");
        System.out.println(natural.isOrdered(abc));
        System.out.println(natural.isStrictlyOrdered(abc));
        
        System.out.println("isOrdered reverse :"+ natural.reverse().isOrdered(abc));
 
        List<String> cba = ImmutableList.of("c", "b", "a");
        System.out.println(natural.isOrdered(cba));
        System.out.println(natural.isStrictlyOrdered(cba));
        System.out.println(cba = natural.sortedCopy(cba));
        
        System.out.println("max:"+natural.max(cba));
        System.out.println("min:"+natural.min(cba));
        
        System.out.println("leastOf:"+natural.leastOf(cba, 2));
        System.out.println("naturalOrdering:"+ naturalOrdering.sortedCopy(list));    
        System.out.println("leastOf list:"+naturalOrdering.leastOf(list, 3));
        System.out.println("greatestOf:"+naturalOrdering.greatestOf(list, 3));
        System.out.println("reverse list :"+ naturalOrdering.reverse().sortedCopy(list));    
        System.out.println("isOrdered list :"+ naturalOrdering.isOrdered(list));
        System.out.println("isOrdered list :"+ naturalOrdering.reverse().isOrdered(list));
        list.add(null);
        System.out.println(" add null list:"+list);
        System.out.println("nullsFirst list :"+ naturalOrdering.nullsFirst().sortedCopy(list));
        System.out.println("nullsLast list :"+ naturalOrdering.nullsLast().sortedCopy(list));
	}
}

