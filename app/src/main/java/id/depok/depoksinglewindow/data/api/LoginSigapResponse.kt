package id.depok.depoksinglewindow.data.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import id.depok.depoksinglewindow.data.UserSigap

data class LoginSigapResponse (

        @SerializedName("status")
        @Expose
        val statussigap: Boolean,

        @SerializedName("data")
        @Expose
        val usersigap: UserSigap,

        @SerializedName("msg")
        @Expose
        val msg: String
)
