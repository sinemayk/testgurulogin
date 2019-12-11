
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class verifyLinks {


    WebDriver driver;


    @BeforeTest
    public void setUp() {

        System.setProperty("webdriver.gecko.driver", "C:\\Selenium\\geckodriver-v0.23.0-win64\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


    }
    //Blogtaki linklerin durumunu kontrol ediyor
    @Test
    public void verifyLinks() {
        String homepage = "http://sinemayk.blogspot.com/";
        driver.get(homepage);
        String url ="";
        HttpURLConnection httpURLConnection = null;

        List<WebElement> links = driver.findElements(By.tagName("a"));
        Iterator<WebElement> it = links.iterator();

        while(it.hasNext()){
            url = it.next().getAttribute("href");

            if (url == null || url.isEmpty()){
                System.out.println(url+ " URL boş ya da anchor etiketi tanımlanmamış");
                continue;
            }
            if (!url.startsWith(homepage)){
                System.out.println(url+ " URL başka bir domaine ait");
                continue;
            }

            try {
                httpURLConnection = (HttpURLConnection) (new URL(url).openConnection());
                httpURLConnection.setRequestMethod("HEAD");
                httpURLConnection.connect();
                int respCode = httpURLConnection.getResponseCode();

                if (respCode >= 400) {
                    System.out.println(url + " is kırık link");

                } else {
                    System.out.println(url + " geçerli link");
                }
            }
                catch(MalformedURLException e){
                    e.printStackTrace();
                }
                catch (IOException e){
                    e.printStackTrace();
            }


            }
        }




    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
