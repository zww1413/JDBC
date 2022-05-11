package preparedstatement;

import preparedstatementutil.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class perparedStatementDeleteTest {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectionUtils.getConnection();
            String sql = "delete from connectiontest where name = ?";
            ps = conn.prepareStatement(sql);

            ps.setString(1,"朱莉");

            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionUtils.closeConnection(conn, ps);
        }
    }
}
