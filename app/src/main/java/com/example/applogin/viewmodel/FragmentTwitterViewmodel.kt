package com.example.applogin.viewmodel

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.applogin.api.ApiConnection
import com.example.applogin.api.response.FirstName
import com.example.applogin.api.response.LastName
import com.example.applogin.api.response.ProfilePicture
import com.example.applogin.callback.Message
import com.example.applogin.model.Linkedin
import com.example.applogin.model.Twitter
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.TwitterSession
import com.twitter.sdk.android.core.identity.TwitterLoginButton
import com.twitter.sdk.android.core.models.User
import kotlinx.android.synthetic.main.fragment_twitter.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentTwitterViewmodel : ViewModel() , LifecycleObserver{
    private val mutableLiveDataTwitter = MutableLiveData<Twitter>()

    val data : LiveData<Twitter>
        get() {
            if (mutableLiveDataTwitter.value == null){
                mutableLiveDataTwitter.value = Twitter("user name","screen name","email","image")
            }
            return mutableLiveDataTwitter
        }
    fun handleLogin(twitterLoginButton: TwitterLoginButton, message: Message){
        twitterLoginButton.callback = object : com.twitter.sdk.android.core.Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>) {
                var twitterApiClient = TwitterCore.getInstance().getApiClient()
                var call: Call<User> = twitterApiClient.getAccountService().verifyCredentials(true, false, true);
                call.enqueue(object : com.twitter.sdk.android.core.Callback<User>() {
                    override fun failure(exception: TwitterException?) {
                        message.listen(exception!!.message.toString())
                    }

                    override fun success(result: Result<User>?) {
                        val user = result!!.data
                        mutableLiveDataTwitter.value = Twitter(user.name , user.screenName,user.email,user.profileImageUrl)
                    }

                })

            }

            override fun failure(exception: TwitterException) {
                message.listen(exception.message.toString())
            }
        }
    }

}