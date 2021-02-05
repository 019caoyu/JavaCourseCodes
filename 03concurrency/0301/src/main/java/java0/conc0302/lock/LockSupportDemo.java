package java0.conc0302.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * 总结：LockSupport.park(t1) 休眠本线程t1后，并不释放锁信息，或者可以理解为与锁信息无关，
 * LockSupport.park(t1) 仅仅是休眠线程t1。
 *
 * 唤醒被LockSupport.park()的线程,可以在其他线程中通过两种方式唤醒：
 * 1. LockSupport.unpark(t1);
 * 2. t1.interrupt()
 *
 */
public class LockSupportDemo {
    
    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");
    
    public static class ChangeObjectThread extends Thread {
        public ChangeObjectThread(String name) {
            super(name);
        }
        @Override public void run() {
            synchronized (u) {
                System.out.println("in " + getName());
                LockSupport.park();
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("被中断了");
                }
                System.out.println("继续执行");
            }
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(1000L);
        t2.start();
        Thread.sleep(3000L);
        t1.interrupt();
        LockSupport.unpark(t2);
        t1.join();
        t2.join();
    }
}