package first.aop_test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.SQLException;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class TestDaoTest {

    @Autowired
    DataSource ds;

    @Autowired
    TestDao testDao;

    @Autowired
    DataSourceTransactionManager dtm;

    @Test
    public void insertTest() throws Exception {
        /**
         *  TransactionManager을 수동주입하지 않고 @Autowired를 사용해 자동주입 받아도 똑같다.
         */
//        PlatformTransactionManager tm = new DataSourceTransactionManager(ds);
        TransactionStatus status = dtm.getTransaction(new DefaultTransactionDefinition());

        try {
            testDao.insertTest(1, 1);
            testDao.insertTest(2, 3);
            testDao.insertTest(3, 3);
            testDao.insertTest(4, 3);
            dtm.commit(status);
        } catch (SQLException e) {
            e.printStackTrace();
            dtm.rollback(status);
        } finally {
        }
    }


}