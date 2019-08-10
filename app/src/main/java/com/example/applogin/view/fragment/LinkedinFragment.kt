package com.example.applogin.view.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.applogin.callback.Message

import com.example.applogin.R
import com.example.applogin.viewmodel.FragmentLinkedinViewmodel
import kotlinx.android.synthetic.main.fragment_linkedin.*

class LinkedinFragment : Fragment() {

    var fragmentLinkedinViewmodel = FragmentLinkedinViewmodel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_linkedin, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fragmentLinkedinViewmodel = ViewModelProviders.of(this).get(FragmentLinkedinViewmodel::class.java)

        fragmentLinkedinViewmodel.data.observe(this, Observer {
            textUsernameLinkedin.text = "${it.firstName?.localized?.enUS.toString()} ${it.lastName?.localized?.enUS.toString()}"
            Glide
                .with(context!!)
                .load(it.profilePicture?.displayImage?.elements?.get(2)?.identifiers?.get(0)?.identifier.toString())
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageLinkedin)
        })
        imagebuttonLoginLinkedin.setOnClickListener { v ->
            fragmentLinkedinViewmodel.loginLinkedIn(object : Message {
                override fun listen(mess: String) {
                    Toast.makeText(context, mess, Toast.LENGTH_LONG).show()
                }

            })
        }
        lifecycle.addObserver(fragmentLinkedinViewmodel)
    }


}
