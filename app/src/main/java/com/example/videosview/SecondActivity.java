package com.example.videosview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import java.util.List;
import model.Videos;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import web.CustomAdapter;
import web.GetDataService;
import web.GetItemListDeserializer;
import web.RetrofitCreateInstance;

public class SecondActivity extends AppCompatActivity {
    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GetDataService service = RetrofitCreateInstance.getRetrofitInstance(new TypeToken<List<Videos>>() {}.getType(), new GetItemListDeserializer()).create(GetDataService.class);
        //create object call's type
        Call<List<Videos>> call = service.getAllMovies();
        call.enqueue(new Callback<List<Videos>>() {
            @Override
            public void onResponse(Call<List<Videos>> call, Response<List<Videos>> response) {
                generatDataList(response.body());
                Log.d("OnResponse", response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Videos>> call, Throwable t) {

                Log.d("oNSomethingWrong" , "Something wrong" + t.getMessage());
                Toast.makeText(SecondActivity.this, "Something wrong with response",Toast.LENGTH_SHORT).show();
            }
        });
    }
//Method for generation List of data using RecyclerView with custom adapter
    private void generatDataList(List<Videos> videosList){
        recyclerView = findViewById(R.id.recycler);
        adapter = new CustomAdapter(this, videosList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SecondActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
