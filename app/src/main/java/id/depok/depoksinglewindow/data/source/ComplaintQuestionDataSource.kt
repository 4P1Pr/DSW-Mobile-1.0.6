package id.depok.depoksinglewindow.data.source

import id.depok.depoksinglewindow.data.api.ComplaintQuestionRequest
import id.depok.depoksinglewindow.data.api.ComplaintQuestionResponse
import id.depok.depoksinglewindow.data.api.GetComplaintQuestionResponse
import io.reactivex.Single

/**
 * Created by PiNGUiN on 2017-12-27.
 */
interface ComplaintQuestionDataSource {

    fun sendComplaintQuestion(complaintQuestionQuestionRequest: ComplaintQuestionRequest)
            : Single<ComplaintQuestionResponse>

    fun getComplaint(token: String): Single<GetComplaintQuestionResponse>

    fun getQuestion(token: String): Single<GetComplaintQuestionResponse>
}