package preparedstatementutil;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class ConnectionUtils {
    public static Connection getConnection() throws Exception {
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        //通过字符输入流获取配置文件

        Properties pros = new Properties();
        pros.load(is);
        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driver = pros.getProperty("driver");//获取配置文件内三要素

        Class.forName(driver);//加载驱动

        Connection conn = DriverManager.getConnection(url, user, password);//通过DriverManager建立连接
        return conn;
    }

    public static void closeConnection(Connection conn, PreparedStatement ps) {
        try {
            ps.close();//资源关闭
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            conn.close();//资源关闭
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void closeConnection(Connection conn, PreparedStatement ps,ResultSet rs) {
        try {
            ps.close();//资源关闭
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            conn.close();//资源关闭
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}