package com.example.applogin.view.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.applogin.R
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.fragment_main.*
import com.facebook.FacebookException

import android.content.Intent
import android.util.Log
import androidx.navigation.Navigation
import com.facebook.login.LoginManager
import kotlinx.android.synthetic.main.fragment_twitter.*


class MainFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        facebookLogin.setOnClickListener { v ->Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_facebookFragment)  }
        linkedinLogin.setOnClickListener { v -> Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_linkedinFragment) }
        googleLogin.setOnClickListener { v -> Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_googleFragment) }
        zaloLogin.setOnClickListener { v -> Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_zaloFragment) }
        twitterLogin.setOnClickListener{ v -> Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_twisterFragment) }
    }


}
