package com.tool;

import com.tool.util.FileUtil;
import com.tool.util.GeneralUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ChiYuan
 * Date: 2020/12/31 下午 06:24
 * To change this template use File | Settings | File Templates.
 */
public class PunchCard {
    private static Properties properties;

    /**
     * 元件初始化
     *
     * @param iniFile iniFile
     */
    public PunchCard(String iniFile) throws Exception {
        properties = FileUtil.loadProp(new File(iniFile), "UTF-8");
    }

    public static void main(String[] args) throws Exception {
        System.setProperty("https.protocols", "TLSv1.2");
        PunchCard punchCard = new PunchCard(args[0]);
        punchCard.start();
    }

    private void start() {
        HashMap<String, String> cookies = getCookie();
        HashMap<String, String> headers = getHeaders();
        if (cookies != null) {
            try {
                Connection.Response contentRequest = Jsoup.connect("https://pro.104.com.tw/psc2/api/f0400/clockin")
                        .cookies(cookies).headers(headers).method(Connection.Method.POST).ignoreContentType(true).timeout(30000).execute();
                if (contentRequest.statusCode() == 200) {
                    System.out.println(contentRequest.body());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void getTabCookie() {
        System.setProperty("webdriver.chrome.driver", PunchCard.getSetting("webdriver.chrome.driver", String.class, ""));
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();

        // 關閉推播
        capabilities.setCapability("profile.default_content_setting_values.notifications", 2);
        capabilities.setCapability("credentials_enable_service", false);
        capabilities.setCapability("profile.password_manager_enabled", false);
        // Chrome各種設置
        ChromeOptions options = new ChromeOptions();
        List<String> addArguments = new ArrayList<>();
        //隱藏視窗作業
//        addArguments.add("--headless");
        // 無痕模式
//        addArguments.add("--incognito");
        //視窗最大化
        addArguments.add("--start-maximized");
        addArguments.add("disable-infobars");
        addArguments.add("--disable-extensions");
        addArguments.add("--disable-notifications");
        options.addArguments(addArguments);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        try {
            WebDriver webDriver = new ChromeDriver(capabilities);
            webDriver.get("https://login.taobao.com/member/login.jhtml");
            Thread.sleep(3000);
            WebElement webElement = webDriver.findElement(By.cssSelector("input[name=\"fm-login-id\"]"));
            webElement.sendKeys("886-0905364075");
            Thread.sleep(3000);
            webElement = webDriver.findElement(By.cssSelector("input[name=\"fm-login-password\"]"));
            webElement.sendKeys("eland4321");
            Thread.sleep(3000);
//            webElement = webDriver.findElement(By.xpath("//label[@class='Login__rememberLabel']"));
//            webElement.click();
//            Thread.sleep(3000);
            webElement = webDriver.findElement(By.xpath("//button[@class='fm-button fm-submit password-login']"));
            webElement.click();
            Thread.sleep(3000);
            Set<Cookie> cookieSet = webDriver.manage().getCookies();
            webDriver.quit();
            webDriver = new ChromeDriver(options);
            webDriver.get("https://world.taobao.com/");
            for (Cookie cookie : cookieSet) {
                webDriver.manage().addCookie(cookie);
            }
            webDriver.get("https://world.taobao.com/");
            Thread.sleep(3000);
            webDriver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private HashMap<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("Accept-Language", "zh-TW,zh;q=0.9,en-US;q=0.8,en;q=0.7");
        headers.put("Content-Length", "0");
        headers.put("Content-type", "application/json");
        headers.put("Origin", "https://pro.104.com.tw");
        headers.put("Referer", "https://pro.104.com.tw/psc2");
        headers.put("Sec-Fetch-Dest", "empty");
        headers.put("Sec-Fetch-Mode", "cors");
        headers.put("Sec-Fetch-Site", "same-origin");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");
        headers.put("X-Request", "JSON");
        headers.put("X-Requested-With", "XMLHttpRequest");
        return headers;
    }

    private HashMap<String, String> getCookie() {
        HashMap<String, String> cookies = new HashMap<>();
        System.setProperty("webdriver.chrome.driver", PunchCard.getSetting("webdriver.chrome.driver", String.class, ""));
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();

        // 關閉推播
        capabilities.setCapability("profile.default_content_setting_values.notifications", 2);
        capabilities.setCapability("credentials_enable_service", false);
        capabilities.setCapability("profile.password_manager_enabled", false);
        // Chrome各種設置
        ChromeOptions options = new ChromeOptions();
        List<String> addArguments = new ArrayList<>();
        //隱藏視窗作業
//        addArguments.add("--headless");
        // 無痕模式
//        addArguments.add("--incognito");
        //視窗最大化
        addArguments.add("--start-maximized");
        addArguments.add("disable-infobars");
        addArguments.add("--disable-extensions");
        addArguments.add("--disable-notifications");
        options.addArguments(addArguments);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        try {
            WebDriver webDriver = new ChromeDriver(capabilities);
            webDriver.get("https://cloud.nueip.com/login");
            Thread.sleep(3000);
            WebElement webElement = webDriver.findElement(By.cssSelector("input[name=\"inputCompany\"]"));
            webElement.sendKeys(PunchCard.getSetting("company", String.class, ""));
            Thread.sleep(3000);
            webElement = webDriver.findElement(By.cssSelector("input[name=\"inputID\"]"));
            webElement.sendKeys(PunchCard.getSetting("id", String.class, ""));
            Thread.sleep(3000);
            webElement = webDriver.findElement(By.cssSelector("input[name=\"inputPassword\"]"));
            webElement.sendKeys(PunchCard.getSetting("password", String.class, ""));
            Thread.sleep(3000);
            webElement = webDriver.findElement(By.xpath("//button[@id='login-button']"));
            webElement.click();
            Thread.sleep(3000);
            new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"mm-0\"]/div[3]/div/div[2]/div[4]/div[2]/div[1]/div[1]/div")));
            webElement = webDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div[2]/div[4]/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[1]/div"));
            webElement.click();
            Set<Cookie> cookieSet = webDriver.manage().getCookies();
            for (Cookie cookie : cookieSet) {
                cookies.put(cookie.getName(), cookie.getValue());
            }
            Thread.sleep(3000);
            webDriver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cookies;
    }

    /**
     * 取得Fetcher的Properties設定值
     *
     * @param key          key
     * @param c            class
     * @param defaultValue default
     * @param <T>          type
     * @return value
     */
    public static <T> T getSetting(String key, Class c, T defaultValue) {
        return GeneralUtil.getSetting(properties, key, c, defaultValue);
    }
}