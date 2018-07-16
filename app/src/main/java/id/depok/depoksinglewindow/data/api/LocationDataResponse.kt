package id.depok.depoksinglewindow.data.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import id.depok.depoksinglewindow.data.LocationData

/**
 * Created by PiNGUiN on 2017-12-29.
 */
data class LocationDataResponse(

        @SerializedName("status")
        @Expose
        val status: Boolean,

        @SerializedName("data")
        @Expose
        val locations: MutableList<LocationData>,

        @SerializedName("message")
        @Expose
        val message: String
)