package io.github.caoshen.androidadvance.rxjava;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxLifecycleMainActivity extends AppCompatActivity {
    private static final String TAG = "RxLifecycleMainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxlifecycle);
        Log.d(TAG, "onCreate:");

        Observable.interval(2, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> Log.d(TAG, String.valueOf(aLong)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        2021-03-17 11:06:35.813 15683-15683/io.github.caoshen.androidadvance.rxjava D/RxLifecycleMainActivity: 100
//        2021-03-17 11:06:37.027 15683-15683/io.github.caoshen.androidadvance.rxjava D/RxLifecycleMainActivity: onDestroy
//        2021-03-17 11:06:37.044 15683-15683/io.github.caoshen.androidadvance.rxjava D/RxLifecycleMainActivity: 101
//        2021-03-17 11:06:37.825 15683-15683/io.github.caoshen.androidadvance.rxjava D/RxLifecycleMainActivity: 102
        Log.d(TAG, "onDestroy");
    }
}


