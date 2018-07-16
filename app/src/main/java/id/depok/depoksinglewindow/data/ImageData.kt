package id.depok.depoksinglewindow.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by PiNGUiN on 2018-01-01.
 */
data class ImageData(
    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("image")
    @Expose
    val image: String,

    @SerializedName("status")
    @Expose
    val status: Int
)