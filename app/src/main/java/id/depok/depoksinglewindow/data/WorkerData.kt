package id.depok.depoksinglewindow.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

const val WORKER_ID: Int = -1


/**
 * Created by PiNGUiN on 2017-12-14.
 */
data class WorkerData(

        @SerializedName("id_pekerjaan")
        @Expose
        val id: Int,

        @SerializedName("nama")
        @Expose
        var nama: String

): Serializable {

    override fun toString(): String {
        return nama
    }
}