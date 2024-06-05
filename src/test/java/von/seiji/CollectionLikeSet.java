package von.seiji;
/*
 * Created by IntelliJ IDEA.
 * User: jake
 * Date: 2024/6/5
 * Time: 下午9:01
 * To change this template use File | Settings | File Templates.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CollectionLikeSet {
    private List<String> list = new ArrayList<>();
    public void judge (String s) {
        synchronized (s.intern()){
            if (list.contains(s)) {
                System.out.println(s+" already exists");
            }
            else {
                list.add(s);
                System.out.println(s+" is added");
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        CollectionLikeSet c = new CollectionLikeSet();

        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                c.judge("hello world" +"--"+ new Random().nextInt(10));
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println("--------------------------------");
        System.out.println("list size: "+c.list.size());
        System.out.println("--------------------------------");
        System.out.println("list: "+c.list);
    }
}
