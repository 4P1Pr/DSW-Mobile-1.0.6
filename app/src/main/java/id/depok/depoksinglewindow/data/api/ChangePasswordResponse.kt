package id.depok.depoksinglewindow.data.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by PiNGUiN on 2018-01-02.
 */
data class ChangePasswordResponse(
        @SerializedName("status")
        @Expose
        val status: Boolean,

        @SerializedName("message")
        @Expose
        val message: String
)