package com.example.mudassirkhan.visaapplicationform.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mudassirkhan.visaapplicationform.dao.CustomerTable;
import com.example.mudassirkhan.visaapplicationform.model.Customer;

import java.util.ArrayList;



/**
 * Created by waqas on 1/10/17.
 */

public class DatabaseManager {
    private static DatabaseManager databaseManager;
    private static SQLiteOpenHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    private DatabaseManager() {}

    public static synchronized DatabaseManager getInstance() {
        if (databaseManager == null) {
            databaseManager = new DatabaseManager();
            databaseHelper = new DatabaseHelper();
        }
        return databaseManager;
    }

    public synchronized SQLiteDatabase openDatabase() {
        // Opening new database
        if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
            sqLiteDatabase = databaseHelper.getWritableDatabase();
        }
        return sqLiteDatabase;
    }

    public synchronized void closeDatabase() {
        // Closing database
        sqLiteDatabase.close();
    }

//    public ArrayList<SensorDeviceStats> getAllSensorStats () {
//        SensorStatsTable sensorStatsTable = new SensorStatsTable();
//         return sensorStatsTable.getAllSensorList();
//    }
//
//    public long insertSensorStats(SensorDeviceStats sensorStats) {
//        SensorStatsTable sensorStatsTable = new SensorStatsTable();
//        return sensorStatsTable.insertSensor(sensorStats);
//    }
//
//    public void deleteAllSensorStats() {
//        SensorStatsTable sensorStatsTable = new SensorStatsTable();
//        sensorStatsTable.deleteAllData();
//    }

    public Customer getCustomerByID(int id ) {
        CustomerTable customerTable = new CustomerTable();
        Customer customer = customerTable.findCustomerById(id);
        return customer;
    }
    public long insertCustomerData(Customer customerData){
        CustomerTable customerTable=new CustomerTable();
        return customerTable.insertCustomer(customerData);
    }

//    public Site getSiteByID(int id ) {
//        SiteTable siteTable = new SiteTable();
//        Site site = siteTable.findSiteById(id);
//        return site;
//    }
//
//    public SensorDevice getSensorDeviceByID(int id ) {
//        SensorDeviceTable sensorDeviceTable = new SensorDeviceTable();
//        SensorDevice device = sensorDeviceTable.findDeviceById(id);
//        return device;
//    }

}
