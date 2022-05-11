package druid;

import org.junit.Test;
import preparedstatement.Customer;
import preparedstatementutil.ConnectionUtils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class perparedStatementSelectTest {
    @Test
    public void queryForConnectiontestTest(){
        perparedStatementSelectTest perparedStatementSelectTest = new perparedStatementSelectTest();
        String mysql = "select * from connectiontest where name = ?";
        preparedstatement.Customer customer = perparedStatementSelectTest.queryForConnectiontest(mysql, "周杰伦");
        System.out.println(customer);
    }
    public preparedstatement.Customer queryForConnectiontest(String mysql, Object...args){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            conn = Druid.getConnection();
            preparedStatement = conn.prepareStatement(mysql);
            for (int i = 0;i <args.length;i++){
                preparedStatement.setObject(i+1, args[i]);
            }
            rs = preparedStatement.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int column = metaData.getColumnCount();
            while(rs.next()) {
                preparedstatement.Customer customer = new preparedstatement.Customer();
                for (int i = 0; i < column; i++) {
                    Object columnValue = rs.getObject(i+1);
                    String columnLabel = metaData.getColumnLabel(i+1);//不用columnName，避免数据库字段与所建对象属性不同名问题。
                    Field field = Customer.class.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(customer, columnValue);
                }
                return  customer;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionUtils.closeConnection(conn, preparedStatement,rs);
        }
        return null;
    }
}
