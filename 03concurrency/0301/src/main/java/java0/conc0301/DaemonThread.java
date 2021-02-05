package java0.conc0301;

/**
 * 总结：
 * JAVA 进程中，如果运行的线程都是 daemon 线程，则进程可以正常结束，
 * 如果运行的线程中有非 daemon 线程，则进程需要等待所有的非daemon线程都运行完毕，才能正常结束。
 */
public class DaemonThread {
    
    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
                try {
                    Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                Thread t = Thread.currentThread();
                System.out.println("当前线程:" + t.getName());
        };
        Thread thread = new Thread(task);
        thread.setName("test-thread-1");
        thread.setDaemon(true);
        thread.start();

        //Thread.sleep(2000);
    }
    
    
}
