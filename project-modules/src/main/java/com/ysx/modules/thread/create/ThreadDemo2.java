/**
 * 
 */
package com.ysx.modules.thread.create;

/**
 * 
 * @author yangShiXiong
 * @Data 2020年9月23日
 */
/**
 * 
 * 创建方式二 ：实现 Runnable
 *
 * 创建三个窗口卖票，总票数为100张，使用继承自Thread方式
 * 用静态变量保证三个线程的数据独一份
 * 
 * 存在线程的安全问题，有待解决
 *
 * */

public class ThreadDemo2 implements Runnable{

	private int ticket = 100;

	public void run() {
		// TODO Auto-generated method stub
		while(true){
            if(ticket>0){
//                try {
//                    sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println(Thread.currentThread().getName()+"当前售出第"+ticket+"张票");
                ticket--;
            }else{
                break;
            }
        }
	}
	
	public static void main(String[] args){
		ThreadDemo2 w = new ThreadDemo2();
    
	    //虽然有三个线程，但是只有一个窗口类实现的Runnable方法，由于三个线程共用一个window对象，所以自动共用100张票
	    
	    Thread t1=new Thread(w);
	    Thread t2=new Thread(w);
	    Thread t3=new Thread(w);
	
	    t1.setName("售票口1");
	    t2.setName("售票口2");
	    t3.setName("售票口3");
	
	    t1.start();
	    t2.start();
	    t3.start();
	}

   
}
