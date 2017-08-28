package com.justtherest.agefinder;

//import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Button cButton;
    private EditText mNum;
    private EditText dNum;
    private EditText yNum;
    private TextView yearOld;

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
                int age;
                int userMonth = Integer.parseInt(mNum.getText().toString());
                int userDay = Integer.parseInt((dNum.getText().toString()));
                int userYear = Integer.parseInt(yNum.getText().toString());

                Calendar cal = Calendar.getInstance();

                SimpleDateFormat month = new SimpleDateFormat("MM");
                final int sysMonth = Integer.parseInt(month.format(cal.getTime()));

                SimpleDateFormat day = new SimpleDateFormat("DD");
                final int sysDay = Integer.parseInt(day.format(cal.getTime()));

                SimpleDateFormat year = new SimpleDateFormat("YYYY");
                final int sysYear = Integer.parseInt(year.format(cal.getTime()));

                age = findAge(userMonth, userDay, userYear, sysMonth,sysDay,sysYear);

                yearOld.setText(String.valueOf(age) + " Years Old");










            }
        });











    }
    int findAge(int uMonth, int uDay, int uYear,
                int sMonth, int sDay, int sYear){
        int age = sYear - uYear;
        if (monthGreater(sMonth, uMonth)) return age - 1;
        if (dayGreater(sDay, uDay)) return age - 1;




        return age;
    }

    Boolean monthGreater (int sMonth, int uMonth){
        if (sMonth >= uMonth) return false;
        return  true;

    }

    Boolean dayGreater (int sDay, int uDay) {
        if (sDay >= uDay) return false;
        return true;

    }
}
