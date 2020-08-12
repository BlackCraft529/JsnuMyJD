package com.jsnu.jd.jsnujd.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author 魏荣轩
 * @date 2020/8/8 21:00
 */
public class AddressUtil {
    /**
     * json工具对象
     */
    private static ObjectMapper jsonObjectMapper =  new ObjectMapper();

    private final static String KEY = "dd03a58ee261851088a83d4950ef4a2b";
    private final static String URL_HEAD = "https://restapi.amap.com/v3/ip?output=json&key=" ;
    public static String getLocationByIp(String ip) throws IOException {
        String urlStr= URL_HEAD + KEY + "&ip=" + ip ;
        URL url = new URL(urlStr);
        HttpsURLConnection urlCon = (HttpsURLConnection) url.openConnection();
        urlCon.connect();
        BufferedReader br = new BufferedReader(
                new InputStreamReader(urlCon.getInputStream(), StandardCharsets.UTF_8));
        //地址json串
        StringBuilder addressBuilder = new StringBuilder();
        String content = null ;
        while((content=br.readLine())!=null) {
            addressBuilder.append(content);
        }
        ObjectNode addressNode = jsonObjectMapper.readValue(addressBuilder.toString(),ObjectNode.class);
        return addressNode.get("city").toString().replaceAll("\"","").replaceAll("市","");
    }
}
