package com.example.prolojenie;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.prolojenie.common.MovieItem;
import com.example.prolojenie.common.URLs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BlankFragment extends Fragment {

    String filter;
    LayoutInflater inflater;
    Context context;

    public BlankFragment(String filter) {
        this.filter = filter;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        context = inflater.getContext();
        return inflater.inflate(R.layout.blank_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initMoviesData();
    }

    private void initMoviesData() {
    RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLs.getMoviesWithFilter(filter), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                initMovies(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    List<MovieItem> movieItems = new ArrayList<>();
    private void initMovies(JSONArray response) {
        RecyclerView moviesView = getView().findViewById(R.id.moviesView);
        for (int position = 0; position < response.length(); position++) {
            try {
                movieItems.add(new MovieItem((JSONObject) response.get(position)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        moviesView.setAdapter(new MovieItemAdapter());
    }

    private class MovieItemAdapter extends RecyclerView.Adapter<MovieItemAdapter.ViewHolder> {
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = inflater.inflate(R.layout.item_movie, parent, false);
            return new ViewHolder(itemView);

        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            MovieItem movieItem = movieItems.get(position);
            holder.nameView.setText(movieItem.getName());
            Glide.with(context).load(URLs.getApiImage(movieItem.getPoster())).into(holder.posterView);

        }

        @Override
        public int getItemCount() {
            return movieItems.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            final ImageView posterView;
            final TextView nameView;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                posterView = itemView.findViewById(R.id.posterView);
                nameView = itemView.findViewById(R.id.nameView);
            }
        }
    }
}