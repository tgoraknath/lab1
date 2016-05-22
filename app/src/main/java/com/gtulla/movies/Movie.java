package com.gtulla.movies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gtulla on 5/21/16.
 */
public class Movie {
    private static String IMG_URL = "https://image.tmdb.org/t/p/w342/%s";
    private String title;
    private String imgUrl;
    private String overview;

    public Movie() {
    }
    public Movie(String title, String imgURI, String overview) {
       this.title =  title;
        this.imgUrl = String.format(IMG_URL, imgURI);
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getOverview() {
        return overview;
    }
    public static List<Movie> convert(JSONArray array) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        Movie movie = null;
        JSONObject each = null;
        for (int i = 0; i < array.length(); i++) {
            each = array.getJSONObject(i);
            movie = new Movie(each.getString("title") ,
                    each.getString("poster_path"),
                    each.getString("overview"));
            movies.add(movie);
        }
        return movies;
    }
}
