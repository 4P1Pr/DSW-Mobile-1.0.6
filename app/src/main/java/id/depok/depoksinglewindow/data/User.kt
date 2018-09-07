package id.depok.depoksinglewindow.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by PiNGUiN on 2017-12-08.
 */
data class User (
        @SerializedName("id")
        @Expose
        val id: Long,

        val nik: String,

        @SerializedName("name")
        @Expose
        val name: String,
        val profilePicture: String,

        @SerializedName("nickname")
        @Expose
        val nickname: String,

        @SerializedName("address")
        @Expose
        val address: String,

        @SerializedName("email")
        @Expose
        val email: String,

        @SerializedName("birthplace")
        @Expose
        val birthPlace: String,

        @SerializedName("birhtdate")
        @Expose
        val birthDate: Date,

        @SerializedName("gender")
        @Expose
        val gender: Int,

        @SerializedName("religion")
        @Expose
        val religion: String,

        @SerializedName("marital_status")
        @Expose
        val maritalStatus: Int,

        @SerializedName("phone_number")
        @Expose
        val phoneNumber: String,

        @SerializedName("")
        @Expose
        val isDepokCitizen: Int,

        @SerializedName("zipcode")
        @Expose
        val zipcode: String,

        @SerializedName("province_id")
        @Expose
        val provinceId: Long,

        @SerializedName("kab_id")
        @Expose
        val kabId: Long,

        @SerializedName("kec_id")
        @Expose
        val kecId: Long,

        @SerializedName("kel_id")
        @Expose
        val kelId: Long,

        @SerializedName("token")
        @Expose
        val token: String
)