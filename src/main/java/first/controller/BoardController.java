package first.controller;

import first.domain.BoardDto;
import first.domain.PageHandler;
import first.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @PostMapping("/modify")
    public String modify(Integer page, Integer pageSize, BoardDto boardDto, Model m, HttpSession session, RedirectAttributes rattr) {
        String writer = (String) session.getAttribute("id");
        boardDto.setWriter(writer);
        try {
            rattr.addAttribute("page", page);
            rattr.addAttribute("pageSize", pageSize);
//            rattr.addAttribute("page", page);
//            rattr.addAttribute("pageSize", pageSize);
            if(boardService.modify(boardDto) != 1)
                throw new Exception("Modify failed");

            m.addAttribute("msg", "MOD_OK");
            rattr.addFlashAttribute("msg", "MOD_OK");
            return "redirect:/board/list";
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute(boardDto);
            rattr.addAttribute("page", page);
            rattr.addAttribute("pageSize", pageSize);
            rattr.addAttribute("msg", "MOD_ERR");
            return "board";
        }
    }


    @PostMapping("/write")
    public String write(BoardDto boardDto, Model m, HttpSession session, RedirectAttributes rattr) {
        String writer = (String) session.getAttribute("id");
        boardDto.setWriter(writer);
        try {
            int rowCnt = boardService.write(boardDto);
            if(rowCnt != 1)
                throw new Exception("Write Failed"); // 글쓰기가 성공해서 1줄이 추가되면 return값으로 1이 오는데
                // 1이 아닐경우에는 예외를 던져서 게시물 목록으로 가지않고 입력했던 내용을 다시 보여주게함.

//            m.addAttribute("msg", "WRT_OK");   메세지 작성을 성공했으면 메세지로 성공했다고 알려줌
//            하지만 위에처럼 쓰면 URL창에 메세지 내용이 자꾸 남기 때문에 RedirectAttributes 를 사용해서 1회용으로 해줌.
            rattr.addFlashAttribute("msg","WRT_OK");

            return "redirect:/board/list"; // 글쓰기가 에러 발생하지 않으면 게시물 목록으로
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute(boardDto);  // 입력했던 내용이 날라가지 않도록 모델에 저장해놓고 입력했던 내용을 보여줌
            m.addAttribute("msg", "WRT_ERR"); // 메세지를 줘서 에러인지 아닌지를 알려줌
            rattr.addFlashAttribute("msg", "WRT_ERR");
            return "board";  // 글쓰기에서 에러가 발생하면 다시 글쓰기 화면으로
        }

    }

    @GetMapping("/write")
    public String write(Model m) {
        m.addAttribute("mode", "new");
        return "board";  // 읽기와 쓰기에 사용 , 쓰기에 사용할때는 mode = new -> jsp에서 이 mode값을 확인해서 new일 때는 쓰기에 알맞게 설정, 아니면 보여주기만함
    }

    @PostMapping("/remove")
    public String remove(Integer bno, Integer page, Integer pageSize, Model m, HttpSession session , RedirectAttributes rattr) {
        String writer = (String) session.getAttribute("id");
        try {
            rattr.addAttribute("page", page);
            rattr.addAttribute("pageSize", pageSize);

            int rowCnt = boardService.remove(bno, writer);
            if(rowCnt != 1)
                throw new Exception("board remove error");

            rattr.addFlashAttribute("msg", "DEL_OK");
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg","DEL_ERR");// RedirectAttributes는 1회용 한번쓰고 없어짐
            // 삭제에 실패하고 게시판목록으로 가서 새로고침을 해보면 계속 삭제에 실패했습니다 라고 알람이 뜨는데
            // m.addAttribute("msg","DEL_ERR") 에서 rattr.addFlashAttribute("msg","DEL_ERR") 로 바꾸면 알람이 안뜬다
            // model을 썼을때 get방식으로 URL에 MSG가 계속 남기때문에 1회성인 RedirectAttributes를 써줌.
            // boardList.jsp 에 let msg = "${param.msg}" 을 let msg = "${msg}" 로 변경해줌
        }

        return "redirect:/board/list";
    }

    @GetMapping("/read")
    public String read(Integer bno,Integer page, Integer pageSize,Model m) {
        try{
            BoardDto boardDto = boardService.read(bno);
//            m.addAttribute("boardDto", boardDto);  // 아래 문장과 동일
            m.addAttribute(boardDto);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return "board";
    }

    @GetMapping("/list")
    public String list(Integer page, Integer pageSize, Model m, HttpServletRequest request) {

        if (!loginCheck(request))
            return "redirect:/login/login?toURL=" + request.getRequestURL(); // 로그인을 안했으면 로그인 화면으로 이동

        if (page == null) page = 1;
        if (pageSize == null) pageSize = 10;

        try {

            int totalCnt = boardService.getCount();
            PageHandler pageHandler = new PageHandler(totalCnt, page, pageSize);
            Map map = new HashMap();
            map.put("offset", (page - 1) * pageSize);
            map.put("pageSize", pageSize);

            List<BoardDto> list = boardService.getPage(map);
            m.addAttribute("list", list);
            m.addAttribute("ph", pageHandler);
            m.addAttribute("page", page);
            m.addAttribute("pageSize",pageSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "boardList";
    }

    private boolean loginCheck(HttpServletRequest request) {
        // 1. 세션을 얻어서
        HttpSession session = request.getSession();

        // 2. 세션에 id가 있는지 확인, 있으면 true를 반환
        return session.getAttribute("id") != null;
    }
}
