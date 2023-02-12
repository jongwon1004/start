package first.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class A1Dao {

    @Autowired
    DataSource ds;

    public int insert(int key, int value) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DataSourceUtils.getConnection(ds);
            pstmt = conn.prepareStatement("INSERT INTO a1 VALUES (?, ?)");
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
        for(AutoCloseable ac :acs)
            try { if(ac!=null) ac.close(); } catch(Exception e) { e.printStackTrace(); }
    }

    public void deleteAll() throws Exception {
        Connection conn = DataSourceUtils.getConnection(ds);
        String sql = "DELETE FROM a1";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();
        close(pstmt);

    }
}
