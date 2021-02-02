
package java0.conc0301.sync;


/**
 * Thread 线程对象中的任意方法获取的 this 代表线程对象本身，并不代表线程信息（线程信息中包括操作系统线程）
 * Thread.currentThread() 代表操作系统对应的线程信息.
 * 所以 this ！= Thread.currentThread() ,this 代表 Task 对象信息
 * 如下所示：TA 线程和 TB线程在操作系统上创建了两个线程对应 A线程 和 B线程，但是它们公用一个Task 对象信息， this是 task 对象信息
 */
public class Thread1 implements Runnable {

    @Override
    public void run() {
        synchronized (this) {
            System.out.println(this);
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
            }
        }
    }

    public static void main(String[] args) {
        Thread1 t1 = new Thread1();
        System.out.println("the Thread1 obj t1:" + t1);
        Thread ta = new Thread(t1, "A");
        Thread tb = new Thread(t1, "B");

        ta.start();
        tb.start();
    }
}
