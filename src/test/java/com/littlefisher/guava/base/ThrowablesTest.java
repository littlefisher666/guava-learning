package com.littlefisher.guava.base;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.google.common.base.Throwables;

public class ThrowablesTest {

    private static Logger logger = LogManager.getLogger(ThrowablesTest.class);

    /**
     * guava类库中的Throwables提供了一些异常处理的静态方法，
     * 这些方法的从功能上分为两类，一类是帮你抛出异常，另外一类是帮你处理异常。
     */
    @Test
    public void test1() throws SQLException {
        try {
            doSomething();
        } catch (Throwable throwable) {
            // 如果异常的类型是SQLException，那么抛出这个异常
            Throwables.throwIfInstanceOf(throwable, SQLException.class);
            // 如果异常是Error类型，那么抛出这个类型，否则将抛出RuntimeException，
            // 我们知道RuntimeException是不需要在throws中声明的。
            //            Throwables.propagate(throwable);
            Throwables.throwIfUnchecked(throwable);

            getThrowableMsg(throwable);
        }
    }

    /*
     * Throwables类还为我们提供了一些方便的异常处理帮助方法:
     *  我们可以通过Throwables.getRooCause(Throwable)获得根异常
     *  可以使用getCausalChain方法获得异常的列表
     *  可以通过getStackTraceAsString获得异常堆栈的字符串
     */
    private void getThrowableMsg(Throwable throwable) {
        Throwable rootThrowable = Throwables.getRootCause(throwable);
        logger.error(rootThrowable);
        List<Throwable> throwableList = Throwables.getCausalChain(throwable);
        logger.error(throwableList);
        String throwableStr = Throwables.getStackTraceAsString(throwable);
        logger.error(throwableStr);
    }

    private void doSomething() throws Throwable {
    }

}
