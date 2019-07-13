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
        driver.executeScript("var temp_form = document.createElement(\"form\");\n" +
                "temp_form.method = \"post\";\n" +
                "temp_form.style.display = \"none\";\n" +
                "temp_form.target = \"_self\";\n" +
                "temp_form.action = 'http://www.infocomm-journal.com/txxb/CN/article/advancedSearchResult.do';\n" +
                "var opt = document.createElement(\"textarea\");\n" +
                "opt.name = 'searchSQL';\n" +
                "opt.value = '((((((((((((安全[Title]) OR 安全[Abstract]) OR 安全[Keyword]) OR 网络安全[Title] OR) + 网络安全[Abstract]) OR 网络安全[Keyword]) AND 3[Journal]) AND 2016[FromYear]) AND 2019[ToYear]) AND year[Order])';\n" +
                "temp_form.appendChild(opt);\n" +
                "document.body.appendChild(temp_form);\n" +
                "temp_form.submit();");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void findAllPaper(List<WebElement> items, Worker2 worker){
        for(WebElement element : items){
            String path = element.getAttribute("id");
            WebElement titleEle = element.findElement(new By.ByClassName("txt_biaoti"));
            String title = titleEle.getText();
            Paper paper = new Paper(title, path);
            worker.add(paper);
        }
    }

    private void downloadPaper(String url, int itemNum){
        openAllItems(url, itemNum);
        Set<Cookie> cookies = driver.manage().getCookies();
        Map<String, String> map = new HashMap<>();
        for(Cookie cookie : cookies){
            map.put(cookie.getName(),cookie.getValue());
        }
        int size = (int) Math.ceil(itemNum /30);
        Worker2 worker = new Worker2(map);
        for (int i = 0; i < size; i++){
            List<WebElement> items = driver.findElementsByXPath("//*[@class = \"noselectrow\"]");
            findAllPaper(items, worker);
            WebElement btn = driver.findElementByClassName("next");
            driver.executeScript("arguments[0].scrollIntoView()",btn);
            try{
                btn.click();
            }catch (Exception e){
                e.printStackTrace();
                break;
            }
        }

        List<WebElement> items = driver.findElementsByXPath("//*[@class = \"noselectrow\"]");
        findAllPaper(items, worker);



//        WebElement title = items.get(10).findElement(By.tagName("a"));
//        WebElement pdf = items.get(10).findElement(By.className("icon-pdf"));
//        String downloadURL = pdf.getAttribute("href");
//        Set<Cookie> cookies = driver.manage().getCookies();
//        //建立cookies
//        Map<String, String> map = new HashMap<>();

//        Worker worker = new Worker(map, items);
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
        downloadPaper.downloadPaper("http://www.infocomm-journal.com/txxb/CN/1000-436X/home.shtml", 264);
    }
}
