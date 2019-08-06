package com.example.applogin.view.fragment


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.example.applogin.Callback.Message


import com.example.applogin.R
import com.example.applogin.model.Facebook
import com.example.applogin.viewmodel.FragmentFacebookViewmodel
import com.facebook.*

import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.fragment_facebook.*


class FacebookFragment : Fragment() , LifecycleObserver {

    var facebookViewmodel : FragmentFacebookViewmodel = FragmentFacebookViewmodel(lifecycle)
    val callbackManager : CallbackManager = CallbackManager.Factory.create()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_facebook, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        facebookViewmodel = ViewModelProviders.of(this).get(facebookViewmodel.javaClass)

        login_button.setReadPermissions(facebookViewmodel.permission())
        login_button.fragment = this

        facebookViewmodel.data.observe(this , Observer {
            textUsernameFacebook.text = it.name
            textBirthdayFacebook.text = it.birthday
            textGenderFacebook.text = it.gender
            imageFacebook.profileId = it.id
        })
        login_button.setOnClickListener{
            facebookViewmodel.loginfacebook(callbackManager , object : Message{
                override fun listen(mess: String) {
                    Toast.makeText(context,mess!!,Toast.LENGTH_LONG).show()
                }
            })
        }


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data)
    }


}
