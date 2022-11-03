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
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
   EditText t1,t2,t3;
   TextView lbl,lb2;
   Button b1,b2;
   int year,month,day;


    RadioGroup radioGroup;
    RadioButton radioButton;

    CheckBox java,python,php,swift;
    String lang_kn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Calendar calendar=Calendar.getInstance();

        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);


        java=findViewById(R.id.java);
        python=findViewById(R.id.python);
        swift=findViewById(R.id.swift);
        php=findViewById(R.id.php);


        radioGroup = findViewById(R.id.radioGroup);



        b1=findViewById(R.id.b1);
        //b2=findViewById(R.id.selgen);

        lbl=findViewById(R.id.lbl);
        lb2=findViewById(R.id.iddgen);

        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year=calendar.get(Calendar.YEAR);
                month=calendar.get(Calendar.MONTH);
                day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                                t3.setText(dayOfMonth + "/" + (monthOfYear+1) + "/" + year);
                            }
                        },year,month,day);
                datePickerDialog.show();

            }
        });

        /*b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                if (selectedRadioButtonId != -1) {
                    selectedRadioButton = findViewById(selectedRadioButtonId);
                    String selectedRbText = selectedRadioButton.getText().toString();
                    lb2.setText(selectedRbText);
                } else {
                    lb2.setText("");
                }

            }
        });*/

         b1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {


                 int selectedId = radioGroup.getCheckedRadioButtonId();
                 // find the radiobutton by returned id
                 radioButton = (RadioButton) findViewById(selectedId);

                 AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                         AppDatabase.class, "room_db").allowMainThreadQueries().build();
                 UserDao userDao = db.userDao();
                 Boolean check=userDao.is_exist(Integer.parseInt(t1.getText().toString()));


                 lang_kn="";
                 if(java.isChecked()){
                     lang_kn+=" "+java.getText().toString();
                 }
                 if(python.isChecked()){
                     lang_kn+=" "+python.getText().toString();
                 }
                 if(swift.isChecked()){
                     lang_kn+=" "+swift.getText().toString();
                 }
                 if(php.isChecked()){
                     lang_kn+=" "+php.getText().toString();
                 }
                 Toast.makeText(getApplicationContext(),""+lang_kn,Toast.LENGTH_LONG).show();



                 if(check==false) {



                     userDao.insertrecord(new User(Integer.parseInt(t1.getText().toString()), t2.getText().toString(), t3.getText().toString()
                     ,radioButton.getText().toString(),lang_kn));
                     t1.setText("");
                     t2.setText("");
                     t3.setText("");
                     //lb2.setText("Select Gender:");
                     java.setChecked(false);
                     python.setChecked(false);
                     swift.setChecked(false);
                     php.setChecked(false);
                     radioGroup.clearCheck();
                     lbl.setText("Inserted Successfully");
                 }
                 else
                 {
                     t1.setText("");
                     t2.setText("");
                     t3.setText("");
                     lb2.setText("");
                     lbl.setText("Record is existing");
                 }
             }
         });

         

    }


}