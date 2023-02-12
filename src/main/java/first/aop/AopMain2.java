package first.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class AopMain2 {
    public static void main(String[] args) {
        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context_aop.xml");
        MyMath m = (MyMath) ac.getBean("myMath");
        m.add(3, 5);
        m.add(3, 5, 6);
        m.multiply(3, 5);
    }
}
