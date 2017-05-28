package com.example.kitake.equiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class Wynik extends AppCompatActivity {

DbHelper baza;

    protected void onCreate(Bundle savedInstanceState) {
        baza = new DbHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wynik);

        RatingBar bar=(RatingBar)findViewById(R.id.ratingBar1);
        bar.setNumStars(5);
        bar.setStepSize(0.5f);

        TextView n=(TextView)findViewById(R.id.textresult2);
        TextView t=(TextView)findViewById(R.id.textResult);

        Bundle b = getIntent().getExtras();
        int score= b.getInt("score");
        AddData(score);


        n.setText("Zdobyłeś "+score+" na 50 punktów" );
        bar.setRating(score);
        if(score<=10)
        {
                bar.setRating(1);
                t.setText("Ups, przyłóż się do nauki!");}

        if(score>10 && score<=20){
                bar.setRating(2);
                t.setText("Nie jest źle ale mogło być lepiej.");}

        if(score>20 && score<=30 ){
                t.setText("Całkiem nieźle!");
                bar.setRating(3);}
        if(score>30 && score<=40){
                bar.setRating(4);
                t.setText("Dobry wynik!");}
        if(score>40){
                bar.setRating(5);
                t.setText("Gratulacje, świetny wynik!");}

        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.activity_quiz, menu);
        return true;
    }

    public void menu(View view) {
        Intent intent = new Intent(Wynik.this, MenuActivity.class);
        startActivity(intent);
    }

    public void AddData(int newEntry) {

        boolean insertData = baza.addData(newEntry);


    }
    }
