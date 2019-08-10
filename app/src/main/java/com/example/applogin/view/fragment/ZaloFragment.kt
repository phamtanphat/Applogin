package com.example.applogin.view.fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders

import com.example.applogin.R
import com.example.applogin.viewmodel.FragmentZaloViewmodel
import com.zing.zalo.zalosdk.oauth.*
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_zalo.*

class ZaloFragment : Fragment() {

    var fragmentZaloViewmodel : FragmentZaloViewmodel = FragmentZaloViewmodel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_zalo, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fragmentZaloViewmodel = ViewModelProviders.of(this).get(FragmentZaloViewmodel::class.java)
        fragmentZaloViewmodel.data.observe(this, Observer {
            Glide.with(activity!!).load(it.image).placeholder(R.drawable.ic_launcher_background).into(imageZalo)
            textBirthdayZalo.text = it.birthday
            textGenderZalo.text = it.gender
            textUsernameZalo.text = it.name

        })
        zalo_button.setOnClickListener({   ZaloSDK.Instance.authenticate(activity, LoginVia.APP_OR_WEB, OAuthCompleteListener()) })

        lifecycle.addObserver(fragmentZaloViewmodel)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        fragmentZaloViewmodel.handleLogin(requestCode,resultCode,data,activity!!)
    }

}
