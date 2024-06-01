package util;
/*
 * Created by IntelliJ IDEA.
 * User: jake
 * Date: 2024/5/29
 * Time: 下午11:59
 * To change this template use File | Settings | File Templates.
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashing {
//静态方法类名可以直接调用
    public static String hashPassword(String password) {
        try {
            // 创建 MessageDigest 实例
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // 将密码转换为字节数组
            byte[] bytes;
            try {
                // 使用 UTF-8 编码, 如果失败会立即抛出异常，我们只能try catch
                bytes = password.getBytes();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("密码转换为字节数组失败");
                return null;
            }

            // 计算哈希值
            byte[] hashedBytes = md.digest(bytes);

            // 将哈希值转换为十六进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // 哈希算法不可用
            System.out.println("哈希算法不可用");
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String password = "examplePassword";
        String hashedPassword = hashPassword(password);
        System.out.println("原始密码：" + password);
        System.out.println("哈希密码：" + hashedPassword);
    }
}
