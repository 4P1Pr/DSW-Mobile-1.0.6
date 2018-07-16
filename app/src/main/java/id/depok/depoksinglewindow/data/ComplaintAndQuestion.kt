package id.depok.depoksinglewindow.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by PiNGUiN on 2018-01-20.
 */
data class ComplaintAndQuestion(
        @SerializedName("category_name")
        @Expose
        val categoryName: String,

        @SerializedName("message")
        @Expose
        val message: String,

        @SerializedName("file")
        @Expose
        val file: String,

        @SerializedName("image")
        @Expose
        val image: String,

        @SerializedName("created_at")
        @Expose
        val createdAt: Date,

        @SerializedName("status")
        @Expose
        val status: String,

        @SerializedName("insident_coordinate_long")
        @Expose
        val incidentLongitude: String,

        @SerializedName("insident_coordinate_lat")
        @Expose
        val incidentLatitude: String
        )

