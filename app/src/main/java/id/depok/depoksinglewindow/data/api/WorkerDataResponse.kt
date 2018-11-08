package id.depok.depoksinglewindow.data.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import id.depok.depoksinglewindow.data.WorkerData

data class WorkerDataResponse (
        @SerializedName("status")
        @Expose
        val status: Boolean,

        @SerializedName("data")
        @Expose
        val workers: MutableList<WorkerData>,

        @SerializedName("message")
        @Expose
        val message: String
)
