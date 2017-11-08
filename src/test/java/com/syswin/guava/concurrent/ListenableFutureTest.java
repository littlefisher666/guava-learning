package com.syswin.guava.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class ListenableFutureTest extends TestCase{

	/**
	 * 首先通过MoreExecutors类的静态方法listeningDecorator方法初始化一个
	 * ListeningExecutorService的方法，
	 * 然后使用此实例的submit方法即可初始化ListenableFuture对象。
	 * 
	 * 我们上文中定义的ListenableFuture要做的工作，在Callable接口的实现类中定义，
	 * 这里只是休眠了1秒钟然后返回一个数字7.
	 */
	public void test1(){
		ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        final ListenableFuture<Integer> listenableFuture = executorService.submit(new Callable<Integer>() {
            public Integer call() throws Exception {
                System.out.println("call execute..");
                TimeUnit.SECONDS.sleep(1);
                return 7;
            }
        });
        /**
         * 有了ListenableFuture实例，有两种方法可以执行此Future并执行Future完成之后的回调函数。
         */
        
        //方法一：通过ListenableFuture的addListener方法
        listenableFuture.addListener(new Runnable() {
            public void run() {
                try {
                    System.out.println("get listenable future's result " + listenableFuture.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }, executorService);
        
        
        //通过Futures的静态方法addCallback给ListenableFuture添加回调函数
        Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {
            public void onSuccess(Integer result) {
                System.out.println("get listenable future's result with callback " + result);
            }

            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
        
        /**
         * 推荐使用第二种方法，因为第二种方法可以直接得到Future的返回值，
         * 或者处理错误情况。本质上第二种方法是通过调动第一种方法实现的，做了进一步的封装。
         * 
         * 另外ListenableFuture还有其他几种内置实现：

SettableFuture：不需要实现一个方法来计算返回值，而只需要返回一个固定值来做为返回值，可以通过程序设置此Future的返回值或者异常信息
CheckedFuture： 这是一个继承自ListenableFuture接口，他提供了checkedGet()方法，此方法在Future执行发生异常时，可以抛出指定类型的异常。
         */
        
        
	}
}
