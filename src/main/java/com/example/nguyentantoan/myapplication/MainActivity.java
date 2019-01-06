package com.example.nguyentantoan.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int so=5;
        String hoten = "Nguyễn Tấn Toàn";
        int nam = 2018;
        if (so>10){
        Log.d("hahaha", hoten + "-" + nam);}
        else {Log.d("huhuhu", hoten + "+" + nam);}
    }
}
