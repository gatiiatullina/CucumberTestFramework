//package steps;
//
//import io.cucumber.java.After;
//import io.cucumber.java.Before;
//import org.openqa.selenium.WebDriver;
//import utilities.Driver;
//
//import java.util.concurrent.TimeUnit;
//
//public class Hooks {
//    WebDriver driver = Driver.getDriver();
//    @Before
//    public void setUp(){
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.manage().deleteAllCookies();
//        System.out.println("Before Scenario Method");
//    }
//    @After
//    public void tearDown() throws InterruptedException {
//        Thread.sleep(2000);
//        driver.quit();
//        System.out.println("After scenario Method");
//    }
//}
