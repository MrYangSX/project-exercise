/**
 * 
 */
package com.ysx.modules.thread.create;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author yangShiXiong
 * @Data 2020年9月23日
 */
/**
 * 创建线程的方式四：使用线程池（批量使用线程）
 * 1.需要创建实现runnable或者callable接口方式的对象
 * 2.创建executorservice线程池
 * 3.将创建好的实现了runnable接口类的对象放入executorService对象的execute方法中执行。
 * 4.关闭线程池
 *
 * */

class NumberThread implements Runnable{
    public void run() {
        for(int i = 0;i<=100;i++){
            if (i % 2 ==0 )
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }
}

class NumberThread1 implements Runnable{
    public void run() {
        for(int i = 0;i<100; i++){
            if(i%2==1){
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}

public class ThreadDemo4{
	public static void main(String[] args){
		//创建固定线程个数为十个的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        //new一个Runnable接口的对象
        NumberThread number = new NumberThread();
        NumberThread1 number1 = new NumberThread1();

        //执行线程,最多十个
        executorService.execute(number1);
        executorService.execute(number);//适合适用于Runnable

        //executorService.submit();//适合使用于Callable
        //关闭线程池
        executorService.shutdown();
	}
}
