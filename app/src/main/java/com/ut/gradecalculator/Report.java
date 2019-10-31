package com.ut.gradecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TextView;

public class Report extends AppCompatActivity {
    private static String TAG = "GradeCalculator";
    private Bundle bd;
    private TextView ios,android,swift,java;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "This is verbose log for onCreate()of Report class");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        setGrade();
    }

    private void setGrade() {
        Log.v(TAG, "This is verbose log for setGrade() of Report class");
        ios =(TextView)findViewById(R.id.iosGrade);
        int iosG = (MainActivity.mSubjectList.get(0).getGrade());
        ios.setText(String.valueOf(iosG));
        android = (TextView)findViewById(R.id.androidGrade);
        int androidG = (MainActivity.mSubjectList.get(1).getGrade());
        android.setText(String.valueOf(androidG));
        swift = (TextView)findViewById(R.id.swiftGrade);
        int swiftG = (MainActivity.mSubjectList.get(2).getGrade());
        swift.setText(String.valueOf(swiftG));
        java = (TextView)findViewById(R.id.javaGrade);
        int javaG = (MainActivity.mSubjectList.get(3).getGrade());
        java.setText(String.valueOf(javaG));

        TextView minMaxAvg = (TextView)findViewById(R.id.maxMinAvg);
        TextView minMaxAvgVal = (TextView)findViewById(R.id.maxMinAvgVal);
        Intent intent = getIntent();
        String value ="";
        if (intent.hasExtra("Min")) {
            value = intent.getExtras().getString("Min");
            minMaxAvg.setText("MIN");
        }
        if (intent.hasExtra("Max")) {
            value = intent.getExtras().getString("Max");
            minMaxAvg.setText("MAX");
        }
        if(intent.hasExtra("Avg")) {
            value = intent.getExtras().getString("Avg");
            minMaxAvg.setText("AVG");
        }
        minMaxAvgVal.setText(value);
    }

}
