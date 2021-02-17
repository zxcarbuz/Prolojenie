package com.example.prolojenie.common;

import org.json.JSONException;
import org.json.JSONObject;

public class MovieItem {
    private String movieId;
    private String name;
    private String poster;

    public final static String MOVIE_ID = "movieId";
    public final static String NAME = "name";
    public final static String POSTER = "poster";

    public MovieItem(JSONObject movieObj) throws JSONException {
        movieId = movieObj.getString(MOVIE_ID);
        name = movieObj.getString(NAME);
        poster = movieObj.getString(POSTER);

    }

    public String getMovieId() {
        return movieId;
    }

    public String getName() {
        return name;
    }

    public String getPoster() {
        return poster;
    }
}
