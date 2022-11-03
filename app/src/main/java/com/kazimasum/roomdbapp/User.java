package com.kazimasum.roomdbapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey (autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String dob;


    String gender;
    String language;

    public User(int uid, String firstName, String dob,String gender,String language) {
        this.uid = uid;
        this.firstName = firstName;
        this.dob = dob;
        this.gender=gender;
        this.language=language;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return dob;
    }

    public void setLastName(String dob) {
        this.dob = dob;
    }

    public String getGender(){
        return gender;
    }
    public void setGender(String gender){
        this.gender=gender;
    }

    public String getLanguage(){
        return language;
    }
    public void setLanguage(String language){
        this.language=language;
    }


}
