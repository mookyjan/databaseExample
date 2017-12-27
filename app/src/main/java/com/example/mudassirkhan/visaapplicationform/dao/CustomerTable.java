package com.example.mudassirkhan.visaapplicationform.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.mudassirkhan.visaapplicationform.Attributes;
import com.example.mudassirkhan.visaapplicationform.model.Customer;
import com.example.mudassirkhan.visaapplicationform.sqlite.DatabaseManager;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by waqas on 1/16/17.
 */

public class CustomerTable extends DBTable {
    public static final String TABLE_NAME = "customer";
    public static final String COLUMN_IMAGE="image";
    public static final String COLUMN_CUSTOMER_ID = "customer_id";
    public static final String COLUMN_TYPE_APPLICATION="type_application";
    public static final String COLUMN_CUSTOMER_NAME = "customer_name";
    public static final String COLUMN_GENDER="gender";
    public static final String COLUMN_COUNTRY_BIRTH="country_birth";
    public static final String COLUMN_DOB="dob";
    public static final String COLUMN_NATIONALITY="nationality";
    public static final String COLUMN_OCCUPATION="occupation";
    public static final String COLUMN_ADDRESS="address";
    public static final String COLUMN_MARITAL_STATUS="marital_status";
    public static final String COLUMN_TYPE_TRAVEL="type_travel";
    public static final String COLUMN_NUMBER="number";
    public static final String COLUMN_COUNTRY_ISSUE="country_issue";
    public static final String COLUMN_VALID_UNTIL="valid_until";
    public static final String COLUMN_SPONSOR_NAME="sponsor_name";
    public static final String COLUMN_NRIC="nric";
    public static final String COLUMN_TELEPHONE="telephone";
    public static final String COLUMN_SPONSOR_ADDRESS="sponsor_address";
    public static final String COLUMN_STATE="state";
    public static final String COLUMN_DURATION_STAY="duration_stay";
    public static final String COLUMN_PURPOSE_STAY="purpose_stay";
    public static final String COLUMN_CREATED_AT = "createdAt";
    public static final String COLUMN_UPDATED_AT = "updatedAt";

    @Override
    public void setTableName() {
        tableName = TABLE_NAME;
    }

    @Override
    public void setTableAttributes() {
        tableAttributes.add(new Attributes(COLUMN_CUSTOMER_ID,Attributes.Type.PRIMARY_KEY));
        tableAttributes.add(new Attributes(COLUMN_IMAGE,Attributes.Type.BLOB));
        tableAttributes.add(new Attributes(COLUMN_TYPE_APPLICATION, Attributes.Type.TEXT));
        tableAttributes.add(new Attributes(COLUMN_CUSTOMER_NAME,Attributes.Type.TEXT));
        tableAttributes.add(new Attributes(COLUMN_GENDER,Attributes.Type.TEXT));
        tableAttributes.add(new Attributes(COLUMN_COUNTRY_BIRTH,Attributes.Type.TEXT));
        tableAttributes.add(new Attributes(COLUMN_DOB, Attributes.Type.TEXT));
        tableAttributes.add(new Attributes(COLUMN_NATIONALITY, Attributes.Type.TEXT));
        tableAttributes.add(new Attributes(COLUMN_OCCUPATION, Attributes.Type.TEXT));
        tableAttributes.add(new Attributes(COLUMN_ADDRESS, Attributes.Type.TEXT));
        tableAttributes.add(new Attributes(COLUMN_MARITAL_STATUS, Attributes.Type.TEXT));
        tableAttributes.add(new Attributes(COLUMN_TYPE_TRAVEL, Attributes.Type.TEXT));
        tableAttributes.add(new Attributes(COLUMN_NUMBER, Attributes.Type.INTEGER));
        tableAttributes.add(new Attributes(COLUMN_COUNTRY_ISSUE, Attributes.Type.TEXT));
        tableAttributes.add(new Attributes(COLUMN_VALID_UNTIL, Attributes.Type.TEXT));
        tableAttributes.add(new Attributes(COLUMN_SPONSOR_NAME, Attributes.Type.TEXT));
        tableAttributes.add(new Attributes(COLUMN_NRIC, Attributes.Type.TEXT));
        tableAttributes.add(new Attributes(COLUMN_TELEPHONE, Attributes.Type.TEXT));
        tableAttributes.add(new Attributes(COLUMN_SPONSOR_ADDRESS, Attributes.Type.TEXT));
        tableAttributes.add(new Attributes(COLUMN_STATE, Attributes.Type.TEXT));
        tableAttributes.add(new Attributes(COLUMN_DURATION_STAY, Attributes.Type.TEXT));
        tableAttributes.add(new Attributes(COLUMN_PURPOSE_STAY, Attributes.Type.TEXT));
        tableAttributes.add(new Attributes(COLUMN_CREATED_AT,Attributes.Type.TEXT));
        tableAttributes.add(new Attributes(COLUMN_UPDATED_AT,Attributes.Type.TEXT));
    }

