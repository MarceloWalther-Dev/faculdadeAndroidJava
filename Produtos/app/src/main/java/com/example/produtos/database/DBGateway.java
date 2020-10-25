package com.example.produtos.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBGateway {

    private static DBGateway dbGatewey;
    private SQLiteDatabase db;

    public static DBGateway getInstance(Context context){
        if(dbGatewey == null){
            dbGatewey = new DBGateway(context);
        }
        return dbGatewey;
    }

    private DBGateway(Context context){
        DatabaseDBHelper dbhelper = new DatabaseDBHelper(context);
        db = dbhelper.getWritableDatabase();
    }

    public SQLiteDatabase getDatabase(){
        return db;
    }
}
