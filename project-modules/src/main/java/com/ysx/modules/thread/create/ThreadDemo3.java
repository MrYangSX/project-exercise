/**
 * 
 */
package com.ysx.modules.thread.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 
 * @author yangShiXiong
 * @Data 2020年9月23日
 */
/**
 * 
 * 创建方式三 ：实现 Callable
 *
 *
 * */

@SuppressWarnings("rawtypes")
public class ThreadDemo3 implements Callable{

	private int sum=0;
	
	//可以抛出异常
    public Object call() throws Exception {
        for(int i = 0;i<=100;i++){
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName()+":"+i);
                sum += i;
            }
        }
        return sum;
    }

	@SuppressWarnings("unchecked")
	public static void main(String[] args){
		//new一个实现callable接口的对象
		ThreadDemo3 w = new ThreadDemo3();
		
		//通过futureTask对象的get方法来接收futureTask的值
        FutureTask futureTask = new FutureTask(w);
    
        Thread t1 = new Thread(futureTask);
        t1.setName("线程1");
        t1.start();

        try {
            //get返回值即为FutureTask构造器参数callable实现类重写的call的返回值
           Object sum = futureTask.get();
           System.out.println(Thread.currentThread().getName()+":"+sum);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
   
}
