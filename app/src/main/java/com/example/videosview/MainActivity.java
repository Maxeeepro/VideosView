package com.example.videosview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
Button btnStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        btnStart = (Button)findViewById(R.id.button2);
        btnStart.setOnClickListener(this);

    }
//On click method for starting Main Activity
    @Override
    public void onClick(View v) {
        Log.i("OnClick", "onClick");
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

}