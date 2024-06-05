package von.seiji;
/*
 * Created by IntelliJ IDEA.
 * User: jake
 * Date: 2024/6/6
 * Time: 上午10:56
 * To change this template use File | Settings | File Templates.
 */
import java.util.concurrent.CopyOnWriteArrayList;
public class T4 {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> concurrentList = new CopyOnWriteArrayList<>();

        concurrentList.add("Item1");
        concurrentList.add("Item2");
        concurrentList.add("Item3");

        Runnable listUpdater = () -> {
            for (int i = 0; i < 10; i++) {
                concurrentList.add("New Item" + i);
            }
        };

        Thread thread1 = new Thread(listUpdater);
        Thread thread2 = new Thread(listUpdater);

        thread1.start();
        thread2.start();

        // 等待线程完成
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Concurrent list size: " + concurrentList.size());
    }
}


