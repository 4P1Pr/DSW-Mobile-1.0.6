package id.depok.depoksinglewindow.data.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by PiNGUiN on 2017-12-25.
 */
data class RegisterRequest(
    @SerializedName("email")
    @Expose
    val email: String,

    @SerializedName("phone_number")
    @Expose
    val phoneNumber: String,

    @SerializedName("is_depok_citizen")
    @Expose
    val isDepokCitizen: Int,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("gender")
    @Expose
    val gender: Int,

    @SerializedName("address")
    @Expose
    val address: String,

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

    @SerializedName("password")
    @Expose
    val password: String
)