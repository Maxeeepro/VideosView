package com.example.videosview

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import android.view.View
import java.util.ArrayList
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import model.Movies
import model.Movie
import model.Videos
import web.CustomAdapter
import web.GetDataService
import web.RetrofitCreateInstance

class SecondActivity : AppCompatActivity() {
    private var adapter: CustomAdapter? = null
    private var recyclerView: RecyclerView? = null
    internal var myMovies: MutableList<Videos> = ArrayList()
    internal lateinit var movies: Movies
    private var moviesObservable: Observable<Movies>? = null
    private val compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById<View>(R.id.recycler) as RecyclerView?
        adapter = CustomAdapter(this, myMovies)
        val layoutManager = LinearLayoutManager(this@SecondActivity)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = adapter
        loadMovies()
    }

    fun loadMovies() {
        /*
        Get Retrofit Client and concat endPoint to this URL
         */
        val getDataService = RetrofitCreateInstance.getClient()
                .create<GetDataService>(GetDataService::class.java!!)
        /*
        Get List of the Movies from the getAllMovies() method
         */
        moviesObservable = getDataService.allMovies
        compositeDisposable.add(moviesObservable!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<Movies>() {
                    override fun onNext(movies: Movies) {
                        this@SecondActivity.movies = movies
                        updateData()
                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onComplete() {

                    }
                }))

    }

    /*
        Using Call type from Retrofit2
        Call type uses for sending request to the webserver
         */
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
    private fun updateData() {
        for (movie in movies.categories) {
            for (video in movie.videos) {
                myMovies.add(video)
            }
        }
        adapter!!.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        /*
        Delete all subscription onDestroy
         */
        compositeDisposable.clear()
    }
}