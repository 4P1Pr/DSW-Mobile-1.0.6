package id.depok.depoksinglewindow.data.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginSigapRequest (
        @SerializedName("email")
        @Expose
        val email: String,

        @SerializedName("password")
        @Expose
        val password: String
)

