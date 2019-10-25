package com.ice.elastic;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.RequestLine;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import java.io.IOException;

/**
 * 测试连接elasticSearch
 * @ClassName ElasticSearchTest
 * @Description TODO
 * @Author liubin
 * @Date 2019/9/23 7:19 PM
 **/
public class ElasticSearchTest {

    public static void main(String[] args) {
        try {
            RestClient restClient = RestClient.builder(
                    new HttpHost("localhost", 9200, "http")).build();
            Request request = new Request(
                    "GET",
                    "/");

            Response response = restClient.performRequest(request);
            String responseBody = EntityUtils.toString(response.getEntity());
            RequestLine requestLine = response.getRequestLine();
            HttpHost host = response.getHost();
            int statusCode = response.getStatusLine().getStatusCode();
            Header[] headers = response.getHeaders();
            System.out.println("[requestLine]"+requestLine);
            System.out.println("[host]"+host);
            System.out.println("[statusCode]"+statusCode);
            for (Header header:headers){
                System.out.println("[header]"+header);
            }
            System.out.println(responseBody);
            restClient.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
