package com.gtulla.movies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gtulla.movies.Movie;
import com.gtulla.movies.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gtulla on 5/21/16.
 */
public class MovieArrayAdapter extends ArrayAdapter<Movie> {
    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Movie movie = getItem(position);
        ViewHolder holder;
        // Check if an existing view is being reused, otherwise inflate the view
        if(convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.item_movie, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ImageView imgView = (ImageView)convertView.findViewById(R.id.img);
        TextView title = (TextView)convertView.findViewById(R.id.title);
        TextView overview = (TextView)convertView.findViewById(R.id.overview);
       // ImageView imgView = holder.imgView;
        imgView.setImageResource(0);
        Picasso.with(getContext()).load(movie.getImgUrl()).into(imgView);
        title.setText(movie.getTitle());
        overview.setText(movie.getOverview());
        return convertView;
    }
    public static class ViewHolder {
        @BindView(R.id.title)TextView title;
        @BindView(R.id.overview)TextView overview;
        @BindView(R.id.img)ImageView imgView;
        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
