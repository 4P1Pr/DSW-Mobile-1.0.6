package id.depok.depoksinglewindow.data.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import id.depok.depoksinglewindow.data.User

/**
 * Created by PiNGUiN on 2017-12-25.
 */
data class RegisterResponse(
        @SerializedName("status")
        @Expose
        val status: Boolean,

        @SerializedName("data")
        @Expose
        val user: User,

        @SerializedName("message")
        @Expose
        val message: String
)