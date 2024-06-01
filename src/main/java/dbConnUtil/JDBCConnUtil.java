package dbConnUtil;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCConnUtil {

    private static Connection conn = null;
    private static final Properties p = new Properties();

    /* 静态代码块
        类加载时被调用
                读取数据库配置文件db.propertiese
    加载驱动*/
    static {
        try {
            InputStream inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("dbConn.properties");

            p.load(inStream);
            Class.forName(p.getProperty("driver"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection createConn() {
        try {
            return DriverManager.getConnection(p.getProperty("url"), p.getProperty("username"), p.getProperty("password"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("连接数据库失败,检查配置文件");
        }
        return null;
    }
   /*   打开数据库
        @return Connection数据库连接对象*/


    public static Connection openDB() {
        if (conn == null) {
            conn = createConn();
        }
        return conn;
    }
    /*  关闭数据
     */

    public static void closeDB() {
        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*打开JDBC事务*/
    public static void openShiwu() {
        if (conn != null) {
            try {
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /*关闭JDBC事务*/
    public static void closeShiwu() {
        if (conn != null) {
            try {
                conn.commit();
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    public static void main(String[] args) {
        JDBCConnUtil.openDB();
        try {
            System.out.println(conn);
            JDBCConnUtil.closeDB();
            System.out.println(conn);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}

