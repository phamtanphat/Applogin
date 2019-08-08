package com.example.applogin.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Identifier {

    @SerializedName("identifier")
    @Expose
    var identifier: String? = null

}