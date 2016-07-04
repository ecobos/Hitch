package org.lbc.hitch.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import org.lbc.hitch.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class OfferRideActivity extends AppCompatActivity
{

    private static TextView mOffer_ride_date;
    private static TextView mOffer_ride_time;
    private static Calendar mCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_ride);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set default date to today's date
        mOffer_ride_date = (TextView) findViewById(R.id.ride_date);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("E, MMM dd yyyy", Locale.US);
//        String currentDate = dateFormat.format(new Date());
//        mOffer_ride_date.setText(currentDate);

        mOffer_ride_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");

            }
        });

        // Set default time to current time
        mOffer_ride_time = (TextView) findViewById(R.id.ride_time);
//        SimpleDateFormat sdfTime = new SimpleDateFormat("h:mm a z", Locale.US);
//        String currentTime = sdfTime.format(new Date());
//        mOffer_ride_time.setText(currentTime);
        mOffer_ride_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getSupportFragmentManager(), "timePicker");
            }
        });
    }

    /**
     * Date picker fragment is a dialog that allows the user to set the date of the ride
     */
    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            mCalendar = Calendar.getInstance();
            int year = mCalendar.get(Calendar.YEAR);
            int month = mCalendar.get(Calendar.MONTH);
            int day = mCalendar.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        /**
         * Sets the date in the textView and in Parse
         */
        public void onDateSet(DatePicker view, int year, int month, int day) {
            month = month++; // adjust for zero-based numbering
            String date = month + "/" + day + "/" + year;

            Calendar c = Calendar.getInstance();
            c.set(year, month, day);

            SimpleDateFormat sdf = new SimpleDateFormat("E, MMM dd, yyyy", Locale.US);
            String formattedDate = sdf.format(c.getTime());
            mOffer_ride_date.setText(formattedDate);
        }
    }

    /**
     * Time picker fragment is a dialog that allows the user to set the time of the ride
     */
    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            mCalendar = Calendar.getInstance();
            int hour = mCalendar.get(Calendar.HOUR_OF_DAY);
            int minute = mCalendar.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
        }

        /**
         * Sets the time in the textView and in Parse
         */
        public void onTimeSet(TimePicker view, int hour, int minute) {
            String time, period;
            if (hour > 12) {
                hour -= 12;
                period = "PM";
            } else {
                if (hour == 0 ){ hour = 12; }
                period = "AM";
            }
            time = hour + ":" + ((minute < 10)? "0" + minute:minute) + " " + period;
            mOffer_ride_time.setText(time);
            Log.d("time set", "onTimeSet: "+ hour + ":" + minute);
        }
    }

}


