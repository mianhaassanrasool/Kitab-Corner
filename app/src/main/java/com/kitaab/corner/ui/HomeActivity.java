package com.kitaab.corner.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.kitaab.corner.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findViewById(R.id.btnPostAnAd).setOnClickListener(v -> startActivity(new Intent(this, SellBookActivity.class)));
        NavBar.bind(this);
    }
}
