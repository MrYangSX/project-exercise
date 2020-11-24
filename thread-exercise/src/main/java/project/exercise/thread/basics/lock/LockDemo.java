package project.exercise.thread.basics.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * 多线程之间资源共享 - lock
 * 
 * @author yangShiXiong
 * @Data 2020年10月22日
 */
public class LockDemo {

	public static class LockTest {
		private int ticket = 100;//定义一百张票
		Lock lock = new ReentrantLock();
	    
		public void test() {
			// TODO Auto-generated method stub
			while (true) {

				lock.lock();
	            try {
	                Thread.sleep(200);
	                
	                if (ticket > 0) {
	                    try {
	                        Thread.sleep(100);
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }

	                    System.out.println(Thread.currentThread().getName() + "售出第" + ticket + "张票");
	                    ticket--;
	                } else {
	                    break;
	                }
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }finally {
	            	lock.unlock();
	            }
	        }
		}
	}
	public static void testLockTest() throws Exception{  
        final LockTest lockTest = new LockTest();  
        // 新建任务1，调用lockTest的addValue方法  
        Runnable task1 = new Runnable(){  
            public void run(){  
                lockTest.test(); 
            }  
        }; 
	}
	
	public static void main(String[] args){
		final LockTest lockTest = new LockTest();  
        // 新建任务1，调用lockTest的addValue方法  
        Runnable task1 = new Runnable(){  
            public void run(){  
                lockTest.test(); 
            }  
        };
        
        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task1);
        Thread t3 = new Thread(task1);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
	}

}
