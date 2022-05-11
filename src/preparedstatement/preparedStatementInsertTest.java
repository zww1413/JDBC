package preparedstatement;

import preparedstatementutil.ConnectionUtils;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class preparedStatementInsertTest {
    public static void main(String[] args)  {
       PreparedStatement ps = null;
       Connection conn = null;
       try {
          conn = ConnectionUtils.getConnection();//获取工具包中的静态数据库连接方法

          String sql = "insert into connectiontest(name,email,birthday) values(?,?,?)";//预编译sql语句，返回PreparedStatement的实例
          ps = conn.prepareStatement(sql);

          ps.setString(1, "朱莉");
          ps.setString(2, "1413020118@qq.com");
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
          Date date = sdf.parse("1992-10-06");
          ps.setDate(3, new java.sql.Date(date.getTime()));//填充占位符

          ps.execute();//执行操作
       } catch (SQLException e) {
          e.printStackTrace();
       } catch (ParseException e) {
          e.printStackTrace();
       } catch (Exception e) {
          e.printStackTrace();
       }finally {
          ConnectionUtils.closeConnection(conn, ps);//获取工具包中的静态数据库关闭方法
       }
    }
}
