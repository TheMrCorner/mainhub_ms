package com.mrcorner.mainhub.utils;

import org.springframework.stereotype.Component;

@Component
public class PathUtils {
    public static final String API_VERSION = "v1";

    public static final String API_BASE_PATH = "/api/";

    public static String getApiVersion(){
        return API_VERSION;
    }

    public static  String getApiBasePath(){
        return API_BASE_PATH;
    }
}
