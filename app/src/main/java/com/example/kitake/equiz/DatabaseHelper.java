package com.example.kitake.equiz;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kitałke on 2017-05-20.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String FILE_DIR= "/storage/emulated/0/Android/data/com.example.kitake.equiz/files/file";
    private static final String DATABASE_NAME = "Pytania.db";
    private static final String TABLE_QUEST = "Tabela";

    private SQLiteDatabase dbase;
    public DatabaseHelper(final Context context) {

        super(context, FILE_DIR + File.separator + DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {

    }
    public List<Pytania> getAllQuestions() {
        List<Pytania> quesList = new ArrayList<Pytania>();
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Pytania quest = new Pytania();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        return quesList;
    }




}