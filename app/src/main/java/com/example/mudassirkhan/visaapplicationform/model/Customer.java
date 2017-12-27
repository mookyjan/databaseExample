package com.example.mudassirkhan.visaapplicationform.model;

import java.io.Serializable;

/**
 * Created by waqas on 1/16/17.
 */

public class Customer implements Serializable {
    byte[] image;
    int customerId;
    String type_application,customerFullName,gender,countryName,countryBirth,dob,nationality,occupation,address,marital_status,type_travel,countryOfIssue,
    dateValidUntil,sponsorFullName,nric,telephone,sponsorAddress,state,durationOfStay,purposeOfJourney;
    String  number;
    public String getType_application(){
        return type_application;
    }
    public void setType_application(String type_application){
        this.type_application=type_application;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public byte[] getImage(){
        return image;
    }
    public void setImage(byte[] imageByte){
        this.image=imageByte;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public void setCustomerFullName(String customerName) {
        customerFullName = customerName;
    }

    public void  setGender(String gender){
        this.gender=gender;
    }
    public String getGender(){
        return gender;
    }
    public void setCountryBirth(String countryBirth){
        this.countryBirth=countryBirth;
    }
    public String getCountryBirth(){
        return countryBirth;
    }
    public void setCountryName(String countryName){
        this.countryName=countryName;
    }
    public String getCountryName() {
        return countryName;
    }
    public void setDob(String dob){
        this.dob=dob;
    }
    public String getDob(){
        return dob;
    }
    public void setNationality(String nationality){
        this.nationality=nationality;
    }
    public String getNationality(){
        return nationality;
    }
    public void setOccupation(String occupation){
        this.occupation=occupation;
    }
    public String getOccupation(){
        return occupation;
    }
    public void setAddress(String address){
        this.address=address;
    }
    public String getAddress(){
        return address;
    }
    public void setMarital_status(String marital_status){
        this.marital_status=marital_status;
    }
    public String getMarital_status(){
        return marital_status;
    }
    public void setType_travel(String travel){
        this.type_travel=travel;
    }
    public String getType_travel(){
        return type_travel;
    }
    public void setNumber(String number){
        this.number=number;
    }
    public String  getNumber(){
        return number;
    }
    public void setCountryOfIssue(String countryOfIssue){
        this.countryOfIssue=countryOfIssue;
    }
    public String getCountryOfIssue(){
        return countryOfIssue;
    }
    public void setDateValidUntil(String dateValidUntil){
        this.dateValidUntil=dateValidUntil;
    }
    public String getDateValidUntil(){
        return dateValidUntil;
    }
    public void setSponsorFullName(String sponsorFullName){
        this.sponsorFullName=sponsorFullName;
    }
    public String getSponsorFullName(){
        return sponsorFullName;
    }
    public void setNRIC(String nric){
        this.nric=nric;
    }
    public String getNRIC(){
        return nric;
    }
    public void setTelephone(String telephone){
        this.telephone=telephone;
    }
    public String getTelephone(){
        return telephone;
    }
    public void setSponsorAddress(String sponsorAddress){
        this.sponsorAddress=sponsorAddress;
    }
    public String getSponsorAddress(){
        return sponsorAddress;
    }
    public void setState(String state){
        this.state=state;
    }
    public String getState(){
        return state;
    }
    public void setDurationOfStay(String durationOfStay){
        this.durationOfStay=durationOfStay;
    }
    public String getDurationOfStay(){
        return durationOfStay;
    }
    public void setPurposeOfJourney(String purposeOfJourney){
        this.purposeOfJourney=purposeOfJourney;
    }
    public String getPurposeOfJourney(){
        return purposeOfJourney;
    }



}
