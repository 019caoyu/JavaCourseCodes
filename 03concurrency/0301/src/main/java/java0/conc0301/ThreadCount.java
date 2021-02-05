package java0.conc0301;

/**
 * 线程信息：
 * 在没有业务线程情况下，总共有两个线程组，6个线程（4个系统组的线程，2个主线程组的线程），如下所示：
 * java.lang.ThreadGroup[name=system,maxpri=10]
 *     Thread[Reference Handler,10,system]
 *     Thread[Finalizer,8,system]
 *     Thread[Signal Dispatcher,9,system]
 *     Thread[Attach Listener,5,system]
 *     java.lang.ThreadGroup[name=main,maxpri=10]
 *         Thread[main,5,main]
 *         Thread[Monitor Ctrl-Break,5,main]
 */
public class ThreadCount {
    public static void main(String[] args) throws InterruptedException {
        //System.out.println("system："+Thread.currentThread().getThreadGroup().getParent());
        Thread.currentThread().getThreadGroup().getParent().list();

//        System.out.println("main："+Thread.currentThread().getThreadGroup());
//        Thread.currentThread().getThreadGroup().list();
    }
}
