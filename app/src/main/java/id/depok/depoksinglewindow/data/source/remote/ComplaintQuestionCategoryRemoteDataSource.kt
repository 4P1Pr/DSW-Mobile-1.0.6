package id.depok.depoksinglewindow.data.source.remote

import id.depok.depoksinglewindow.data.api.ApiService
import id.depok.depoksinglewindow.data.api.ComplainQuestionCategoriesResponse
import id.depok.depoksinglewindow.data.source.ComplaintQuestionCategoryDataSource
import io.reactivex.Single

/**
 * Created by PiNGUiN on 2017-12-28.
 */
class ComplaintQuestionCategoryRemoteDataSource(private val apiService: ApiService):
        ComplaintQuestionCategoryDataSource {

    override fun getComplaintCategoriesResponseCache(): ComplainQuestionCategoriesResponse? {
        return null
    }

    override fun getComplaintCategories(token: String?): Single<ComplainQuestionCategoriesResponse> {
        return apiService.getComplaintCategories(token)
    }

    override fun saveComplaintCategories(responseComplainQuestion: ComplainQuestionCategoriesResponse) {
    }

    override fun clearComplaintCategoriesResponseCache() {
    }

    override fun getQuestionCategoriesResponseCache(): ComplainQuestionCategoriesResponse? {
        return null
    }

    override fun getQuestionCategories(token: String?): Single<ComplainQuestionCategoriesResponse> {
        return apiService.getQuestionCategories(token)
    }

    override fun saveQuestionCategories(responseComplainQuestion: ComplainQuestionCategoriesResponse) {
    }

    override fun clearQuestionCategoriesResponseCache() {
    }
}