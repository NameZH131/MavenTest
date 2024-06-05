package von.seiji;
/*
 * Created by IntelliJ IDEA.
 * User: jake
 * Date: 2024/6/6
 * Time: 上午11:00
 * To change this template use File | Settings | File Templates.
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class T7 {


    /**
     * 典型的例子是 现在演示如何去重list中重复元素
     */

    public static void main(String[] args) {
       /* List<String> list = IntStream.generate(() -> new Random().nextInt(5))
                .mapToObj(item -> ("员工" + item))
                .limit(200).collect(Collectors.toList());*/
//        List<Integer> list = new ArrayList<>();
        List<Integer> list = new CopyOnWriteArrayList<>();
        list.add(1);
        list.add(3);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(2);
//        System.out.println(list);
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).equals(list.get(j))) {   // 重复元素
                    System.out.println(list.get(i) + " 重复了");
                    list.remove(list.get(j));//这种的好像会有另一种错误（大概是因为删除和获取冲突了，具体的不知道）
//                    j--;// 因为删除了元素，所以需要将j减一，这个很关键，删掉这行代码会导致数组下标越界（有概率报错）
                    if(new Random().nextInt(6) > 0) {
                        list.add(new Random().nextInt(5));
                    }
                }
            }
        }

        System.out.println("去重后："  + list);
    }

    @Test
    public void t55() {
//        List<Integer> list = new ArrayList<>(); // 这里用ArrayList会报错，因为ArrayList是线程不安全的
        List<Integer> list = new CopyOnWriteArrayList<>();
        list.add(1);
        list.add(3);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(2);
        for (Integer s : list) {
            for (Integer s1 : list.subList(list.indexOf(s) + 1, list.size())) {
                if (s.equals(s1)) {
                    System.out.println(s + " 重复了");
                    list.remove(s1);
                }
            }
        }

        System.out.println("去重后：" + list);
    }
}
