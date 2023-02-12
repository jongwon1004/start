package first;

import first.aop.A1Dao;
import first.aop.B1Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Service
public class TxService {
    @Autowired A1Dao a1Dao;
    @Autowired B1Dao b1Dao;

    public void insertA1WithoutTx() throws Exception {
        a1Dao.insert(1, 100);
        a1Dao.insert(1, 200);
    }

    //    @Transactional // RuntimeException, Error 만 rollback
    @Transactional(rollbackFor = {Exception.class})
    public void insertA1WithTxFail() throws Exception { // Exception을 rollback
        a1Dao.insert(1, 100);
//        throw new RuntimeException();
//        throw new Exception();
        a1Dao.insert(1, 200);
    }

    public void insertA1WithTxSuccess() throws Exception {
        a1Dao.insert(1, 100);
        a1Dao.insert(2, 200);
    }
}
