package first;

import first.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class DBConnectionTest2Test {
    @Autowired DataSource ds;

    // 사용자 정보를 user_info테이블에 저장하는 메서드
    @Test
    public void insertUserTest() throws Exception {
        User user = new User("helloworld2", "12345", "abc", "aaa@aaa.com", new Date(), "instagram", new Date());
        deleteAll();
        int rowCnt = insertUser(user);

        assertTrue(rowCnt == 1);
    }

    @Test
    public void selectUserTest() throws Exception {
        deleteAll();
        User user = new User("helloworld2", "12345", "abc", "aaa@aaa.com", new Date(), "instagram", new Date());
        int rowCnt = insertUser(user);
        User user2 = selectUser("helloworld2");

        assertTrue(user.getId().equals("helloworld2"));
    }

    public User selectUser(String id) throws Exception {
        Connection conn = ds.getConnection();

        String sql = "SELECT * FROM user_info WHERE id= ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery(); // SELECT 일땐 executeUpdate() 가 아닌 executeQuery()을 사용

        if(rs.next()) {
            User user = new User();
            user.setId(rs.getString(1));
            user.setPwd(rs.getString(2));
            user.setName(rs.getString(3));
            user.setEmail(rs.getString(4));
            user.setBirth(rs.getDate(5));
            user.setSns(rs.getString(6));
            user.setReg_date(new Date(rs.getTimestamp(7).getTime()));
            return user;
        }
        return null;
    }

    @Test
    public void deleteUserTest() throws Exception {
        deleteAll();
        int rowCnt = deleteUser("helloworld5");
        assertTrue(rowCnt == 0);

        User user = new User("helloworld5", "12345", "abc", "aaa@aaa.com", new Date(), "instagram", new Date());
        rowCnt = insertUser(user);
        assertTrue(rowCnt == 1);

        rowCnt = deleteUser(user.getId());
        assertTrue(rowCnt == 1);

        assertTrue(selectUser(user.getId())== null);
    }

    @Test
    public void updateUserTest() throws Exception {
        deleteAll();
        User user = new User("helloworld5", "12345", "abc", "aaa@aaa.com", new Date(), "instagram", new Date());
        int rowCnt = insertUser(user);
        assertTrue(rowCnt == 1);

        int rowCnt2 = updateUser(user);
        assertTrue(rowCnt2 == 1);
    }

    public int updateUser(User user) throws Exception {
        Connection conn = ds.getConnection();
        String sql = "UPDATE user_info " +
                "SET pwd = ?, name=?, email=?, birth =?, sns=?, reg_date=? " +
                "WHERE id = ? ";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user.getPwd());
        pstmt.setString(2, user.getName());
        pstmt.setString(3, user.getEmail());
        pstmt.setDate(4, new java.sql.Date(user.getBirth().getTime()));
        pstmt.setString(5, user.getSns());
        pstmt.setTimestamp(6, new java.sql.Timestamp(user.getReg_date().getTime()));
        pstmt.setString(7, user.getId());

        return pstmt.executeUpdate();
    }

    public int deleteUser(String id) throws Exception {
        Connection conn = ds.getConnection();
        String sql = "DELETE FROM user_info WHERE id = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);
//        int rowCnt = pstmt.executeUpdate();
        return pstmt.executeUpdate();
    }

    private void deleteAll() throws Exception {
        Connection conn = ds.getConnection();

        String sql = "DELETE FROM user_info";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate(); // insert, delete, update
    }

    // 사용자 정보를 user_info 테이블에 저장하는 메서드
    public int insertUser(User user) throws Exception {
        Connection conn = ds.getConnection();

//    INSERT INTO user_info (id, pwd, name, email, birth, sns, reg_date)
//    VALUES ('johnny1','1234','spring','example@gamil.com','2000-01-01','instagram',NOW());

//        String sql = "INSERT INTO user_info VALUES ('johnny1','1234','spring','example@gamil.com','2000-01-01','instagram',NOW())";

        String sql = "INSERT INTO user_info VALUES (?, ?, ?, ?, ?, ?,NOW())";

        PreparedStatement pstmt = conn.prepareStatement(sql);  // SQL Injection공격, 성능향상
        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getPwd());
        pstmt.setString(3, user.getName());
        pstmt.setString(4, user.getEmail());
        pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));
        pstmt.setString(6, user.getId());

        int rowCnt = pstmt.executeUpdate(); // insert, delete, update
        return rowCnt;
    }

    @Test
    public void main() throws Exception {
//        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
//        DataSource ds = ac.getBean(DataSource.class);

        Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.

        System.out.println("conn = " + conn);
        assertTrue(conn != null);  // 괄호안의 조건식이 True이면 테스트 성공, False이면 테스트 실패
    }
}




