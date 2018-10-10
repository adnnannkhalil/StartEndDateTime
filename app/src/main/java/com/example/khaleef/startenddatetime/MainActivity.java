package com.example.khaleef.startenddatetime;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
@SuppressLint("SetTextI18n")
public class MainActivity extends AppCompatActivity {

    public EditText startDateET, endDateET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startDateET = findViewById(R.id.et_startDate);
        endDateET = findViewById(R.id.et_endDate);

        startDateET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });
        /*endDateET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });*/
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void setDateEdit(int day, int month, int year) {
        startDateET.setText(day + "/" + (month + 1) + "/" + year);

        DialogFragment newFragment = DatePickerFragment.newInstance(year, month, day);
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void setEndDateEdit(int day, int month, int year) {
        endDateET.setText(day + "/" + (month + 1) + "/" + year);
        //showTimePickerDialog();
    }

    public void showTimePickerDialog() {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void setTimeEdit(int hourOfDay, int minute) {
        startDateET.setText(startDateET.getText() + " -" + hourOfDay + ":"	+ minute);
    }
}


