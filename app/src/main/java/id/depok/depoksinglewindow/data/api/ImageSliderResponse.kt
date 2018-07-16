package id.depok.depoksinglewindow.data.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import id.depok.depoksinglewindow.data.ImageData

/**
 * Created by PiNGUiN on 2018-01-01.
 */
data class ImageSliderResponse(
        @SerializedName("status")
        @Expose
        val status: Boolean,

        @SerializedName("data")
        @Expose
        val images: List<ImageData>,

        @SerializedName("message")
        @Expose
        val message: String
)