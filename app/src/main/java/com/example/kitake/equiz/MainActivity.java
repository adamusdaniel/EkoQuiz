package com.example.kitake.equiz;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

      DownloadManager downloadmanager;

        String dwnload_file_path = "http://v-ie.uek.krakow.pl/~s186201/Pytania.db";
       File database = new File("/storage/emulated/0/Android/data/com.example.kitake.equiz/files");
        if (!database.exists()){



                  downloadmanager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

        Uri uri = Uri.parse(dwnload_file_path);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

  request.setDestinationInExternalFilesDir(MainActivity.this, "/file"
          , "Pytania.db");
          downloadmanager.enqueue(request);
       }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void uruchom(View view) {
        Intent intent = new Intent (MainActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}
