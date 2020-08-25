package com.example.androidefficientadvanced;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.androidefficientadvanced.chap1.LayoutInflaterHook;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            LayoutInflaterHook.hookLayoutInflater();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}