package id.depok.depoksinglewindow.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*


/**
 * Created by PiNGUiN on 2017-12-22.
 */
data class PrayTime(
    @SerializedName("Fajr")
    @Expose
    var fajr: String? = null,

    @SerializedName("Sunrise")
    @Expose
    var sunrise: String? = null,

    @SerializedName("Dhuhr")
    @Expose
    var dhuhr: String? = null,

    @SerializedName("Asr")
    @Expose
    var asr: String? = null,

    @SerializedName("Sunset")
    @Expose
    var sunset: String? = null,

    @SerializedName("Maghrib")
    @Expose
    var maghrib: String? = null,

    @SerializedName("Isha")
    @Expose
    var isha: String? = null,

    @SerializedName("SepertigaMalam")
    @Expose
    var sepertigaMalam: String? = null,

    @SerializedName("TengahMalam")
    @Expose
    var tengahMalam: String? = null,

    @SerializedName("DuapertigaMalam")
    @Expose
    var duapertigaMalam: String? = null,

    @SerializedName("method")
    @Expose
    var method: List<String>? = null)

data class Time(
    @SerializedName("date")
    @Expose
    var date: Date? = null
)