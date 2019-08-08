package com.example.applogin.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Element {
    @SerializedName("identifiers")
    @Expose
    var identifiers: List<Identifier>? = null

}