package com.example.applogin.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.applogin.R
import com.example.applogin.view.fragment.TwitterFragment
import com.example.applogin.view.fragment.ZaloFragment
import com.twitter.sdk.android.core.DefaultLogger
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.TwitterConfig


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val navHostFragment = supportFragmentManager.fragments.first()
        if (navHostFragment != null) {
            val childFragments = navHostFragment.childFragmentManager.fragments
            childFragments.forEach { fragment ->
                if ( fragment is ZaloFragment) {
                    fragment.onActivityResult(requestCode, resultCode, data)
                }
                if (fragment is TwitterFragment){
                    fragment.onActivityResult(requestCode, resultCode, data)
                }
            }
        }


    }
}
