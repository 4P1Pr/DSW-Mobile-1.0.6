package id.depok.depoksinglewindow.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

const val MOCK_ID: Long = -1
const val JAWA_BARAT_ID: Long = 32
const val DEPOK_ID: Long = 3276

/**
 * Created by PiNGUiN on 2017-12-14.
 */
data class LocationData(

        @SerializedName("id")
        @Expose
        val id: Long,

        @SerializedName("name")
        @Expose
        var name: String
): Serializable {

    override fun toString(): String {
        return name
    }
}