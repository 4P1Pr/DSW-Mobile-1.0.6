package id.depok.depoksinglewindow.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserSigap (
        @SerializedName("id")
        @Expose
        val idsigap: Long,

        @SerializedName("nama")
        @Expose
        val nama: String,

        @SerializedName( "email")
        @Expose
        val emailsigap: String,

        @SerializedName( "id_fb")
        @Expose
        val fb: String,

        @SerializedName("id_gplus")
        @Expose
        val gplus: String,

        @SerializedName("phone")
        @Expose
        val nohp: String,

        @SerializedName("image")
        @Expose
        val image: Boolean,

        @SerializedName("created_at")
        @Expose
        val created: Long,

        @SerializedName("deleted_at")
        @Expose
        val deleted: Long,

        @SerializedName("auth_token")
        @Expose
        val authtoken: String,

        @SerializedName("latitude")
        @Expose
        val latitude: Long,

        @SerializedName("longitude")
        @Expose
        val longitude: Long,

        @SerializedName("num_reports")
        @Expose
        val numreports: Long,

        @SerializedName("fcm_token")
        @Expose
        val fcm: String,

        @SerializedName("opd_id")
        @Expose
        val opd: Long

        )