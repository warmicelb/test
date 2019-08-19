package com.ice.se;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @ClassName URLTest
 * @Description TODO
 * @Author liubin
 * @Date 2019/7/9 3:07 PM
 **/
public class URLTest {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.baidu.com/s?wd=url&rsv_spt=1&rsv_iqid=0xa6df39e900073675&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&rqlang=cn&tn=baiduhome_pg&rsv_enter=0&oq=url&rsv_t=3cb38miwXpTj5fsTzfdMzdM41TRN94NHR%2FgXbOFu%2FTFswxSk0AMIYqEH1WOxioORLzYc&rsv_pq=9a05658b000c69ec");
//        System.out.println(url.getProtocol());
//        System.out.println(url.getHost());
//        System.out.println(url.getPort());
//        System.out.println(url.getAuthority());
//        System.out.println(url.getQuery());
        URLConnection urlConnection = url.openConnection();
        if(urlConnection instanceof HttpURLConnection){
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line = null;
            while((line = bufferedReader.readLine())!=null){
                response.append(line);
            }
            System.out.println(response.toString());
        }
    }
}
