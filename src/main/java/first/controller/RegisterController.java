package first.controller;

import first.dao.UserDao;
import first.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import first.domain.User;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    UserDao userDao;

    final int FAIL = 0;

    @InitBinder
    public void toDate(WebDataBinder binder) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
        binder.setValidator(new UserValidator());

    }

    @GetMapping("/add")
    public String register() {
        return "registerForm"; // WEB-INF/views/registerForm.jsp
    }

    @PostMapping("/save")
    public String save(@Valid User user, BindingResult result, Model m) throws Exception {
        System.out.println("result="+result);
        System.out.println("user="+user);

        if(!result.hasErrors()) {
            // 2. DB에 신규회원 정보를 저장
            int rowCnt = userDao.insertUser(user);

            if(rowCnt != FAIL) {
                return "registerInfo";
            }
        }

        return "registerForm";
    }


//    @RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
//    public String register(){
//        return "registerForm";
//    }
//
//    @PostMapping("/save")
//    public String save(User user, BindingResult result, Model m) throws Exception{
//        System.out.println("result = " + result);
//        if(!isValid(user)) {
//            String msg = URLEncoder.encode("idを正しく入力してください", "utf-8");
//
//            m.addAttribute("msg", msg);
//            return "forward:/register/add";
//        }
//
//        return "registerInfo";
//    }
//
//    private boolean isValid(User user) {
//        return true;
//    }
}
