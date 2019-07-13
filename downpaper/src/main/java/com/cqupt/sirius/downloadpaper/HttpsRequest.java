package com.cqupt.sirius.downloadpaper;

import org.openqa.selenium.Cookie;

import javax.net.ssl.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpsRequest {
    //添加ssl协议
    static {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    public void checkClientTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                    public void checkServerTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
        };
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
        }
    }

    public static void downloadToFile(String urlStr, String fileName, String savePath){
        try {
            byte[] getData = downloadDataFromUrl(urlStr);
            downloadToFile(getData, fileName, savePath);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public static void downloadToFile(byte[] data, String fileName, String savePath){
        try {
            File saveDir = new File(savePath);
            if(!saveDir.exists()){
                saveDir.mkdir();
            }
            File file = new File(saveDir+File.separator+fileName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            if(fos!=null){
                fos.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static URLConnection getConnect(String urlStr) throws IOException{
        return getConnect(urlStr, null);
    }

    public static URLConnection getConnect(String urlStr, String cookies)throws IOException{
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");
        //conn.setRequestProperty("Cookie","fp=9151bb7be803da6b9acb13b3546f70fb; __gads=ID=3b9eff35ad16a003:T=1557388093:S=ALNI_MYla-nLi0-z-e2LtoqQMKfUiUM07w; cookieconsent_status=dismiss; s_ecid=MCMID%7C01244611405859368161518647923828219802; AMCV_8E929CC25A1FB2B30A495C97%40AdobeOrg=1687686476%7CMCIDTS%7C18037%7CMCMID%7C01244611405859368161518647923828219802%7CMCAAMLH-1558925200%7C11%7CMCAAMB-1558925200%7CRKhpRz8krg2tLO6pguXWp5olkAcUniQYPHaMWWgdJ3xzPWQmdj0y%7CMCOPTOUT-1558327600s%7CNONE%7CMCAID%7CNONE%7CvVersion%7C3.0.0; utag_main=v_id:016ad322494a0005462305b8bf1d03073004006b00bd0$_sn:1$_ss:0$_st:1558322429049$ses_id:1558320400717%3Bexp-session$_pn:3%3Bexp-session$vapi_domain:ieee.org; ipCheck=113.251.167.51; WLSESSION=220357260.20480.0000; TS011813a0_26=014082121d35da66c2e94cf12821c3fb6423bd2edaa763cf8b3a33f1ecf4d2bebaaff9795927264f49aab11837839473fb3b8d69e9884861efc2fe2786579c149c6e37ce0c; ipList=113.251.167.51; TS01f64340=012f350623cf5f4263d0a7dd033222d2bab90db2d49777df6037cd345e293bd89b54be01ca79ab7d20c824b2811f57249d82eafd9a5ae443b242bdbce0c4f9f02532026139f4b9b443c13bdbd7bfc2f6e3b18164afe7748531d981406e84e4ec89caff2a53; JSESSIONID=YlBQKvDUHd-Hwhkp0be32DSvERH8AHWm-kTafdH12__FyV7R0R-z!772104591; ERIGHTS=Fncvg5hF0AlBxx3sxxtUVUkCAtLYweV0Di*GuXPL3OloaJghgFx2BJ1HMFwx3Dx3D-18x2dauJiEW6kwHsPrQKQ8eH2vwx3Dx3D6S13V8keiMN0rIFb7YSd9Ax3Dx3D-74WfbdZD876j9q2bPIopZAx3Dx3D-hSx2Fy2PwxxNx2B4GkiZY7zyx2B7gx3Dx3D; TS011813a0=012f350623e58d29aa2c04d4c1e4c4aadc98e4adf2570d4f02d1b0951144b64b299e8e6b31b9662af7ec1bfebedea14fe0eb681ab91bd709e93a1715d3fee5ca1615b89a843930cd5f8de806160cce5d55162984646103bd8b8e22c91763f31adf3b40e941e1bfde6905a10923d283bf159e7e8b636f5501c8237a8446c4d18cc54ee3501d; TS01d430e1=012f3506233e61b6594e7bb3b1bf3ff9585aeeb9719777df6037cd345e293bd89b54be01caf7e88d516f792ca6636ca30912af804823e3f9cbc6ef56f5e89ec9390ca3c50eae4cad2a8882f08165054112eaeaf20749a42936b853cdba588ae1b96ba3546aff89014b9f0a0d644130bb61c505328988e06a3528eac896c9085464e9935fd373f9a5e9bccb78d8f1a081044334ffd8ef1aebdbf9b8fe0adbcf99e72b476fcc2b37effb863dba18aad87e2bdb00b419; xpluserinfo=eyJpc0luc3QiOiJ0cnVlIiwiaW5zdE5hbWUiOiJDSE9OR1FJTkcgVU5JViBPRiBQT1NUIEFORCBURUxFQ09NIiwicHJvZHVjdHMiOiJJQk18SUVMfFZERXxOT0tJQSBCRUxMIExBQlN8In0=; seqId=20818");
        if (cookies != null){
            conn.setRequestProperty("Cookie", cookies);
        }
        conn.connect();
        return conn;
    }

    public static byte[] downloadDataFromUrl(String urlStr)  throws IOException{
        URLConnection conn = getConnect(urlStr);
        return downloadDataFromUrl(conn);
    }



    public static byte[] downloadDataFromUrl(URLConnection conn) throws IOException{
        InputStream inputStream = conn.getInputStream();
        byte[] getData = readInputStream(inputStream);
        if(inputStream!=null){
            inputStream.close();
        }
        return getData;
    }

    public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }
    public static void main(String[] args) throws IOException {
        String str = "?";
        System.out.println(str);
        str = str.replaceAll("\\?","");
        System.out.println(str);
    }




}