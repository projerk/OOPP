package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TranslateAPI {

    public static String translate(String target) {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--log-level=3");
        String res = "";
        WebDriver driver = new ChromeDriver(options);
        String encodedTarget = URLEncoder.encode(target, StandardCharsets.UTF_8);

        String desUrl = "https://translate.google.com/?hl=vi&sl=vi&tl=en&text=" + encodedTarget + "&op=translate";
        driver.get(desUrl);

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//*[@id=\"yDmH0d\"]/c-wiz/div/div[2]/c-wiz/div[2]/c-wiz/div[1]/div[2]/div[2]/c-wiz/div/div[6]/div/div[1]/span[1]/span/span")
            ));

            res = element.getText();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            driver.quit();
        }

        return res;
    }
}