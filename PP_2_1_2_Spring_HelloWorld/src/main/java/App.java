import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);


        HelloWorld helloWorldBean = (HelloWorld) applicationContext.getBean("helloworld");
        System.out.println(helloWorldBean.getMessage());
        HelloWorld helloWorldBean1 = (HelloWorld) applicationContext.getBean("helloworld") ;
        System.out.println(helloWorldBean1.getMessage());

        Cat catBean = (Cat) applicationContext.getBean("cat");
        System.out.println(catBean.getMessage());
        Cat catBean1 = (Cat) applicationContext.getBean("cat");
        System.out.println(catBean1.getMessage());

        System.out.println(helloWorldBean==helloWorldBean1);
        System.out.println(catBean==catBean1);
    }
}