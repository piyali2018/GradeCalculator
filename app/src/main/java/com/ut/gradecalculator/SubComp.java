package com.ut.gradecalculator;

import android.util.Log;

import java.util.Comparator;

public class SubComp implements Comparator<Subject> {
    private final static String TAG = "GradeCalculator";
    @Override
    public int compare(Subject o1, Subject o2) {
        Log.v(TAG, "This is verbose log for compare() of SubComp class");
        return Integer.valueOf(o1.getGrade()).compareTo(Integer.valueOf(o2.getGrade()));
    }
}
