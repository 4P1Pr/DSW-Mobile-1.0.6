package id.depok.depoksinglewindow.data.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by PiNGUiN on 2017-12-27.
 */
data class ComplaintQuestionResponse(

        @SerializedName("status")
        @Expose
        val status: Boolean,

        @SerializedName("message")
        @Expose
        val message: String

)