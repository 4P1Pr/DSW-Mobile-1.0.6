package id.depok.depoksinglewindow.data.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by PiNGUiN on 2017-12-24.
 */
data class LoginRequest(
        @SerializedName("email")
        @Expose
        val email: String,

        @SerializedName("password")
        @Expose
        val password: String)