package com.thoughtworks.gauge.maven;

import com.thoughtworks.gauge.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class StepImplementation {

    String username = "YOUR_USERNAME_LAMBDATEST";
    String accesskey = "YOUR_ACCESS_TOKEN";
    static RemoteWebDriver driver = null;
    String gridURL = "@hub.lambdatest.com/wd/hub";
    boolean status = false;

    @Step("Navigate to <https://www.google.co.in>")
    public void navigateTo(String url) throws InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build", "your build name");
        capabilities.setCapability("name", "your test name");
        capabilities.setCapability("platform", "Windows 10");
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("version", "71.0");
        capabilities.setCapability("visual", true);
        try {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        driver.get(url);
        Thread.sleep(2000);
    }

    @Step("Enter query <query> in the search box and submit")
    public void enterQuery(String queryL) throws InterruptedException {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Lambda Test");
        searchBox.submit();

    }

    @Step("The search results should show <Jupiter> as result")
    public void verifySearchResult(String resultString) {
        WebElement result = driver.findElement(By.className("LC20lb"));
        assertEquals(resultString, result.getText());
        driver.quit();
    }

}