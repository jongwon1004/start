package first.dao;

import first.domain.BoardDto;

import java.util.List;
import java.util.Map;

public interface BoardDao {
    BoardDto select(int bno) throws Exception;
    int insert(BoardDto boardDto) throws Exception;

    int delete(Integer bno, String writer) throws Exception;

    int deleteAll();

    int update(BoardDto boardDto) throws Exception;

    int increaseViewCnt(Integer bno) throws Exception;

    List<BoardDto> selectPage(Map map) throws Exception;

    List<BoardDto> selectAll() throws Exception;

    int count() throws Exception;
}
