package com.example.applogin.viewmodel

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import androidx.lifecycle.Lifecycle.Event.ON_CREATE
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import com.example.applogin.model.Facebook
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult

import java.util.*
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData

class FragmentFacebookViewmodel : ViewModel() , LifecycleObserver{


    private val mutableLiveDatafacebook = MutableLiveData<Facebook>()

    val data : LiveData<Facebook>
    get() {
        if (mutableLiveDatafacebook.value == null){
            var facebook = Facebook("01/01/1970" , "user name","1","nam")
            mutableLiveDatafacebook.value = facebook
        }
        return mutableLiveDatafacebook
    }

    @OnLifecycleEvent(ON_CREATE)
    fun loginfacebook( callbackManager: CallbackManager ){
        LoginManager.getInstance().registerCallback(callbackManager, object  : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult?) {
                val request = GraphRequest.newMeRequest(loginResult!!.accessToken ){ `object`, response ->
                    mutableLiveDatafacebook.value = Facebook(
                        `object`["birthday"].toString(),
                        `object`["name"].toString(),
                        `object`["id"].toString(),
                        `object`["gender"].toString())
                }
                val parameters = Bundle()
                parameters.putString("fields", "name,gender,birthday")
                request.parameters = parameters
                request.executeAsync()
            }

            override fun onCancel() {

            }

            override fun onError(error: FacebookException?) {

            }

        })
    }

    @OnLifecycleEvent(ON_DESTROY)
    fun clear(){
        mutableLiveDatafacebook.value = null
    }

    fun permission() : List<String>{
        return Arrays.asList("user_gender" , "user_birthday")
    }

}