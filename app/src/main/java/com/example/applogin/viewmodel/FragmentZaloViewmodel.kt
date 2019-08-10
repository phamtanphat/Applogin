package com.example.applogin.viewmodel;

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.applogin.callback.Message
import com.example.applogin.model.Zalo
import com.facebook.CallbackManager
import com.zing.zalo.zalosdk.oauth.ZaloOpenAPICallback
import com.zing.zalo.zalosdk.oauth.ZaloSDK
import org.json.JSONObject


class FragmentZaloViewmodel : ViewModel() , LifecycleObserver{
    private val mutableLiveDataZaloFragment = MutableLiveData<Zalo>()

    val data: LiveData<Zalo>
        get() {
            if (mutableLiveDataZaloFragment.value == null) {
                mutableLiveDataZaloFragment.value = Zalo("Name", "Gender", "01/01/1970", "Image")
            }
            return mutableLiveDataZaloFragment
        }

    fun handleLogin(requestCode: Int, resultCode: Int, data: Intent? , activity : FragmentActivity ) {
        var field: Array<String> = arrayOf("id", "birthday", "gender", "picture", "name")
        ZaloSDK.Instance.onActivityResult(activity, requestCode, resultCode, data);
        ZaloSDK.Instance.getProfile(activity, object : ZaloOpenAPICallback {
            override fun onResult(jSONObject: JSONObject?) {
                if (jSONObject!=null){
                    var zalo= Zalo(jSONObject!!.getString("name"),jSONObject.getString("gender"),jSONObject.getString("birthday"),jSONObject.getJSONObject("picture").getJSONObject("data").getString("url"))
                    mutableLiveDataZaloFragment.value = zalo
                }

            }

        }, field)

    }


}
