package com.example.applogin.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FirstName {
    @SerializedName("localized")
    @Expose
    var localized: Localized? = null
}