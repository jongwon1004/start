package first.aop;

import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AopMain {
    public static void main(String[] args) throws Exception {
        MyAdvice myAdvice = new MyAdvice();

        Class myClass = Class.forName("first.aop.MyClass");
        Object obj = myClass.newInstance();

        for(Method m : myClass.getDeclaredMethods()) {  // myClass 의 모든 메서드를 배열로 가져옴
            myAdvice.invoke(m, obj, null);
        }
    }
}

class MyAdvice {
    Pattern p = Pattern.compile("a.*");

    boolean matches(Method m) {
        Matcher matcher = p.matcher(m.getName());
        return matcher.matches();
    }

    void invoke(Method m,Object obj, Object... args) throws Exception {
        if(m.getAnnotation(Transactional.class)!= null) {
            System.out.println();
            System.out.println("[before]{");
        }
        m.invoke(obj, args);
        if(m.getAnnotation(Transactional.class)!= null) {
            System.out.println("}[after]");
            System.out.println();
        }
    }
}

class MyClass {
    void aaa() {
        System.out.println("aaa() is called");
    }

    @Transactional
    void bbb() {
        System.out.println("bbb() is called");
    }

    void ccc() {
        System.out.println("ccc() is called");
    }
}
