package id.depok.depoksinglewindow.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by PiNGUiN on 2017-12-28.
 */
data class ComplaintQuestionCategory(
        @SerializedName("id")
        @Expose
        val id: Int,

        @SerializedName("name")
        @Expose
        val name: String
) {

    override fun toString(): String {
        return name
    }
}