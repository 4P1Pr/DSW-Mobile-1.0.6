package id.depok.depoksinglewindow.data.source

import id.depok.depoksinglewindow.data.api.ComplainQuestionCategoriesResponse
import io.reactivex.Single

/**
 * Created by PiNGUiN on 2017-12-28.
 */
interface ComplaintQuestionCategoryDataSource {

    fun getComplaintCategoriesResponseCache(): ComplainQuestionCategoriesResponse?

    fun getComplaintCategories(token: String?): Single<ComplainQuestionCategoriesResponse>

    fun saveComplaintCategories(responseComplainQuestion: ComplainQuestionCategoriesResponse)

    fun clearComplaintCategoriesResponseCache()

    fun getQuestionCategoriesResponseCache(): ComplainQuestionCategoriesResponse?

    fun getQuestionCategories(token: String?): Single<ComplainQuestionCategoriesResponse>

    fun saveQuestionCategories(responseComplainQuestion: ComplainQuestionCategoriesResponse)

    fun clearQuestionCategoriesResponseCache()
}