package com.example.prolojenie.common;

public class URLs {
    private static final String API = "";
    private static final String API_IMAGE = API + "/up/images/";
    private static final String MOVIES_WITH_FILTER = API + "/movies?filter=";

    public static String getApiImage(String shortUrl) {
        return API_IMAGE + shortUrl;
    }

    public static String getMoviesWithFilter(String filter) {
        return MOVIES_WITH_FILTER + filter;
    }
}
