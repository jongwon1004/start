package first;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@Controller
public class TwoDice {
    @RequestMapping("/rollDice")
    public static void main(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out =  response.getWriter();
        int rd1 = new Random().nextInt(6)+1;
        int rd2 = new Random().nextInt(6)+1;
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("<img src='resources/dice/dice"+rd1+".jpg'>");
        out.println("<img src='resources/dice/dice"+rd2+".jpg'>");
        out.println("</body>");
        out.println("</html>");

    }
}
