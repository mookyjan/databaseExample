package com.example.mudassirkhan.visaapplicationform.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mudassirkhan.visaapplicationform.Utils;
import com.example.mudassirkhan.visaapplicationform.VisaApplication;
import com.example.mudassirkhan.visaapplicationform.dao.CustomerTable;
import com.example.mudassirkhan.visaapplicationform.model.Customer;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by waqas on 1/10/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    /*version number to upgrade database version
    each time if you Add, Edit table, you need to change the
    version number. */
    private static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "visaAppDB.db";

    public DatabaseHelper() {
        super(VisaApplication.getAppContext(), Utils.getStorageDirectory()+DATABASE_NAME, null, DATABASE_VERSION);
        //if sd card path exist store db file on it otherwise use internal storage
        //String dbPath =  Utils.getStorageDirectory()+DATABASE_NAME;
        getWritableDatabase();
       // super(VisaApplication.getAppContext(),DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     //   db.execSQL(new SensorStatsTable().createTable());
       // db.execSQL(new DeviceParamsTable().createTable());
        db.execSQL(new CustomerTable().createTable());
       // db.execSQL(new SiteTable().createTable());
       // db.execSQL(new SensorDeviceTable().createTable());
        seedData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
      //  sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SensorStatsTable.TABLE_NAME);
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DeviceParamsTable.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CustomerTable.TABLE_NAME);
     //   sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SiteTable.TABLE_NAME);
      //  sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SensorDeviceTable.TABLE_NAME);

        onCreate(sqLiteDatabase);
    }

    private void seedData(SQLiteDatabase db) {
        insertCustomer(db);
      //  insertSites(db);
        //insertDevices(db);

    }


    public void insertCustomer(SQLiteDatabase db) {
        Customer customer = new Customer();
        customer.setCustomerFullName("HBL");
        ContentValues values = new ContentValues();
        values.put(CustomerTable.COLUMN_CUSTOMER_NAME, customer.getCustomerFullName());
        db.insert(CustomerTable.TABLE_NAME, null, values);
    }
    public void addCustomer(Customer customer){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(CustomerTable.COLUMN_CUSTOMER_NAME,customer.getCustomerFullName());
        contentValues.put(CustomerTable.COLUMN_GENDER,customer.getGender());
        contentValues.put(CustomerTable.COLUMN_DOB,customer.getDob());
        contentValues.put(CustomerTable.COLUMN_NATIONALITY,customer.getNationality());
        contentValues.put(CustomerTable.COLUMN_OCCUPATION,customer.getOccupation());
        contentValues.put(CustomerTable.COLUMN_ADDRESS,customer.getAddress());
        contentValues.put(CustomerTable.COLUMN_MARITAL_STATUS,customer.getMarital_status());
        contentValues.put(CustomerTable.COLUMN_TYPE_TRAVEL,customer.getType_travel());
        contentValues.put(CustomerTable.COLUMN_NUMBER,customer.getNumber());
        contentValues.put(CustomerTable.COLUMN_COUNTRY_ISSUE,customer.getCountryOfIssue());
        contentValues.put(CustomerTable.COLUMN_VALID_UNTIL,customer.getDateValidUntil());
        contentValues.put(CustomerTable.COLUMN_SPONSOR_NAME,customer.getSponsorFullName());
        contentValues.put(CustomerTable.COLUMN_NRIC,customer.getNRIC());
        contentValues.put(CustomerTable.COLUMN_TELEPHONE,customer.getTelephone());
        contentValues.put(CustomerTable.COLUMN_SPONSOR_ADDRESS,customer.getSponsorAddress());
        contentValues.put(CustomerTable.COLUMN_STATE,customer.getState());
        contentValues.put(CustomerTable.COLUMN_DURATION_STAY,customer.getDurationOfStay());
        contentValues.put(CustomerTable.COLUMN_PURPOSE_STAY,customer.getPurposeOfJourney());

        sqLiteDatabase.insert(CustomerTable.TABLE_NAME,null,contentValues);
        sqLiteDatabase.close();








    }

//    private void insertSites(SQLiteDatabase db) {
//        List<Site> siteList = new ArrayList<Site>();
//        Site site1 = new Site();
//        site1.setSiteName("Gulberg");
//        siteList.add(site1);
//
//        Site site2 = new Site();
//        site2.setSiteName("Liberty");
//        siteList.add(site2);
//
//        Site site3 = new Site();
//        site2.setSiteName("Model Town");
//        siteList.add(site3);
//
//        for (Site site :
//                siteList) {
//            ContentValues values = new ContentValues();
//            values.put(SiteTable.COLUMN_SITE_NAME, site.getSiteName());
//            db.insert(SiteTable.TABLE_NAME, null, values);
//        }
//    }

//    private void insertDevices(SQLiteDatabase db) {
//        List<SensorDevice> sensorDeviceList = new ArrayList<SensorDevice>();
//        SensorDevice sensorDevice1 = new SensorDevice();
//        sensorDevice1.setDeviceType("UPS");
//        sensorDeviceList.add(sensorDevice1);
//
//        SensorDevice sensorDevice2 = new SensorDevice();
//        sensorDevice1.setDeviceType("Gen");
//        sensorDeviceList.add(sensorDevice2);
//
//        SensorDevice sensorDevice3 = new SensorDevice();
//        sensorDevice1.setDeviceType("AC");
//        sensorDeviceList.add(sensorDevice3);
//
//        for (SensorDevice device :
//                sensorDeviceList) {
//            ContentValues values = new ContentValues();
//            values.put(SensorDeviceTable.COLUMN_DEVICE_TYPE, device.getDeviceType());
//            db.insert(SensorDeviceTable.TABLE_NAME, null, values);
//        }
//    }


}
