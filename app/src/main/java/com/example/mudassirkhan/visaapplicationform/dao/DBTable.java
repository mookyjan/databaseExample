package com.example.mudassirkhan.visaapplicationform.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.mudassirkhan.visaapplicationform.Attributes;
import com.example.mudassirkhan.visaapplicationform.sqlite.DatabaseManager;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by waqas on 1/10/17.
 */

public abstract class DBTable {
    public String tableName;
    List<Attributes> tableAttributes;

    public DBTable(){
        tableName = "";
        tableAttributes = new ArrayList<Attributes>();
        setTableName();
        setTableAttributes();
    }
    public abstract void setTableName();
    public abstract void setTableAttributes();

    public String createTable() {
        String querry = "CREATE TABLE " + tableName + "(";
        for (Attributes attributes :
                tableAttributes) {
            if (attributes.getColumnType().equals(Attributes.Type.PRIMARY_KEY)) {
                querry = querry + attributes.getColumnName() + "   INTEGER PRIMARY KEY AUTOINCREMENT    ";
            } else {
                querry = querry + " , " + attributes.getColumnName() + " " + attributes.getColumnType();
            }
        }
        querry = querry + ")";
        return querry;
    }


    public Cursor getAllData() {
        String querry = "SELECT * FROM " + tableName;
        Cursor cursor = DatabaseManager.getInstance().openDatabase().rawQuery(querry, null);
        return cursor;
    }

    public long insertData(ContentValues values) {
        long id = DatabaseManager.getInstance().openDatabase().insert(tableName, null, values);
        DatabaseManager.getInstance().closeDatabase();
        return id;
    }

    public void deleteAllData() {
        DatabaseManager.getInstance().openDatabase().execSQL("delete from " + tableName);
        DatabaseManager.getInstance().closeDatabase();
    }

}
