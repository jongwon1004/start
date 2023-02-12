package first.dao;

import first.domain.BoardDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class BoardDaoImplTest {

    @Autowired
    BoardDao boardDao;

    @Test
    public void count() throws Exception {
        assertTrue(boardDao.count() == 8);
        BoardDto boardDto = new BoardDto("Second Title", "내용이 없습니다", "악");
        boardDao.insert(boardDto);
        assertTrue(boardDao.count() == 9);
    }


    @Test
    public void select() throws Exception{
        assertTrue(boardDao!=null);
        System.out.println("boardDao = " + boardDao);
        BoardDto boardDto = boardDao.select(445);
        System.out.println("boardDto = " + boardDto);
        assertTrue(boardDto.getBno().equals(445));
    }

    @Test
    public void selectAll() throws Exception {
        List<BoardDto> list = boardDao.selectAll();

        BoardDto boardDto = new BoardDto("hello", "no content", "johnny");
        assertTrue(list.size() == 10);
        assertTrue(boardDao.insert(boardDto) == 1);
        assertTrue(list.size() == 11);

    }

    @Test
    public void insert() throws Exception {
        BoardDto boardDto1 = new BoardDto("First Title", "내용이 없습니다", "작성자최종원");
        assertTrue(boardDao.insert(boardDto1)==1);

        BoardDto boardDto2 = new BoardDto("First Title2", "내용이 없습니다2", "작성자최종원2");
        assertTrue(boardDao.count()==13);
    }

    @Test
    public void delete() throws Exception {
        Integer bno = 458;
        BoardDto boardDto = new BoardDto("First Title", "내용이 없습니다", "작성자최종원");
        assertTrue(boardDao.delete(bno,boardDto.getWriter())==1);
    }

    @Test
    public void update() throws Exception {
        BoardDto boardDto = new BoardDto("hello title22", "何もありません", "お腹");
        assertTrue(boardDao.insert(boardDto) == 1);

        Integer bno = 461;
        System.out.println("bno = " + bno);
        boardDto.setBno(bno);
        boardDto.setTitle("yes title");
        assertTrue(boardDao.update(boardDto) == 1);
    }

    @Test
    public void insertData() throws Exception {
        for(int i = 0; i < 400; i ++) {
            BoardDto boardDto = new BoardDto("こんにちは" + i, "よろしくお願いします", "admin");
            boardDao.insert(boardDto);
        }
    }
}