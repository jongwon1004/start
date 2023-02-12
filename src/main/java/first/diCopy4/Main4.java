//package first.diCopy4;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.context.support.GenericXmlApplicationContext;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.Arrays;
//import java.util.Properties;
//
//
//@Component("engine") class Engine{}
//@Component class Door{}
//@Component class SuperEngine extends Engine {}
//@Component class TruckEngine extends Engine {}
//@Component class Test {}
//
//@Component
//class Car{
//    @Value("red") String color;
//    @Value("100") int oil;
////    @Autowired
////    @Qualifier("superEngine")
////    @Resource(name = "superEngine")   // 위의 두문장이랑 이 한문장이랑 같아보이지만 다름,위의 두문장은 타입으로찾고 이름, 이문장은 바로 이름
//    Engine engine;  // byType - 타입으로 먼저 검색, 여러개면 이름으로 검색
//    Door[] doors;
//
////    public Car() {}
//
//    @Autowired
//    public Car(@Value("red")String color, @Value("100") int oil, Engine engine, Door[] doors) {
//        this.color = color;
//        this.oil = oil;
//        this.engine = engine;
//        this.doors = doors;
//    }
//
//    @Override
//    public String toString() {
//        return "Car{" +
//                "color='" + color + '\'' +
//                ", oil=" + oil +
//                ", engine=" + engine +
//                ", doors=" + Arrays.toString(doors) +
//                '}';
//    }
//}
//
//
//public class Main4 {
//    public static void main(String[] args) {
//        ApplicationContext ac = new GenericXmlApplicationContext("config.xml");
//        Car car = (Car)ac.getBean("car",Car.class);
////        Door door = ac.getBean("door", Door.class);
////        Engine engine = ac.getBean("engine", Engine.class);
////        car.setColor("red");
////        car.setOil(100);
////        car.setEngine(engine);
////        car.setDoors(new Door[]{ac.getBean("door",Door.class),ac.getBean("door",Door.class)});
//        System.out.println("car = " + car);
//
////        Engine engine = ac.getBean("engine",Engine.class);
//        Engine engine1 = ac.getBean("engine",Engine.class);
//        Engine engine2 = (Engine) ac.getBean("superEngine");
//        System.out.println("engine1 = " + engine1);
//        System.out.println("engine2 = " + engine2);
//        Test test = ac.getBean("test",Test.class);
//        System.out.println("test = " + test);
//
//
//        System.out.println("ac.getBean(SysInfo.class) = " + ac.getBean(SysInfo.class));
//        Properties properties = System.getProperties();
//        System.out.println("properties = " + properties);
//
//    }
//}
//
//@Component
//@PropertySource("setting.properties")
//class SysInfo {
//    @Value("#{systemProperties['user.timezone']}")
//    String timezone;
//    @Value("#{systemEnvironment['PWD']}")
//    String currDir;
//    @Value("${autosaveDir}")
//    String autosaveDir;
//    @Value("${autosaveInterval}")
//    int autosaveInterval;
//    @Value("${autosave}")
//    boolean autosave;
//
//    @Override
//    public String toString() {
//        return "SysInfo{" +
//                "timezone='" + timezone + '\'' +
//                ", currDir='" + currDir + '\'' +
//                ", autosaveDir='" + autosaveDir + '\'' +
//                ", autosaveInterval=" + autosaveInterval +
//                ", autosave=" + autosave +
//                '}';
//    }
//}