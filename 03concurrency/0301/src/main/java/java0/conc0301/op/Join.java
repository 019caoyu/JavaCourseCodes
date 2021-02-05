package java0.conc0301.op;

/**
 * 总结：线程A结束的时候，JVM内部会发送通知 notifyAll 给该线程对象A上的等待线程，唤醒在线程对象A上等待的线程
 * 其中this 代表线程的执行的task对象。
 * JAVA线程可以看作由两部分组成，
 *  1. Java Task 对象，由JVM回调执行线程中的任务，即run方法的对象实体。
 *  2. 操作系统的线程，一个Java 线程会一对一一个操作系统线程，
 *  而Thread 中的 this对象代表Java Task 对象实体，
 *  Thread.current()返回的线程代表的是操作系统线程
 */
public class Join {
    
    public static void main(String[] args) {
        Object oo = new Object();
    
        MyThread thread1 = new MyThread("thread1 -- ");
        oo = thread1;
        thread1.setOo(oo);
        thread1.start();
        
        synchronized (oo) {  // 这里用oo或thread1/this
            for (int i = 0; i < 100; i++) {
                if (i == 20) {
                    try {
                        oo.wait(0);
                        //thread1.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " -- " + i);
            }
        }
    }
    
}

class MyThread extends Thread {
    
    private String name;
    private Object oo;
    
    public void setOo(Object oo) {
        this.oo = oo;
    }
    
    public MyThread(String name) {
        this.name = name;
    }
    
    @Override
    public void run() {
        synchronized (this) { // 这里用oo或this，效果不同
            for (int i = 0; i < 100; i++) {
                System.out.println(name + i);
            }
            //my Test: add notifyAll to solve dead lock
            //oo.notifyAll();
        }
    }
    
}