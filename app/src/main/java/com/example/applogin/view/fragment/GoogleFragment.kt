package com.example.applogin.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.applogin.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.fragment_google.*
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.applogin.callback.Message
import com.example.applogin.model.Google
import com.example.applogin.viewmodel.FragmentGoogleViewmodel




class GoogleFragment : Fragment() {

    lateinit var googleApiClient : GoogleSignInClient
    val RC_SIGN_IN : Int = 123
    var fragmentGoogleViewmodel = FragmentGoogleViewmodel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_google, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fragmentGoogleViewmodel = ViewModelProviders.of(this).get(FragmentGoogleViewmodel::class.java)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()
            .build()

        googleApiClient = GoogleSignIn.getClient(context!!, gso)

        fragmentGoogleViewmodel.data.observe(this , object : Observer<Google> {
            override fun onChanged(t: Google?) {
                textUsernameGoogle.text = t!!.name
                textEmailGoogle.text = t.email
                if (!t.image.equals("null")){
                    Glide.with(context!!).load(t.image).placeholder(R.drawable.ic_launcher_background).into(imageGoogle)
                }else{
                    Toast.makeText(context,"Ban khong co hinh",Toast.LENGTH_LONG).show()
                }
            }
        })
        sign_in_button.setOnClickListener { v ->
            signIn()
        }
        lifecycle.addObserver(fragmentGoogleViewmodel)
    }
    private fun signIn() {
        val signInIntent = googleApiClient.getSignInIntent()
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            fragmentGoogleViewmodel.handleSignInResult(task , object  : Message {
                override fun listen(mess: String) {
                    Toast.makeText(context,mess,Toast.LENGTH_LONG).show()
                }

            })
        }
    }



}
