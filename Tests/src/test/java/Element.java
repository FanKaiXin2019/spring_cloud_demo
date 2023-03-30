import javax.swing.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wbfancy
 * @version 1.0
 * @description: TODO
 * @date 2022/6/28 17:03
 */
public class Element implements Runnable{
    public AtomicInteger i = new AtomicInteger(0);
    @Override
    public void run() {
        for (int j = 0; j < 100; j++) {
            System.out.println(Thread.currentThread().getName()+ ":"+i.incrementAndGet());
        }

    }
    public static void main(String[] args) {

        Element s = new Element();
        Thread t1 = new Thread(s, "窗口1");
        Thread t2 = new Thread(s, "窗口2");
        Thread t3 = new Thread(s, "窗口3");
        Thread t4 = new Thread(s, "窗口4");
        Thread t5 = new Thread(s, "窗口5");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
