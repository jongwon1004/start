package first.controller;

import first.domain.User;
import first.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.sql.SQLException;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    UserDao userDao;

    @GetMapping("/login")
    public String loginForm(){
        return "loginForm";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        // 1. 세션을 종료
        session.invalidate();
        // 2. 홈으로 이동
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(String id, String pwd, String toURL ,boolean rememberId, HttpServletResponse response, HttpServletRequest request) throws Exception {
        // 1. id와 pwd를 확인
        if(!loginCheck(id,pwd)) {
            // 일치하지 않으면, loginForm으로 이동
            String msg = URLEncoder.encode("メールまたはパスワードが一致していません。", "utf-8");

            return "redirect:/login/login?msg="+msg;
        }

        // id와 pwd가 일치하면 세션 객체에 id를 저장
        HttpSession session = request.getSession();
        session.setAttribute("id", id);

        if(rememberId) {
            // 1. 쿠키를 생성
            Cookie cookie = new Cookie("id", id);
            response.addCookie(cookie);
        }else {
            // 1. 쿠키를 생성
            Cookie cookie = new Cookie("id", id);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }

        // 2. id와 pwd가 일치하면, 홈으로 이동
        toURL = toURL == null || toURL.equals("") ? "/" : toURL;

        return "redirect:"+toURL;
    }

    private boolean loginCheck(String id, String pwd) {
//        return "whddnjs3340".equals(id) && "dnflwlq1408".equals(pwd);
        User user = null;

        try {
            user = userDao.selectUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException is called");
            return false;
        } catch (Exception e) {
            return false;
        }

        return user!=null & user.getPwd().equals(pwd);
    }
}
