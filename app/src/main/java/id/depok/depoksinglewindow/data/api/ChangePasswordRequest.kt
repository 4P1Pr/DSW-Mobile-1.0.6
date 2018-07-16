package id.depok.depoksinglewindow.data.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by PiNGUiN on 2018-01-02.
 */
data class ChangePasswordRequest(
    @SerializedName("old_password")
    @Expose
    val oldPassword: String,

    @SerializedName("new_password")
    @Expose
    val newPassword: String,

    @Expose(serialize = false, deserialize = false)
    val token: String?
)