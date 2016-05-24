package com.gtulla.movies;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.gtulla.movies.adapters.MovieArrayAdapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class MoviesActivity extends AppCompatActivity {

    private static String MOVIE_DB_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    private SwipeRefreshLayout swipeContainer;
    private List<Movie> movieList = null;
    private MovieArrayAdapter adapter = null;
    @BindView(R.id.lvItems) ListView lvItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);
        movieList = new ArrayList<>();
        lvItems = (ListView) findViewById(R.id.lvItems);
        adapter = new MovieArrayAdapter(this, movieList);
        lvItems.setAdapter(adapter);
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(MOVIE_DB_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieArray = null;
                try {
                    movieArray = response.getJSONArray("results");
                    movieList.addAll(Movie.convert(movieArray));
                    adapter.notifyDataSetChanged();
                    Log.d("DEBUG", movieList.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }
}
