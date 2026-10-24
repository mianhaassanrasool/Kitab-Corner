package com.kitaab.corner.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.kitaab.corner.R;

public class MakeOfferActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_offer);
        findViewById(R.id.btnSendOffer).setOnClickListener(v -> startActivity(new Intent(this, ChatActivity.class)));
    }
}
