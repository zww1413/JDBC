package preparedstatement;

import preparedstatementutil.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class preparedStatemenAlterTest {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn= ConnectionUtils.getConnection();

            String sql = "update connectiontest set Email= ? where name = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"2021020609@qq.com");
            ps.setString(2, "朱玲莉 ");

            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionUtils.closeConnection(conn, ps);
        }
    }
}
