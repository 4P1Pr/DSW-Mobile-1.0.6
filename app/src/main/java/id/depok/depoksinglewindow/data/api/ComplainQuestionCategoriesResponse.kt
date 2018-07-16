package id.depok.depoksinglewindow.data.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import id.depok.depoksinglewindow.data.ComplaintQuestionCategory

/**
 * Created by PiNGUiN on 2017-12-28.
 */
data class ComplainQuestionCategoriesResponse(

    @SerializedName("status")
    @Expose
    val status: Boolean,

    @SerializedName("data")
    @Expose
    val data: List<ComplaintQuestionCategory>,

    @SerializedName("nessage")
    @Expose
    val message: String
)