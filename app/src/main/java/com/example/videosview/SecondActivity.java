package com.example.videosview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import model.Movies;
import model.Movie;
import model.Videos;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import web.CustomAdapter;
import web.GetDataService;
import web.RetrofitCreateInstance;

public class SecondActivity extends AppCompatActivity {
    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    List<Videos> myMovies = new ArrayList<>();
    Movies movies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        adapter = new CustomAdapter(this, myMovies);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SecondActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setAdapter(adapter);
        recyclerView.setAdapter(adapter);
        recyclerView.setAdapter(adapter);
        loadMovies();
    }
    public void loadMovies(){
        GetDataService getDataService = RetrofitCreateInstance.getClient()
                .create(GetDataService.class);
        Call<Movies> call = getDataService.getAllMovies();
        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                if(response.isSuccessful()){
                    SecondActivity.this.movies = response.body();
                    Log.d("OnResponse","OnResponse" );
                    updateData();
                }
            }
            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                Log.d("OnFailure",t.toString() );
            }
        });
    };
    private void updateData(){
        for(Movie movie : movies.categories){
            for(Videos video : movie.videos){
                myMovies.add(video);
            }
        }
        adapter.notifyDataSetChanged();
    }
}