    public Customer findCustomerById(int id) {
        Customer customer = new Customer();
        String[] columns = {COLUMN_CUSTOMER_ID,COLUMN_IMAGE, COLUMN_TYPE_APPLICATION,COLUMN_CUSTOMER_NAME,COLUMN_GENDER,COLUMN_COUNTRY_BIRTH,COLUMN_DOB,COLUMN_NATIONALITY,
        COLUMN_OCCUPATION,COLUMN_ADDRESS,COLUMN_MARITAL_STATUS,COLUMN_TYPE_TRAVEL,COLUMN_NUMBER,COLUMN_COUNTRY_ISSUE,COLUMN_VALID_UNTIL,
        COLUMN_SPONSOR_NAME,COLUMN_NRIC,COLUMN_TELEPHONE,COLUMN_SPONSOR_ADDRESS,COLUMN_STATE,COLUMN_DURATION_STAY,COLUMN_PURPOSE_STAY};
        Cursor res = DatabaseManager.getInstance().openDatabase().query(tableName, columns, COLUMN_CUSTOMER_ID + "=?", new String[]{"" + id}, null, null, null);

        while (res.moveToNext()) {
            customer.setCustomerId(res.getInt(res.getColumnIndex(COLUMN_CUSTOMER_ID)));
            customer.setCustomerFullName(res.getString(res.getColumnIndex(COLUMN_CUSTOMER_NAME)));
        }
        res.close();
        DatabaseManager.getInstance().closeDatabase();
        return customer;
    }
    public long insertCustomer(Customer customer) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_IMAGE,customer.getImage());
        values.put(COLUMN_TYPE_APPLICATION,customer.getType_application());
        values.put(COLUMN_CUSTOMER_NAME, customer.getCustomerFullName());
        values.put(COLUMN_GENDER,customer.getGender());
        values.put(COLUMN_COUNTRY_BIRTH,customer.getCountryBirth());
        values.put(COLUMN_DOB,customer.getDob());
        values.put(COLUMN_NATIONALITY,customer.getNationality());
        values.put(COLUMN_OCCUPATION,customer.getOccupation());
        values.put(COLUMN_ADDRESS,customer.getAddress());
        values.put(COLUMN_MARITAL_STATUS,customer.getMarital_status());
        values.put(COLUMN_TYPE_TRAVEL,customer.getType_travel());
        values.put(COLUMN_NUMBER,customer.getNumber());
        values.put(COLUMN_COUNTRY_ISSUE,customer.getCountryOfIssue());
        values.put(COLUMN_VALID_UNTIL,customer.getDateValidUntil());
        values.put(COLUMN_SPONSOR_NAME,customer.getSponsorFullName());
        values.put(COLUMN_NRIC,customer.getNRIC());
        values.put(COLUMN_TELEPHONE,customer.getTelephone());
        values.put(COLUMN_SPONSOR_ADDRESS,customer.getSponsorAddress());
        values.put(COLUMN_STATE,customer.getState());
        values.put(COLUMN_DURATION_STAY,customer.getDurationOfStay());
        values.put(COLUMN_PURPOSE_STAY,customer.getPurposeOfJourney());
        return insertData(values);
    }
    /**
     * Get All Customer Data
     */
    public List<Customer> getAllCustomer(){
        Customer customer=new Customer();
        List<Customer> customerList=new ArrayList<>();
        String selectQuery="SELECT * FROM "+TABLE_NAME;
       Cursor cursor=DatabaseManager.getInstance().openDatabase().rawQuery(selectQuery,null);
        while (cursor.moveToNext()){
            customer.setCustomerId(cursor.getInt(cursor.getColumnIndex(COLUMN_CUSTOMER_ID)));
            customer.setImage(cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE)));
            customer.setType_application(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE_APPLICATION)));
            customer.setCustomerFullName(cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_NAME)));
            customer.setGender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)));
            customer.setCountryBirth(cursor.getString(cursor.getColumnIndex(COLUMN_COUNTRY_BIRTH)));
            customer.setDob(cursor.getString(cursor.getColumnIndex(COLUMN_DOB)));
            customer.setNationality(cursor.getString(cursor.getColumnIndex(COLUMN_NATIONALITY)));
            customer.setOccupation(cursor.getString(cursor.getColumnIndex(COLUMN_OCCUPATION)));
            customer.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
            customer.setMarital_status(cursor.getString(cursor.getColumnIndex(COLUMN_MARITAL_STATUS)));
            customer.setType_travel(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE_TRAVEL)));
            customer.setNumber(cursor.getString(cursor.getColumnIndex(COLUMN_NUMBER)));
            customer.setCountryOfIssue(cursor.getString(cursor.getColumnIndex(COLUMN_COUNTRY_ISSUE)));
            customer.setDateValidUntil(cursor.getString(cursor.getColumnIndex(COLUMN_VALID_UNTIL)));
            customer.setSponsorFullName(cursor.getString(cursor.getColumnIndex(COLUMN_SPONSOR_NAME)));
            customer.setNRIC(cursor.getString(cursor.getColumnIndex(COLUMN_NRIC)));
            customer.setTelephone(cursor.getString(cursor.getColumnIndex(COLUMN_TELEPHONE)));
            customer.setSponsorAddress(cursor.getString(cursor.getColumnIndex(COLUMN_SPONSOR_ADDRESS)));
            customer.setState(cursor.getString(cursor.getColumnIndex(COLUMN_STATE)));
            customer.setDurationOfStay(cursor.getString(cursor.getColumnIndex(COLUMN_DURATION_STAY)));
            customer.setPurposeOfJourney(cursor.getString(cursor.getColumnIndex(COLUMN_PURPOSE_STAY)));

            customerList.add(customer);
        }
        cursor.close();
        DatabaseManager.getInstance().closeDatabase();
        return customerList;
    }

}
