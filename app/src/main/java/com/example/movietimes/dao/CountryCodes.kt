package com.example.movietimes.dao

import com.google.gson.annotations.SerializedName


/**
 * Created by Abhishek Shah on 01 June 2023
 *
 * Thinkitive Technologies Pvt. Ltd.
 */
class CountryCodes {
    @SerializedName("name")
    val name: String = ""

    @SerializedName("dial_code")
    val dialCode: String = ""

    @SerializedName("code")
    val code: String = ""

    override fun toString(): String {
        return "CountryCodes(name='$name', " +
                "dialCode='$dialCode', " +
                "code='$code')"
    }


}
