package com.example.androidadvance.annotation;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author caoshen
 * @date 2020/11/30
 */
@RequiresApi(api = Build.VERSION_CODES.N)
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
@Inherited
public @interface ATests {
    ATest[] value();
}
