package com.example.kitake.equiz;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class Wyniki extends AppCompatActivity {
    DbHelper baza;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wyniki);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Wyniki");
        setSupportActionBar(toolbar);
       ListView lista = (ListView)findViewById(R.id.listview);
        baza = new DbHelper(this);


        ArrayList<Integer> theList = new ArrayList<>();
        Cursor data = baza.getListContents();
        if(data.getCount() == 0){
            Toast.makeText(this, "W tym momencie lista wyników jest pusta! Spróbuj zagrać!",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){

                theList.add(data.getInt(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                lista.setAdapter(listAdapter);
                Collections.sort(theList, Collections.reverseOrder());
            }
        }



    }}
