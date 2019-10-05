package com.example.videosview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import model.Movie;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import web.RecyclerInterface;
import web.RetrofitAdapter;

public class MainActivity extends AppCompatActivity {
    private RetrofitAdapter retrofitAdapter;
    private RecyclerView recyclerView;
    private ArrayList<Movie> modelRecyclerArraylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        modelRecyclerArraylist = new ArrayList<>();
        retrofitAdapter = new RetrofitAdapter(this, modelRecyclerArraylist);

        recyclerView.setAdapter(retrofitAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        Log.i("onCreate", "onCreate");
        fetchJSON();
    }

    //create retrofit builder
    private void fetchJSON() {
        Retrofit retrofit = new Retrofit.Builder()
                //get base url from Recycler Interface
                .baseUrl(RecyclerInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        //creation retrofit with recyclerInterface in parameters
        RecyclerInterface api = retrofit.create(RecyclerInterface.class);

        Call<String> call = api.getString();
        //implementation of the Retrofit callback
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //write some logs
                Log.i("Responsestring", response.body().toString());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());
                        //getting JSON response body
                        String jsonresponse = response.body().toString();
                        //put this body into the writeRecycler method like input parameters
                        writeRecycler(jsonresponse);
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");
                    }
                }

            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("onEmptyResponse", "onFailure");
                //if this error exist will print error
                Log.i("onFailure", "error " + t.getMessage());
            }
        });
    }
    private void writeRecycler(String response) {
        try {

            //getting the whole json object from response
            JSONObject obj = new JSONObject(response);
            //create new array with JSON objects from response
            //initiate JSONArray by name of JSONArray
            JSONArray dataArray = obj.getJSONArray("categories");
            //iteration on array with JSON objects
            JSONObject jsonObject = dataArray.getJSONObject(0);
            JSONArray jsonArray = jsonObject.getJSONArray("videos");
//            .getJSONArray("categories");
            for (int i = 0; i < jsonArray.length(); i++) {
                //create an object of Model Class
                Movie modelRecycler = new Movie();
                //work with every element from this array
                //
                JSONObject dataobj = jsonArray.getJSONObject(i);
                Log.i("JSONobjects",dataobj.toString());
                //create object Movie type and setting objects from JSON to this object
                modelRecycler.setImageLarge(dataobj.getString("image-780x1200"));
                modelRecycler.setSubtitle(dataobj.getString("subtitle"));
                modelRecycler.setStudio(dataobj.getString("studio"));
                modelRecycler.setImageSmall(dataobj.getString("image-480x270"));
                //add to the collection my model object
                modelRecyclerArraylist.add(modelRecycler);
                //creation new object of the Retrofit adapter
                //this context and new ArrayList which were created earlier

            }
            retrofitAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        //find recycler view by ID in XML

        //set to recyclerView new adapter
        //recyclerView.setAdapter(retrofitAdapter);

    }
}
