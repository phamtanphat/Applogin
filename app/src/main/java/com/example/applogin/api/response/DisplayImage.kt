package com.example.applogin.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class DisplayImage {

    @SerializedName("elements")
    @Expose
    var elements: List<Element>? = null

}