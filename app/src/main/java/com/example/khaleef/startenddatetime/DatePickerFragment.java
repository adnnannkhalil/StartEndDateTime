package com.example.khaleef.startenddatetime;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

import java.util.Objects;

public class DatePickerFragment extends DialogFragment implements
        DatePickerDialog.OnDateSetListener {

    private int year = 0, month = 0, day = 0;
    private boolean isEndDate = false;

    private static final String TAG_YEAR = "year";
    private static final String TAG_MONTH = "month";
    private static final String TAG_DAY = "day";

    public static DatePickerFragment newInstance(int year, int month, int day) {
        Bundle args = new Bundle();

        args.putInt(TAG_YEAR, year);
        args.putInt(TAG_MONTH, month);
        args.putInt(TAG_DAY, day);

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            day = getArguments().getInt(TAG_DAY);
            month = getArguments().getInt(TAG_MONTH);
            year = getArguments().getInt(TAG_YEAR);
            isEndDate = true;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker

        Calendar calendar = Calendar.getInstance();
        if (!isEndDate) {
            calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(Objects.requireNonNull(getActivity()), this, year, month, day);

        } else {
            calendar = Calendar.getInstance();
            calendar.set(year, month, day);

            DatePickerDialog pickerDialog = new DatePickerDialog(Objects.requireNonNull(getActivity()), this, year, month, day);
            pickerDialog.getDatePicker().setMinDate(calendar.getTime().getTime());
            return pickerDialog;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        if (isEndDate)
            ((MainActivity)Objects.requireNonNull(getActivity())).setEndDateEdit(day, month, year);
        else
            ((MainActivity)Objects.requireNonNull(getActivity())).setDateEdit(day, month, year);
    }
}