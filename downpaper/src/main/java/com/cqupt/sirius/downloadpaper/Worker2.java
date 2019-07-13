package com.cqupt.sirius.downloadpaper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.net.ssl.HttpsURLConnection;
import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Worker2 implements Runnable {
    private Map<String, String> cookies;
    private List<Paper> list;
    private Integer count = -1;
    public Worker2(Map<String, String> cookies) {
        this.list = new ArrayList<>();
        this.cookies = cookies;
    }

    public void addAll(List<Paper> list){
        this.list.addAll(list);
    }

    public void add(Paper paper){
        list.add(paper);
    }


    public int size(){
        return list.size();
    }

    @Override
    public void run() {
        int MAX = list.size();
        while (true){
            Paper current = null;
            synchronized (count){
                if(count<MAX-1){
                    current = list.get(++count);
                }else {
                    break;
                }
            }
            String fileName = current.getName() + ".pdf";
            File file = new File("D:\\资料\\会议总结\\通信学报\\" + fileName);
            if(file.exists()){
                continue;
            }
            String path = current.getPath().substring(3,9);
            String pdfUrl = "http://www.infocomm-journal.com/txxb/CN/article/downloadArticleFile.do?attachType=PDF&id=" + path;
            try {
                URLConnection pdfConn = HttpsRequest.getConnect(pdfUrl,getCookies());
                byte[] pdfdata = HttpsRequest.downloadDataFromUrl(pdfConn);
                Map<String, List<String>> pdfheads = pdfConn.getHeaderFields();
                List<String> pdfcookieList = pdfheads.get("Set-Cookie");
                if (pdfcookieList!=null){
                    for(String cookie : pdfcookieList){
                        int start = cookie.indexOf("=");
                        int end = cookie.indexOf(";");
                        String key = cookie.substring(0,start);
                        String value = cookie.substring(cookie.indexOf("=")+1,end);
                        if(!value.equals("")){
                            cookies.put(key, value);
                        }
                    }
                }
                HttpsRequest.downloadToFile(pdfdata, fileName, "D:\\资料\\会议总结\\通信学报\\");
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
