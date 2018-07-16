package id.depok.depoksinglewindow.data.source.remote

import android.net.Uri
import android.webkit.MimeTypeMap
import id.depok.depoksinglewindow.data.api.ApiService
import id.depok.depoksinglewindow.data.api.ComplaintQuestionRequest
import id.depok.depoksinglewindow.data.api.ComplaintQuestionResponse
import id.depok.depoksinglewindow.data.api.GetComplaintQuestionResponse
import id.depok.depoksinglewindow.data.source.ComplaintQuestionDataSource
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File




@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
/**
 * Created by PiNGUiN on 2017-12-27.
 */
class ComplaintQuestionRemoteDataSource(private val apiService: ApiService):
        ComplaintQuestionDataSource {

    override fun sendComplaintQuestion(complaintQuestionQuestionRequest: ComplaintQuestionRequest):
            Single<ComplaintQuestionResponse> {

        val file: File? = if (complaintQuestionQuestionRequest.fileUri != null)
                                File(complaintQuestionQuestionRequest.fileUri.path)
                            else null
        val type = getMimeType(complaintQuestionQuestionRequest.fileUri)
        val requestFile: RequestBody? = if (file != null && type != null)
                                            RequestBody.create(MediaType.parse(type), file)
                                        else null
        val fileBody: MultipartBody.Part? = if (requestFile != null)
                                                MultipartBody.Part.createFormData("file",
                                                        "file", requestFile)
                                            else null

        val image = if (complaintQuestionQuestionRequest.imageUri != null)
                        File(complaintQuestionQuestionRequest.imageUri.path)
                    else null
        val mimeType = getMimeType(complaintQuestionQuestionRequest.imageUri)
        val requestImage: RequestBody? = if (image != null && mimeType != null)
                                            RequestBody.create(MediaType.parse(mimeType), image)
                                        else null
        val imageBody: MultipartBody.Part? = if (requestImage != null)
                                                MultipartBody.Part.createFormData("image",
                                                        "image", requestImage)
                                            else null

        val name: RequestBody = RequestBody.create(
                MediaType.parse("text/plain"),
                complaintQuestionQuestionRequest.name)
        val email: RequestBody = RequestBody.create(
                MediaType.parse("text/plain"),
                complaintQuestionQuestionRequest.email)
        val phone: RequestBody = RequestBody.create(
                MediaType.parse("text/plain"),
                complaintQuestionQuestionRequest.phoneNumber)
        val category: RequestBody = RequestBody.create(
                MediaType.parse("text/plain"),
                complaintQuestionQuestionRequest.category.toString())
        val message: RequestBody = RequestBody.create(
                MediaType.parse("text/plain"),
                complaintQuestionQuestionRequest.message)
        val userLongitude: RequestBody = RequestBody.create(
                MediaType.parse("text/plain"),
                complaintQuestionQuestionRequest.userLongitude.toString())
        val userLatitude: RequestBody = RequestBody.create(
                MediaType.parse("text/plain"),
                complaintQuestionQuestionRequest.userLatitude.toString())
        val incidentLongitude: RequestBody = RequestBody.create(
                MediaType.parse("text/plain"),
                if (complaintQuestionQuestionRequest.incidentLongitude != null)
                    complaintQuestionQuestionRequest.incidentLongitude.toString() else "")
        val incidentLatitude: RequestBody = RequestBody.create(
                MediaType.parse("text/plain"),
                if (complaintQuestionQuestionRequest.incidentLatitude != null)
                    complaintQuestionQuestionRequest.incidentLatitude.toString() else "")
        val typeComplaint: RequestBody = RequestBody.create(
                MediaType.parse("text/plain"),
                complaintQuestionQuestionRequest.type)
        return apiService.sendComplaint(complaintQuestionQuestionRequest.token,
            name, email, phone, category, message, fileBody, imageBody, userLongitude, userLatitude,
            incidentLongitude, incidentLatitude,typeComplaint)
    }

    override fun getComplaint(token: String): Single<GetComplaintQuestionResponse> {
        return apiService.getComplaint(token)
    }

    override fun getQuestion(token: String): Single<GetComplaintQuestionResponse> {
        return apiService.getQuestion(token)
    }

    private fun getMimeType(uri: Uri?): String? {
        var mimeType: String? = null
        if (uri != null) {
            val fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri
                    .toString())
            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                    fileExtension.toLowerCase())
        }
        return mimeType
    }
}