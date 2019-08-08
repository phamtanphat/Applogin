package com.example.applogin.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Localized {
    @SerializedName("en_US")
    @Expose
    var enUS: String? = null

}