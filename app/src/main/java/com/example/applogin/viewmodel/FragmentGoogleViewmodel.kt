package com.example.applogin.viewmodel;

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.example.applogin.api.ApiConnection
import com.example.applogin.api.response.FirstName
import com.example.applogin.api.response.LastName
import com.example.applogin.api.response.ProfilePicture
import com.example.applogin.callback.Message
import com.example.applogin.model.Google
import com.example.applogin.model.Linkedin
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentGoogleViewmodel : ViewModel(), LifecycleObserver {

    private val mutableLiveDataGoogle = MutableLiveData<Google>()

    val data: LiveData<Google>
        get() {
            if (mutableLiveDataGoogle.value == null) {
                val google = Google("Name", "email", "image")
                mutableLiveDataGoogle.value = google
            }
            return mutableLiveDataGoogle
        }

    fun handleSignInResult(task: Task<GoogleSignInAccount>?, message: Message) {
        try {
            val account = task?.getResult(ApiException::class.java)
            mutableLiveDataGoogle.value = Google(account!!.displayName.toString(),account.email.toString(),account.photoUrl.toString())

        } catch (e: ApiException) {
            message.listen(e.message.toString())
        }

    }



}
