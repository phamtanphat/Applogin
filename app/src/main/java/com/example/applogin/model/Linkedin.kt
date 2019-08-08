package com.example.applogin.model

import com.example.applogin.api.response.LastName
import com.example.applogin.api.response.FirstName
import com.example.applogin.api.response.ProfilePicture
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Linkedin() {

    constructor(firstName: FirstName, lastName: LastName, profilePicture: ProfilePicture, id: String) : this()

    @SerializedName("firstName")
    @Expose
    var firstName: FirstName? = null
    @SerializedName("lastName")
    @Expose
    var lastName: LastName? = null
    @SerializedName("profilePicture")
    @Expose
    var profilePicture: ProfilePicture? = null
    @SerializedName("id")
    @Expose
    var id: String? = null

}