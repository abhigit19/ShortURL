package com.app.shortURL.service;

import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
/**
 * @author abhijit
 */
@Service
public class ShortUrlService {

    public static final String HOST_URL = "http://localhost:8080/s/";
    public static final String BASE62 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private static Map<String, String> tinyUrlMap = new HashMap<>();
    /**
     * @param  fullUrl
     * @return short Url
     */
    public String getShortUrl(String fullUrl) throws MalformedURLException {
        String tinyName=getTinyNames();
        String shortUrl = HOST_URL.concat(tinyName);
        tinyUrlMap.put(tinyName, fullUrl);
        return shortUrl;
    }
    /**
     * @return Short Url Name of length 5 using the 62 different character set
     *
     */
    private String getTinyNames() {
        StringBuilder random = new StringBuilder();
        IntStream.range(0, 5).forEach(
                y -> random.append(BASE62.charAt((int) Math.floor(Math.random() * BASE62.length())))
        );
        return random.toString();
    }
    public Map<String, String> getTinyUrlMap(){
        return ShortUrlService.tinyUrlMap;
    }

}
