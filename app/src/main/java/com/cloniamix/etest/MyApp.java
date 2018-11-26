package com.cloniamix.etest;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.util.Log;

import com.cloniamix.etest.dataBase.room.AppDatabase;

public class MyApp extends Application {

    private static MyApp instance;
    private AppDatabase database;

    public static MyApp getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        Log.d("APP", "onCreate: from myApp");
        instance = this;
        database = Room.databaseBuilder(this,AppDatabase.class, "QuestionList")
                .allowMainThreadQueries()
                .build();

        if (database.mQuestionDao().getAllQuestions().size() == 0) {

            /*ParseData.initDataWithGSON("TestList.json");*/
            //ParseData.initDataWithGSON("Group2List.json");
            ParseData.initDataWithGSON("Group3List.json");
            ParseData.initDataWithGSON("Group4List.json");
            //ParseData.initDataWithGSON("Group5List.json");
        }

        super.onCreate();
    }

    public AppDatabase getDatabase() {
        return database;
    }
}
