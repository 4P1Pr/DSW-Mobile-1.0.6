package id.depok.depoksinglewindow.data.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by PiNGUiN on 2017-12-27.
 */
data class LogActivityRequest(
        @Expose(serialize = false, deserialize = false)
        val token: String?,
        @SerializedName("user_id")
        @Expose
        val user_id: Long,

        @SerializedName("event_name")
        @Expose
        val event_name: String

       )