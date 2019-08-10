package com.example.applogin.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.applogin.R
import com.example.applogin.callback.Message
import com.example.applogin.viewmodel.FragmentTwitterViewmodel
import com.twitter.sdk.android.core.*
import kotlinx.android.synthetic.main.fragment_twitter.*
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.TwitterSession
import com.twitter.sdk.android.core.models.User
import kotlinx.android.synthetic.main.fragment_linkedin.*
import retrofit2.Call

class TwitterFragment : Fragment() {

    var fragmentTwitterViewmodel : FragmentTwitterViewmodel = FragmentTwitterViewmodel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val config = TwitterConfig.Builder(context)
            .logger(DefaultLogger(Log.DEBUG))//enable logging when app is in debug mode
            .twitterAuthConfig(
                TwitterAuthConfig(
                    resources.getString(R.string.twitterconsumkey),
                    resources.getString(R.string.twitterconsumsecretkey)
                )
            )
            .debug(true)//enable debug mode
            .build()
        Twitter.initialize(config)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_twitter, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fragmentTwitterViewmodel = ViewModelProviders.of(this).get(FragmentTwitterViewmodel::class.java)

        fragmentTwitterViewmodel.data.observe(this , Observer {
            textUsernametwitter.text = it.username
            textScreentwitter.text = it.screenname
            textEmailtwitter.text = it.email
            Glide
                .with(context!!)
                .load(it.image)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imagetwitter)
        })

        fragmentTwitterViewmodel.handleLogin(twitter_login_button,object : Message{
            override fun listen(mess: String) {
                Toast.makeText(activity,mess,Toast.LENGTH_LONG).show()
            }

        })

        lifecycle.addObserver(fragmentTwitterViewmodel)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        twitter_login_button.onActivityResult(requestCode, resultCode, data)
    }
}


