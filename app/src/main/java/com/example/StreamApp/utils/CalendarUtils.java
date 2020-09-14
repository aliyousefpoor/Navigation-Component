package com.example.StreamApp.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.example.StreamApp.R;
import com.example.StreamApp.data.datasource.local.database.DateListener;

import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;

public class CalendarUtils {
    private static final String TAG = "CalendarUtils";
    private PersianDatePickerDialog picker;
    private DateListener dateListener;
    private Context context;

    public CalendarUtils (Context context, DateListener dateListener){
        this.context=context;
        this.dateListener = dateListener;
    }
    
    @SuppressLint("ResourceAsColor")
    public void showCalendar(){
        PersianCalendar initDate = new PersianCalendar();
        initDate.setPersianDate(1370, 3, 13);
        picker = new PersianDatePickerDialog(context)
                .setPositiveButtonString("باشه")
                .setNegativeButton("بیخیال")
                .setTodayButton("امروز")
                .setTodayButtonVisible(true)
                .setMinYear(1300)
                .setMaxYear(1420)
                .setActionTextColor(R.color.white)
                .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
                .setActionTextColor(Color.GRAY)
                .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
                .setShowInBottomSheet(true)
                .setListener(new Listener() {
                    @Override
                    public void onDateSelected(PersianCalendar persianCalendar) {
                        Log.d(TAG, "onDateSelected: " + persianCalendar.getGregorianChange());//Fri Oct 15 03:25:44 GMT+04:30 1582
                        Log.d(TAG, "onDateSelected: " + persianCalendar.getTimeInMillis());//1583253636577
                        Log.d(TAG, "onDateSelected: " + persianCalendar.getTime());//Tue Mar 03 20:10:36 GMT+03:30 2020
                        Log.d(TAG, "onDateSelected: " + persianCalendar.getDelimiter());//  /
                        Log.d(TAG, "onDateSelected: " + persianCalendar.getPersianLongDate());// سه‌شنبه  13  اسفند  1398
//                        Log.d(TAG, "onDateSelected: " + persianCalendar.getPersianLongDateAndTime()); //سه‌شنبه  13  اسفند  1398 ساعت 20:10:36
//                        Log.d(TAG, "onDateSelected: " + persianCalendar.getPersianMonthName()); //اسفند
                        Log.d(TAG, "onDateSelected: " + persianCalendar.isPersianLeapYear());//false
                        String persianDate = persianCalendar.getPersianShortDate().replaceAll("/", "-");
                        dateListener.onDateChange(persianDate);
                    }

                    @Override
                    public void onDismissed() {

                    }
                });

        picker.show();
    }
}
