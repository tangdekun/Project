package com.tdk.componentlesson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tdk.method_annotations.MethodProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @MethodProvider
    fun testMethod() {

    }

    @MethodProvider
    fun testMethod1() {

    }
}