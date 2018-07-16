package id.depok.depoksinglewindow.data.source

import id.depok.depoksinglewindow.data.api.ComplaintQuestionRequest
import id.depok.depoksinglewindow.data.api.ComplaintQuestionResponse
import id.depok.depoksinglewindow.data.api.GetComplaintQuestionResponse
import io.reactivex.Single

/**
 * Created by PiNGUiN on 2017-12-27.
 */
class ComplaintQuestionQuestionRepository(private val complaintQuestionRemoteDataSource: ComplaintQuestionDataSource): ComplaintQuestionDataSource {

    override fun sendComplaintQuestion(complaintQuestionQuestionRequest: ComplaintQuestionRequest): Single<ComplaintQuestionResponse> {
        return complaintQuestionRemoteDataSource.sendComplaintQuestion(complaintQuestionQuestionRequest)
    }

    override fun getComplaint(token: String): Single<GetComplaintQuestionResponse> {
        return complaintQuestionRemoteDataSource.getComplaint(token)
    }

    override fun getQuestion(token: String): Single<GetComplaintQuestionResponse> {
        return complaintQuestionRemoteDataSource.getQuestion(token)
    }
}