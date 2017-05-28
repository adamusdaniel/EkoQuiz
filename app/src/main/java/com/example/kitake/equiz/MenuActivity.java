package com.example.kitake.equiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


    }

    public void start(View view) {
        Intent intent = new Intent (MenuActivity.this, QuizActivity.class);
        startActivity(intent);
    }

    public void instrukcja(View view) {
        Intent intent = new Intent (MenuActivity.this, InstrukcjaActivity.class);
        startActivity(intent);
    }

    public void wyniki(View view) {
        Intent intent = new Intent (MenuActivity.this, Wyniki.class);
        startActivity(intent);
    }
}
