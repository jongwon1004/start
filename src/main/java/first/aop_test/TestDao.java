package first.aop_test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class TestDao {

    @Autowired
    DataSource ds;

    public int insertTest(int key, int value) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DataSourceUtils.getConnection(ds);
            pstmt = conn.prepareStatement("INSERT INTO testDao VALUES (?, ?)");
            pstmt.setInt(1, key);
            pstmt.setInt(2, value);
            System.out.println("conn = " + conn);

            return pstmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }finally {
//            close(pstmt, conn);
            close(pstmt);
            DataSourceUtils.releaseConnection(conn,ds);
        }
    }

    private void close(AutoCloseable... acs) {
        for(AutoCloseable ac : acs) {
            try { if(ac != null) ac.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
}
