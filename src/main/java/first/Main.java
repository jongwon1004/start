package first;

import first.controller.TestController;

import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws Exception {
//        TestController testController = new TestController();
//        testController.main()  // private이라서 외부 호출 불가

        // Reflection API를 사용 - 클래스 정보를 얻고 다룰 수 있는 강력한 기능 제공
        // java.lang.reflect패키지를 제공
        // TestController클래스의 Class객체 ( 클래스의 정보를 담고 있는 개체 ) 를 얻어온다.

        Class helloClass = Class.forName("first.controller.TestController");
        TestController testController = (TestController) helloClass.newInstance();
        Method main = helloClass.getDeclaredMethod("main");  // method참조할떄 쓰는 클래스
        main.setAccessible(true); // private인 main()을 호출가능하게 한다.
        main.invoke(testController);  // hello.main()호출과 동일
    }
}

