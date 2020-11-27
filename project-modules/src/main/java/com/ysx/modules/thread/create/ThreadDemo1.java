package com.ysx.modules.thread.create;

/**
 * 
 * @author yangShiXiong
 * @Data 2020年9月23日
 */
/**
 * 
 * 创建方式一 ：继承 Thread
 *
 * 创建三个窗口卖票，总票数为100张，使用继承自Thread方式
 * 用静态变量保证三个线程的数据独一份
 * 
 * 存在线程的安全问题，有待解决
 *
 * */

public class ThreadDemo1 extends Thread{

	private static int ticket = 100; //将其加载在类的静态区，所有线程共享该静态变量

   @Override
   public void run() {
       while(true){
           if(ticket>0){
                try {
                	Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
               System.out.println(getName()+" 当前售出第 "+ticket+" 张票");
               ticket--;
           }else{
               break;
           }
       }
   }

public static void main(String[] args){
	ThreadDemo1 t1 = new ThreadDemo1();
	ThreadDemo1 t2 = new ThreadDemo1();
	ThreadDemo1 t3 = new ThreadDemo1();

    t1.setName("售票口1");
    t2.setName("售票口2");
    t3.setName("售票口3");

    t1.start();
    t2.start();
    t3.start();
}
}
