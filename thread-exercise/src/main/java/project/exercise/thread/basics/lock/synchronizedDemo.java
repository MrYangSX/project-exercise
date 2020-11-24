package project.exercise.thread.basics.lock;

/**
 * 
 * 多线程之间资源共享 - synchronized
 * 
 * @author yangShiXiong
 * @Data 2020年10月22日
 */
public class synchronizedDemo implements Runnable {

	private int ticket = 100;//定义一百张票
    
	public void run() {
		// TODO Auto-generated method stub
		while (true) {

			// 使用同步代码块
            synchronized(this){
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

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
			//同步方法
//			 sale();
        }
	}
	
	// 使用同步方法
    public synchronized void sale(){
        try {
            Thread.sleep(300);    //休息300毫秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        } 
        if (ticket > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "售出第" + ticket + "张票");
            ticket--;
        }
    }
	
	public static void main(String[] args){
		synchronizedDemo w= new synchronizedDemo();

	       Thread t1 = new Thread(w);
	       Thread t2 = new Thread(w);
	       Thread t3 = new Thread(w);

	       t1.setName("窗口1");
	       t2.setName("窗口2");
	       t3.setName("窗口3");

	       t1.start();
	       t2.start();
	       t3.start();
	}

}
