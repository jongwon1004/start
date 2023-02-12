package first.dao;

import first.domain.User;

public interface UserDao {
    int deleteUser(String id) throws Exception;

    User selectUser(String id) throws Exception;

    // 사용자 정보를 user_info테이블에 저장하는 메서드
    int insertUser(User user) throws Exception;

    // 매개변수로 받은 사용자 정보로 user_info테이블을 update하는 메서드
    int updateUser(User user) throws Exception;
    int count() throws Exception;
    void deleteAll() throws Exception;
}
