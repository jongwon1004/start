package first;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;

@Controller
public class YoilTellerMVC5 {
    @RequestMapping("/getYoilMVC5") // http://localhost/ch2/getYoilMVC
//    public String main(@ModelAttribute MyDate date, Model model) {
    public String main(MyDate date, Model model) {   // 위의 코드와 동일 -_-
        // 1. 유효성 검사
        if(!isValid(date))
            return "yoilError";  // 유효하지 않으면, /WEB-INF/views/yoilError.jsp로 이동

        // 4. 작업 결과를 보여줄 View의 이름을 반환
        return "yoil"; // /WEB-INF/views/yoil.jsp
    }

    // 반환타입앞에 @ModelAttribute애너테이션을 씀으로써
    // "yoil" 을 key값으로 갖는 모델에 저장함  model.addAttribute("yoil", yoil) 이 필요없게됨
    private @ModelAttribute("yoil") char getYoil(MyDate date){
        return getYoil(date.getYear(), date.getMonth(), date.getDay());
    }

    private char getYoil(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        return " 일월화수목금토".charAt(dayOfWeek);
    }

    private boolean isValid(MyDate date){
        return isValid(date.getYear(), date.getMonth(), date.getDay());
    }

    private boolean isValid(int year, int month, int day) {
        if(year==-1 || month==-1 || day==-1)
            return false;

        return (1<=month && month<=12) && (1<=day && day<=31); // 간단히 체크
    }
}