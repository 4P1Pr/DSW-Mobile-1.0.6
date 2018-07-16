package id.depok.depoksinglewindow.data.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import id.depok.depoksinglewindow.data.PrayTime
import id.depok.depoksinglewindow.data.Time

/**
 * Created by PiNGUiN on 2017-12-22.
 */
data class PrayTimeResponse(
        @SerializedName("data")
        @Expose
        val prayTime: PrayTime,

        @SerializedName("time")
        @Expose
        val time: Time
)