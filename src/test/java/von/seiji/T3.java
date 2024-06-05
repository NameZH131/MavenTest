package von.seiji;
/*
 * Created by IntelliJ IDEA.
 * User: jake
 * Date: 2024/6/6
 * Time: 上午12:10
 * To change this template use File | Settings | File Templates.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class T3 {

    /**
     * 这差异就出来了
     * 为啥之前看不出来，是因为代码本身的执行速度很快，另外对于count值很大也许是能看出效果的，但是电脑可能卡住
     */

    // 创建一个字符串列表
    static int count = 1000;
    static List<Integer> list1 = new ArrayList<>();
    static List<Integer> list2 = new ArrayList<>();


    public void judge11(Integer s) throws InterruptedException {
        synchronized (this) {//时间耗时为啥差那么多，就是因为很多代码卡在这处浪费了时间，这个粒度很大，每次过来都得阻塞100ms
            if (list1.contains(s)) {
//                System.out.println(s+" already exists");
            } else {//这里换行干嘛
                Thread.sleep(100);//忘了这个，试试
                list1.add(s);
//                System.out.println(s+" is added");
            }
        }
    }


    public void judge22(Integer s) throws InterruptedException {
        synchronized (s) {
//            System.out.println(s + "-->" + System.identityHashCode(s));//这个看其煤气作用得看内存地址 如果对于同样的s值 经过此方法 获取的内存地址相同，说明粒度是合适的，也就是锁起到作用了
            if (list2.contains(s)) {
//                System.out.println(s + " already exists");
            } else {
                Thread.sleep(100);//忘了这个，试试
                list2.add(s);
//                System.out.println(s + " is added");
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {

        T3 c = new T3();
        CountDownLatch countDownLatch = new CountDownLatch(count);
        long l = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                try {
                    c.judge11(new Random().nextInt(10));
                } catch (InterruptedException e) {
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println("time: " + (System.currentTimeMillis() - l));
        System.out.println(list1);

//        list.clear();//公平起见还是new两个集合
        System.out.println("------------------------------------------------------");

        CountDownLatch countDownLatch1 = new CountDownLatch(count);
        long l1 = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                try {
                    c.judge22( new Random().nextInt(10));
                } catch (InterruptedException e) {
                }
                countDownLatch1.countDown();
            }).start();
        }
        countDownLatch1.await();
        System.out.println("time: " + (System.currentTimeMillis() - l1));
        System.out.println(list2);
    }


}
