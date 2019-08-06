package com.example.applogin.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.applogin.R
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

    }
}
