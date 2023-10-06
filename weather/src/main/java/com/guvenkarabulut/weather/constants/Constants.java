package com.guvenkarabulut.weather.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {
    public static String API_URL;
    public static String ACCES_KEY;
    public static String ACCES_KEY_PARAM = "?access_key=";
    public static String QUERY_KEY_PARAM = "&query=";

    @Value("${weather-stack.api-url}")
    public void setApiUrl(String apiUrl) {
        Constants.API_URL = apiUrl;
    }
    @Value("${weather-stack.acces-key}")
    public void setAccesKey(String accesKey) {
        Constants.ACCES_KEY = accesKey;
    }
}
