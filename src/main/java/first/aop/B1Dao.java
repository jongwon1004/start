package first.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class B1Dao {
    @Autowired
    DataSource ds;

    public int insertTest(int key, int value) throws Exception {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = DataSourceUtils.getConnection(ds);
            pstmt = connection.prepareStatement("INSERT INTO b1 VALUES (?, ?)");
            pstmt.setInt(1, key);
            pstmt.setInt(2, value);
            System.out.println("connection = " + connection);
            return pstmt.executeUpdate() ;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            //            close(pstmt, conn);
            close(pstmt);
            DataSourceUtils.releaseConnection(connection,ds);
        }
    }

    public void close(AutoCloseable... acs) {
        for (AutoCloseable ac: acs) {
            try { if(ac!=null) ac.close(); } catch (Exception e) { e.printStackTrace();}
        }
    }
}
