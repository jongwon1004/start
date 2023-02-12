package first;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestJsp {
    @RequestMapping("/testJsp")
    public String main(){
        return "registerForm";
    }
}
