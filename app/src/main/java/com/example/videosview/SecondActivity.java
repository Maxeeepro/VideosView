package com.example.videosview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
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
    private Observable<Movies> moviesObservable;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        adapter = new CustomAdapter(this, myMovies);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SecondActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        loadMovies();
    }
    public void loadMovies(){
        /*
        Get Retrofit Client and concat endPoint to this URL
         */
        GetDataService getDataService = RetrofitCreateInstance.getClient()
                                                              .create(GetDataService.class);
        /*
        Get List of the Movies from the getAllMovies() method
         */
        moviesObservable = getDataService.getAllMovies();
        compositeDisposable.add(moviesObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Movies>() {
                    @Override
                    public void onNext(Movies movies) {
                        SecondActivity.this.movies = movies;
                        updateData();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));

        }
//        call.enqueue(new Callback<Movies>() {
//            @Override
//            public void onResponse(Call<Movies> call, Response<Movies> response) {
//                if(response.isSuccessful()){
//                    SecondActivity.this.movies = response.body();
//                    Log.d("OnResponse","OnResponse" );
//                    updateData();
//                }
//            }
//            @Override
//            public void onFailure(Call<Movies> call, Throwable t) {
//                Log.d("OnFailure",t.toString() );
//            }
//        });
        private void updateData(){
            for(Movie movie : movies.categories){
                for(Videos video : movie.videos){
                    myMovies.add(video);
                }
            }
            adapter.notifyDataSetChanged();
        }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*
        Delete all subscription onDestroy
         */
        compositeDisposable.clear();
    }
}


