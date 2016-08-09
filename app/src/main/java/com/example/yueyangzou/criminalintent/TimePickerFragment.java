package com.example.yueyangzou.criminalintent;

import android.app.Activity;
import android.app.Dialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.TimePicker;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Created by yueyangzou on 16/8/9.
 */
public class TimePickerFragment extends DatePickerFragment {
    public static final String EXTRA_TIME = "com.example.yueyangzou.criminalintent.time";
    private static final String ARG_TIME = "time";
    private TimePicker mTimePicker;


    public static  TimePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TIME
                , date);

        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }



    public Dialog onCreateDialog(Bundle saveInstanceState) {
        Date date = (Date) getArguments().getSerializable(ARG_TIME);


        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);


        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);


        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_time, null);
        mTimePicker = (TimePicker) v.findViewById(R.id.dialog_time_time_picker);

        mTimePicker.setHour(hour);
        mTimePicker.setMinute(minute);

        return new AlertDialog.Builder(getActivity()).setView(v).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int hour1 = mTimePicker.getHour();
                int minute1 = mTimePicker.getMinute();

                Date date = new GregorianCalendar(0,0,0,hour1, minute1).getTime();
                sendResult(Activity.RESULT_OK, date);

            }
        }).create();
    }


    private void sendResult(int resultCode, Date date) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TIME, date);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);

    }
}
