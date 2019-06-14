package com.cqupt.sirius.downloadpaper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.*;

public class DownloadPaper {
    private static ChromeDriver driver;

    static {
        System.setProperty("webdriver.chrome.driver","C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        driver = new ChromeDriver();
    }
    private void openAllItems(String url, int itemNum){
        driver.get(url);
        int size = (int) Math.ceil(itemNum /25);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < size; i++){
            System.out.println("第" + (i+1) + "页");
            WebElement btn = driver.findElementByClassName("loadMore-btn");
            driver.executeScript("arguments[0].scrollIntoView()",btn);
            try{
                btn.click();
            }catch (Exception e){
                e.printStackTrace();
                break;
            }
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//                continue;
//            }
        }
    }

    private void downloadPaper(String url, int itemNum){
        openAllItems(url, itemNum);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> items = driver.findElementsByXPath("//*[@class = \"List-results-items\"]");
        WebElement title = items.get(10).findElement(By.tagName("a"));
        WebElement pdf = items.get(10).findElement(By.className("icon-pdf"));
        String downloadURL = pdf.getAttribute("href");
        Set<Cookie> cookies = driver.manage().getCookies();
        //建立cookies
        Map<String, String> map = new HashMap<>();
        for(Cookie cookie : cookies){
            map.put(cookie.getName(),cookie.getValue());
        }
        Worker worker = new Worker(map, items);
        new Thread(worker).start();
        new Thread(worker).start();
        new Thread(worker).start();
        new Thread(worker).start();
//        try {
//            byte[] data = HttpRequest.downloadDataFromUrl(downloadURL);
//            HttpRequest.downloadToFile(data,"hello", "D:\\downloadtest\\");
//            String html = new String(data);
//            Document doc = Jsoup.parse(html);
//            Elements elements = doc.getElementsByTag("iframe");
//            System.out.println(elements.get(0).attr("src"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    public static void main(String[] args) {
        DownloadPaper downloadPaper = new DownloadPaper();
        downloadPaper.downloadPaper("https://ieeexplore.ieee.org/xpl/mostRecentIssue.jsp?punumber=7957740", 72);
    }
}
