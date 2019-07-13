package com.cqupt.sirius.downloadpaper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.net.ssl.HttpsURLConnection;
import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Worker implements Runnable {
    private Map<String, String> cookies;
    private List<WebElement> items;
    private int MAX;
    private int count = -1;
    public Worker(Map<String, String> cookies, List<WebElement> items) {
        this.cookies = cookies;
        this.items = items;
        MAX = items.size();
        count = 0;
    }


    @Override
    public void run() {
        while (count < MAX){
            WebElement item = items.get(count);
            count ++;
            WebElement title = item.findElement(By.tagName("a"));
            String name = title.getText();
            name = name.replaceAll("/","");
            name = name.replaceAll(":","");
            name = name.replaceAll("\\?","");
            File file = new File("D:\\资料\\会议总结\\通信学报\\" + name + ".pdf");
            if(file.exists()){
                continue;
            }
            WebElement pdf = item.findElement(By.className("icon-pdf"));
            String downloadURL = pdf.getAttribute("href");
            try {
                URLConnection conn = HttpsRequest.getConnect(downloadURL, getCookies());
                byte[] data = HttpsRequest.downloadDataFromUrl(conn);
                Map<String, List<String>> heads = conn.getHeaderFields();
                List<String> cookieList = heads.get("Set-Cookie");
                for(String cookie : cookieList){
                    int start = cookie.indexOf("=");
                    int end = cookie.indexOf(";");
                    String key = cookie.substring(0,start);
                    String value = cookie.substring(cookie.indexOf("=")+1,end);
                    if(!value.equals("")){
                        cookies.put(key, value);
                    }
                }
                String html = new String(data);
                Document doc = Jsoup.parse(html);
                Elements elements = doc.getElementsByTag("iframe");
                String pdfUrl = null;
                try{
                    pdfUrl = elements.get(0).attr("src");
                }catch (IndexOutOfBoundsException e){
                    System.out.println(count);
                    continue;
                }
                URLConnection pdfConn = HttpsRequest.getConnect(pdfUrl, getCookies());
                byte[] pdfdata = HttpsRequest.downloadDataFromUrl(pdfConn);
                Map<String, List<String>> pdfheads = pdfConn.getHeaderFields();
                List<String> pdfcookieList = heads.get("Set-Cookie");
                for(String cookie : pdfcookieList){
                    int start = cookie.indexOf("=");
                    int end = cookie.indexOf(";");
                    String key = cookie.substring(0,start);
                    String value = cookie.substring(cookie.indexOf("=")+1,end);
                    if(!value.equals("")){
                        cookies.put(key, value);
                    }
                }
                HttpsRequest.downloadToFile(pdfdata,name + ".pdf", "D:\\资料\\会议总结\\S&P\\2017\\");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private  String getCookies(){
        int size = cookies.size();
        Iterator<String> iterator = cookies.keySet().iterator();
        StringBuffer result = new StringBuffer();
        while (size>1){
            String key = iterator.next();
            String value = cookies.get(key);
            result.append(key + "=" + value + "; ");
            size--;
        }
        String key = iterator.next();
        String value = cookies.get(key);
        result.append(key + "=" + value);
        return result.toString();
    }
}
