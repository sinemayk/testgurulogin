import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class verifyLoginSection {

     WebDriver driver;
    // WebDriverWait wait=new WebDriverWait(driver, 20);

    @BeforeTest
    public void setUp() {

        System.setProperty("webdriver.gecko.driver", "C:\\Selenium\\geckodriver-v0.23.0-win64\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);



    }

    @Test(priority = 2)
    public void verifyTitle() {
        driver.get("http://www.mercury-tours.com");
        String actualTitle = driver.getTitle();
        String expectedTitle = "Welcome: Mercury Tours";

        if (actualTitle.contentEquals(expectedTitle)){
        System.out.println("test is passed");}
        else {
            System.out.println("test is failed");
        }


    }

    @Test (priority = 1)
    public void elementsFind() {
        driver.get("http://demo.guru99.com/test/ajax.html");
        List<WebElement> elements = driver.findElements(By.name("name"));
        System.out.println("number of element: " + elements.size());

        for (int i =0; i<elements.size(); i++)
        {
            System.out.println("Radio button text: " + elements.get(i).getAttribute("value"));
        }

    }

    /*@Test
    public void dropDownList() {
        driver.get("http://demo.guru99.com/test/newtours/register.php");
        Select drpCountry = new Select(driver.findElement(By.name("country")));
        drpCountry.selectByVisibleText("ANTARTICA");


    }*/

    @Test (groups = {"bonding","strong_ties"})
    public void KeyboardAndMouse() throws InterruptedException{

        driver.get("http://sinemayk.blogspot.com/");
        Actions action = new Actions(driver);
        WebElement araButonu = driver.findElement(By.xpath("//button[@class='search-expand touch-icon-button']"));
        Action click = action.moveToElement(araButonu).click().keyDown(araButonu, Keys.SHIFT).sendKeys("git komutlar").keyUp(araButonu, Keys.SHIFT).build();
        click.perform();
        Thread.sleep(5000);
        System.out.println("GiT KOMUTLAR yazisi yazildi");
    }

    @Test (groups = {"bonding"})
    public void Satirici() throws InterruptedException{

        driver.get("http://demo.guru99.com/test/write-xpath-table.html");
        System.out.println(driver.findElement(By.xpath("//table/tbody/tr[2]/td[2]")).getText());


        driver.get("http://demo.guru99.com/test/newtours/");
        System.out.println(driver.findElement(By.xpath("//table[@width=\"270\"]/tbody/tr[4]/td")).getText());


    }

    @Test (groups = {"strong_ties"})
    public void DinamikTablo() throws InterruptedException{

        driver.get("http://demo.guru99.com/test/web-table-element.php");
        //Dinamik tablodaki sütun ve satır sayısını bulma
        List<WebElement> sutun = driver.findElements(By.xpath("//*[@id=\"leftcontainer\"]/table/thead/tr/th"));
        System.out.println("Sutun sayisi: " + sutun.size());
        List<WebElement> satir = driver.findElements(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr"));
        System.out.println("Satir sayisi: " + satir.size());

        //Dinamik tablodaki 7. satirdaki sirket degerlerini bulma
        System.out.println("7. satirdaki sirket: " + driver.findElement(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr[7]/td[1]")).getText());
        System.out.println("7. satirdaki sirketin Prev Close degeri: " + driver.findElement(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr[7]/td[3]")).getText());

        //Dinamik tablodaki belirli bir sütundaki maximum değeri bulma

        double r=0;
        for (int i =0; i<satir.size();i++)
        {
           String max = driver.findElement(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr["+(i+1)+"]/td[4]")).getText();
            double maxsayi=0;
            maxsayi = Double.valueOf(max);
           System.out.println("Simdiki Ucret degerleri " + i + ": " + maxsayi);
           if(maxsayi>r)
           {
               r= maxsayi;
           }


        }
        System.out.println("Maximum current price is : "+ r);

        //Bütün tablonun her satırındaki değerleri getirme

        driver.get("http://demo.guru99.com/test/table.html");
        List<WebElement> satirlar = driver.findElements(By.xpath("/html/body/table/tbody/tr"));
        int satirsayisi = satirlar.size();
        System.out.println("Kac satir var: "+ satirsayisi);
        for (int i=1; i<=satirsayisi; i++)
        {
            List<WebElement> sutunlar = driver.findElements(By.xpath("/html/body/table/tbody/tr["+i+"]/td"));
            int sutunsayisi = sutunlar.size();
            System.out.println("Kac sutun var: "+ sutunsayisi);
            for (int y=1; y<=sutunsayisi; y++)
            System.out.println(driver.findElement(By.xpath("/html/body/table/tbody/tr["+i+"]/td["+y+"]")).getText());
        }
    }

    @Test (priority = 6)
    public void toolTipStatik() throws InterruptedException{

        driver.get("https://www.seleniumhq.org");
         WebElement element = driver.findElement(By.linkText("Browser Automation"));
         String toolTipMetni = element.getAttribute("title");
         System.out.println("GetAttribute yontemiyle ToolTip'in Metni: " +toolTipMetni);
         Assert.assertEquals("Return to Selenium home page", toolTipMetni);
    }

    @Test (groups = {"bonding"})
    public void toolTipJQuery() {
        driver.get("https://jqueryui.com/tooltip/");
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='demo-frame']")));
        WebElement elementJQuery = driver.findElement(By.xpath("/html[1]/body[1]/p[4]/input[1]"));

        Actions action = new Actions(driver);
        action.moveToElement(elementJQuery).build().perform();
        WebElement toolTipElement = driver.findElement(By.xpath("//div[@class='ui-helper-hidden-accessible']//div[1]"));

        String toolTipText = toolTipElement.getText();
        System.out.println("JQuery ile yazilan Tooltip metni: " +toolTipText);
        Assert.assertEquals("We ask for your age only for statistical purposes.", toolTipText);

    }


    @AfterTest
    public void tearDown(){
        driver.quit();
    }




    }

