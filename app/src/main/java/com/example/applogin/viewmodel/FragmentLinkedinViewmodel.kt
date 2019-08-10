package com.example.applogin.viewmodel

import androidx.lifecycle.*
import com.example.applogin.callback.Message
import com.example.applogin.api.ApiConnection
import com.example.applogin.api.response.FirstName
import com.example.applogin.api.response.LastName

import com.example.applogin.api.response.ProfilePicture

import com.example.applogin.model.Linkedin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentLinkedinViewmodel : ViewModel() , LifecycleObserver {

    private val mutableLiveDataLinkedin = MutableLiveData<Linkedin>()

    val data : LiveData<Linkedin>
        get() {
            if (mutableLiveDataLinkedin.value == null){
                val linkedin = Linkedin(FirstName(), LastName(), ProfilePicture(),"null")
                mutableLiveDataLinkedin.value = linkedin
            }
            return mutableLiveDataLinkedin
        }
    fun loginLinkedIn(message: Message){
        ApiConnection.getAPi()!!.login().enqueue(object : Callback<Linkedin>{
            override fun onFailure(call: Call<Linkedin>, t: Throwable) {
                message.listen(t.message.toString())
            }
            override fun onResponse(call: Call<Linkedin>, response: Response<Linkedin>) {
                if (response.isSuccessful && response.code() == 200){
                    mutableLiveDataLinkedin.value = response.body()
                }
            }
        })
    }
}