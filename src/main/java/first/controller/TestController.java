package first.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller  // 1. 원격 호출 가능한 프로그램으로 등록
public class TestController {
    @RequestMapping("/hello")  // 2. URL과 main()을 연결
    private void main(){
        System.out.println("hello world!");
    }
}
