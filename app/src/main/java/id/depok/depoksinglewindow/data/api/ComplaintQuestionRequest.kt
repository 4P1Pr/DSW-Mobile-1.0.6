package id.depok.depoksinglewindow.data.api

import android.net.Uri

/**
 * Created by PiNGUiN on 2017-12-27.
 */
data class ComplaintQuestionRequest(
        val name: String,
        val email: String,
        val phoneNumber: String,
        val category: Int,
        val message: String,
        val fileUri: Uri?,
        val imageUri: Uri?,
        val token: String?,
        val userLongitude: Double,
        val userLatitude: Double,
        val incidentLongitude: Double?,
        val incidentLatitude: Double?,
        val type: String?
        )