package id.depok.depoksinglewindow.data.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import id.depok.depoksinglewindow.data.ComplaintAndQuestion

/**
 * Created by PiNGUiN on 2018-01-20.
 */
data class GetComplaintQuestionResponse(

    @SerializedName("data")
    @Expose
    val data: List<ComplaintAndQuestion>,

    @SerializedName("status")
    @Expose
    val status: Boolean,

    @SerializedName("message")
    @Expose
    val message: String
)