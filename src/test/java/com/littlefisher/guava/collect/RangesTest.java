package com.littlefisher.guava.collect;

import com.google.common.collect.BoundType;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class RangesTest {

    private static Logger logger = LogManager.getLogger(RangesTest.class);

    /**
     * 构建区间   区间判断
     */
    @Test
    public void test1() {
        Range<Integer> range = Range.closedOpen(1, 5); //[1,5)
        logger.debug(range.contains(0));

        range = Range.greaterThan(1); //(1,+∞)
        logger.debug(range.contains(0));

        range = Range.lessThan(1); //(-∞,1)
        logger.debug(range.contains(0));

        range = Range.atLeast(1); //(-∞,1]
        logger.debug(range.contains(1));

        range = Range.range(1, BoundType.OPEN, 5, BoundType.CLOSED); //(1,5]
        logger.debug(range.contains(5));

        range = Range.downTo(5, BoundType.CLOSED); //[5,+∞)
        logger.debug(range.contains(5));

        range = Range.upTo(5, BoundType.OPEN); //(-∞,5)
        logger.debug(range.contains(5));
    }

    /**
     * 查询运算
     */
    @Test
    public void test2() {
        Range.openClosed(4, 4).isEmpty(); // returns true
        Range.closed(4, 4).isEmpty(); // returns false
        Range.open(4, 4).isEmpty(); // Range.open throws IllegalArgumentException
        Range.closed(3, 10).lowerEndpoint(); // returns 3
        Range.open(3, 10).lowerEndpoint(); // returns 3
        Range.closed(3, 10).lowerBoundType(); // returns CLOSED
        Range.open(3, 10).upperBoundType(); // returns OPEN
    }

    /**
     * 关系运算
     */
    @Test
    public void test3() {
        //包含
        boolean flag = Range.openClosed(1, 5).encloses(Range.openClosed(2, 5));
        logger.debug(flag);

        //是否相连(是否有交集)
        flag = Range.openClosed(1, 5).isConnected(Range.openClosed(2, 3));
        logger.debug(flag);

        //交集
        Range<Integer> range = Range.openClosed(1, 5).intersection(Range.openClosed(2, 3));
        logger.debug(range);

        //并集
        Range<Integer> range1 = Range.openClosed(1, 5).span(Range.openClosed(2, 9));
        logger.debug(range1);
    }

    /**
     * 离散域
     */
    @Test
    public void test4() {
        DiscreteDomain<Integer> range = DiscreteDomain.integers();
        logger.debug(range.distance(1, 5));
        logger.debug(range.previous(2));
        logger.debug(range.next(3));
    }
}
