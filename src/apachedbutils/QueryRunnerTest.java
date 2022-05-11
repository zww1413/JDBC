package apachedbutils;

import druid.Customer;
import druid.Druid;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class QueryRunnerTest {
    @Test
    public void testInsert()  {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = Druid.getConnection();
            String sql = "insert into connectiontest(name,email,birthday) values(?,?,?)";
            int insertCount = runner.update(conn, sql, "蔡徐坤", "caixukun@126.com", "1997-09-08");
            System.out.println("添加了"+insertCount+"条记录");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Druid.closeConnection(conn);
        }
    }
    @Test
    public void testSelect(){
        Connection connection = null;
        try {
            QueryRunner runner = new QueryRunner();
            connection = Druid.getConnection();
            String sql = "select * from connectiontest where name = ?";
            BeanHandler<Customer> handler = new BeanHandler<>(Customer.class);
            Customer customer = runner.query(connection, sql, handler, "蔡徐坤");
            System.out.println(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtils.close(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void testSelect1() {
        Connection connection = null;
        try {
            QueryRunner runner = new QueryRunner();
            connection = Druid.getConnection();
            String sql = "select * from connectiontest where name = ? or name = ?";
            BeanListHandler<Customer> handler = new BeanListHandler<>(Customer.class);
            List<Customer> customers = runner.query(connection, sql, handler, "蔡徐坤", "朱文武");
            Iterator iterator = customers.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtils.close(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
