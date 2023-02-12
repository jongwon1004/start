//package first.diCopy3;
//
//import org.apache.ibatis.javassist.ClassPath;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.io.FileReader;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//import java.util.Set;
//
//@Component class Car { }
//@Component class SportsCar extends Car {}
//@Component class Truck extends Car {}
//@Component class Engine{}
//
//class AppContext {
//    Map map; // 객체 저장소
//
//    AppContext() {
//        map = new HashMap();
//        doComponentScan();
//
//    }
//
//    private void doComponentScan() {
//        // 1. 패키지내의 클래스 목록을 가져온다.
//        // 2. 반복문으로 클래스를 하나씩 읽어와서 @Component가 붙어 있는지 확인
//        // 3. @Component가 붙어있으면 객체를 생성해서 map에 저장
////        ClassLoader classLoader = AppContext.class.getClassLoader();
////        ClassPath classPath = ClassPath.from(classLoader);
////        classPath.get
////
////        Set<ClassPath.ClassInfo> set = classPath.getTopLevelClasses("first.diCopy3");
//
//    }
//
//    Object getBean(String key) {
//        return map.get(key);
//    } // byName
//
//    Object getBean(Class clazz) { // byType
//        for (Object obj : map.values()) {
//            if (clazz.isInstance(obj))
//                return obj;
//        }
//        return null;
//    }
//
//}
//public class Main3 {
//    public static void main(String[] args) throws Exception{
//        AppContext ac = new AppContext();
//        Car car = (Car)ac.getBean("car"); // byName 으로 객체를 검색
//        Car car2 = (Car)ac.getBean(Car.class); // byName 으로 객체를 검색
////        Engine engine = (Engine) ac.getBean(Engine.class); // byType 으로 객체를 검색
//        System.out.println("car = " + car);
////        System.out.println("engine = " + engine);
//    }
//
//}
