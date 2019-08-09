package com.example.applogin.view.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.applogin.R
import java.security.NoSuchAlgorithmException
import android.util.Base64
import android.util.Log
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.applogin.view.fragment.ZaloFragment
import com.zing.zalo.zalosdk.oauth.ZaloOpenAPICallback
import com.zing.zalo.zalosdk.oauth.ZaloSDK
import org.json.JSONObject
import java.security.MessageDigest


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
            }
        }


    }
}
