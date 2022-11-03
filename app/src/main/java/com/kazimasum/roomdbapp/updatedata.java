package com.kazimasum.roomdbapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Calendar;

public class updatedata extends AppCompatActivity
{
  int uid;
  EditText fname, lname;
  TextView gen,l1;
  Button btn,btn2;

  RadioGroup radioGroup1;
  RadioButton radioButton1;


    CheckBox java1,python1,php1,swift1;
    String lang_kn;


    int year,month,day;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedata);

        fname=findViewById(R.id.editfname);
        lname=findViewById(R.id.editlname);
        gen=findViewById(R.id.editgen);
        java1=findViewById(R.id.java1);
        python1=findViewById(R.id.python1);
        swift1=findViewById(R.id.swift1);
        php1=findViewById(R.id.php1);

        l1=findViewById(R.id.lang12);

        radioGroup1 = findViewById(R.id.radioGroup);
        final Calendar calendar=Calendar.getInstance();

        btn=findViewById(R.id.btn);
        //btn2=findViewById(R.id.btnselg);

        lname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year=calendar.get(Calendar.YEAR);
                month=calendar.get(Calendar.MONTH);
                day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(updatedata.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                                lname.setText(dayOfMonth + "/" + (monthOfYear+1) + "/" + year);
                            }
                        },year,month,day);
                datePickerDialog.show();

            }
        });



       /* btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedRadioButtonId = radioGroup1.getCheckedRadioButtonId();
                if (selectedRadioButtonId != -1) {
                    selectedRadioButton1 = findViewById(selectedRadioButtonId);
                    String selectedRbText = selectedRadioButton1.getText().toString();
                    gen.setText(selectedRbText);
                } else {
                    gen.setText("");
                }
            }
        });*/





        uid=Integer.parseInt(getIntent().getStringExtra("uid"));
        fname.setText(getIntent().getStringExtra("ufname"));
        lname.setText(getIntent().getStringExtra("ulname"));
        l1.setText(getIntent().getStringExtra("ulang"));
        gen.setText(getIntent().getStringExtra("ugen"));
        //java1.setText(getIntent().getStringExtra("ujava"));
        //python1.setText(getIntent().getStringExtra("upython"));
        //swift1.setText(getIntent().getStringExtra("uswift"));
        //php1.setText(getIntent().getStringExtra("uphp"));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                lang_kn=" ";


                if(java1.isChecked()){
                    lang_kn+=" "+java1.getText().toString();
                }
                if(python1.isChecked()){
                    lang_kn+=" "+python1.getText().toString();
                }
                if(swift1.isChecked()){
                    lang_kn+=" "+swift1.getText().toString();
                }
                if(php1.isChecked()){
                    lang_kn+=" "+php1.getText().toString();
                }
                l1.setText(lang_kn);


                int selectedId = radioGroup1.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioButton1 = (RadioButton) findViewById(selectedId);



                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "room_db").allowMainThreadQueries().build();
                UserDao userDao = db.userDao();
                userDao.updateById(uid,fname.getText().toString(),lname.getText().toString(),radioButton1.getText().toString(),
                        l1.getText().toString());
                startActivity(new Intent(getApplicationContext(),fetchdata.class));
                finish();
            }
        });
    }




}