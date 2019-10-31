package com.ut.gradecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "GradeCalculator";
    private static Button btnMin;
    private static Button btnMax;
    private static Button btnAvg;
    public  static ArrayList<Subject> mSubjectList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "This is verbose log for onCreate() of MainActivity class");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onClickButtonListener();
    }

    private void getGrade(){
        Log.v(TAG, "This is verbose log for getGrade() of MainActivity class");
        TableLayout tablelayout =(TableLayout)findViewById(R.id.tableLayout);
        int childParts = tablelayout.getChildCount();
        if (tablelayout != null) {
            for (int i = 0; i < childParts; i++) {
                View viewChild = tablelayout.getChildAt(i);
                if (viewChild instanceof TableRow) {
                    int rowChildParts = ((TableRow) viewChild).getChildCount();
                    for (int j = 0; j < rowChildParts; j++) {
                        View viewChild2 = ((TableRow) viewChild).getChildAt(j);
                        Subject subject = new Subject();
                        if (viewChild2 instanceof EditText) {
                            // get text from edit text
                            String text = ((EditText) viewChild2).getText().toString();
                            if(text.matches("")){
                                mSubjectList.get(i - 1).setGrade(0);
                            }
                            else{
                                mSubjectList.get(i - 1).setGrade(Integer.parseInt(text));
                            }
                        } else if (viewChild2 instanceof TextView) {
                            // get text from text view
                            String text = ((TextView) viewChild2).getText().toString();
                            if(!text.equals("Grade Calculator")&& !text.equals("MIN")
                                    && !text.equals("MAX") &&!text.equals("AVG")) {

                                subject.setId(i);
                                subject.setName(text);
                                mSubjectList.add(subject);
                            }

                        }
                    }
                }
            }
        }
    }

    private void onClickButtonListener() {
        btnMin = (Button)findViewById(R.id.min);
        btnMax = (Button)findViewById(R.id.max);
        btnAvg = (Button)findViewById(R.id.avg);

        btnMin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.v(TAG, "This is verbose log for Min button Clicked of MainActivity class");
                        getGrade();
                        String min= getMin();
                        Intent intent = new Intent("com.ut.gradecalculator.Report");
                        intent.putExtra("Min", min);
                        startActivity(intent);
                    }
                }

        );

        btnMax.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.v(TAG, "This is verbose log for Max button Clicked of MainActivity class");
                        getGrade();
                        String max= getMax();
                        Intent intent = new Intent("com.ut.gradecalculator.Report");
                        intent.putExtra("Max", max);
                        startActivity(intent);
                    }
                }

        );

        btnAvg.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.v(TAG, "This is verbose log for Avg button Clicked of MainActivity class");
                        getGrade();
                        String avg= getAvg();
                        Intent intent = new Intent("com.ut.gradecalculator.Report");
                        intent.putExtra("Avg", avg);
                        startActivity(intent);
                    }
                }

        );

    }

    private String getMin(){
        Log.v(TAG, "This is verbose log for getMin()of MainActivity class");
        Subject minGrade = Collections.min(mSubjectList, new SubComp());
        int minVal= minGrade.getGrade();
        String min = String.valueOf(minVal);
        return min;
    }

    private String getMax(){
        Log.v(TAG, "This is verbose log for getMax()of MainActivity class");
        Subject maxGrade = Collections.max(mSubjectList, new SubComp());
        int maxVal= maxGrade.getGrade();
        String max = String.valueOf(maxVal);
        return max;
    }

    private String getAvg(){
        Log.v(TAG, "This is verbose log for getAvg()of MainActivity class");
        String avg="";
        int sum =0, avrg=0;
        for (Subject sub : mSubjectList){
            int gradeSum = sub.getGrade();
            sum += gradeSum;
        }
        avrg= sum/4;
        avg = String.valueOf(avrg);
        return avg;
    }


}
