package com.justtherest.agefinder;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Justin 2017
 */

/*
TODO: Add Validity Check and AutoProgress to Next Box (Maybe)!!!!
 */
public class MainActivity extends AppCompatActivity {

    private Button cButton;
    private EditText mNum;
    private EditText dNum;
    private EditText yNum;
    private TextView yearOld;
    private int uMonth;
    private int uDay;
    private int uYear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cButton = (Button) findViewById(R.id.calButton);
        mNum = (EditText) findViewById(R.id.mNum);
        dNum = (EditText) findViewById(R.id.dNum);
        yNum = (EditText) findViewById(R.id.yNum);
        yearOld= (TextView) findViewById(R.id.yearOldText);

        cButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxes();

            }
        });


        mNum.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    checkBoxes();
                    return true;
                }
                return false;
            }
        });

        dNum.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    checkBoxes();
                    return true;
                }
                return false;
            }
        });

        yNum.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    checkBoxes();
                    return true;
                }
                return false;
            }
        });


    }
    public int findAge(int sMonth, int sDay, int sYear){
        int age = sYear - getUYear();

        if (monthGreater(sMonth)) return age - 1;

        if (dayGreater(sDay)) return age - 1;


        return age;
    }

    Boolean monthGreater (int sMonth){
        if (sMonth >= getUMonth()) return false;
        return  true;

    }

    Boolean dayGreater (int sDay) {
        if (sDay >= getUDay()) return false;
        return true;

    }

    public void setUMonth() {
        uMonth = Integer.parseInt(mNum.getText().toString());
    }

    public void setUDay() {
        uDay = Integer.parseInt(dNum.getText().toString());
    }

    public void setUYear() {
        uYear = Integer.parseInt(yNum.getText().toString());
    }

    public int getUMonth() {
        return uMonth;
    }

    public int getUDay() {
        return uDay;
    }

    public int getUYear() {
        return uYear;
    }

    public void calculateAge() {
        int age;

        setUMonth();

        setUDay();

        setUYear();

        Calendar cal = Calendar.getInstance();

        SimpleDateFormat month = new SimpleDateFormat("MM");
        final int sysMonth = Integer.parseInt(month.format(cal.getTime()));

        SimpleDateFormat day = new SimpleDateFormat("dd");
        final int sysDay = Integer.parseInt(day.format(cal.getTime()));

        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        final int sysYear = Integer.parseInt(year.format(cal.getTime()));

        age = findAge(sysMonth,sysDay,sysYear);

        if (age < 0)
            Toast.makeText(this,"Sorry This App Does Not Work With People From " +
                    "The Future",Toast.LENGTH_SHORT).show();
        else {
            yearOld.setText(String.valueOf(age) + " Years Old");

            Toast.makeText(this,"Done",Toast.LENGTH_SHORT).show();
        }


    }

    public void checkBoxes() {

        if (mNum.getText().toString().trim().length() == 0 ||
                dNum.getText().toString().trim().length() == 0 ||
                yNum.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "Please Fill All Boxes",Toast.LENGTH_SHORT).show();
        }
        else calculateAge();
    }
}
