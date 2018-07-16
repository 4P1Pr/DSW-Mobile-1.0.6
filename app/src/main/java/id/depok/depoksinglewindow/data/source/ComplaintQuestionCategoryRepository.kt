package id.depok.depoksinglewindow.data.source

import id.depok.depoksinglewindow.data.api.ComplainQuestionCategoriesResponse
import io.reactivex.Single

/**
 * Created by PiNGUiN on 2017-12-28.
 */
class ComplaintQuestionCategoryRepository(private val localDataSource: ComplaintQuestionCategoryDataSource,
                                          private val remoteDataSource: ComplaintQuestionCategoryDataSource) :
        ComplaintQuestionCategoryDataSource {

    override fun getComplaintCategoriesResponseCache(): ComplainQuestionCategoriesResponse? {
        return localDataSource.getComplaintCategoriesResponseCache()
    }

    override fun getComplaintCategories(token: String?): Single<ComplainQuestionCategoriesResponse> {
        return localDataSource.getComplaintCategoriesResponseCache()?.let {
            Single.just(it)
        } ?: remoteDataSource.getComplaintCategories(token).doOnSuccess {
            response -> localDataSource.saveComplaintCategories(response)
        }
    }

    override fun saveComplaintCategories(responseComplainQuestion: ComplainQuestionCategoriesResponse) {
        localDataSource.saveComplaintCategories(responseComplainQuestion)
    }

    override fun clearComplaintCategoriesResponseCache() {
        localDataSource.clearComplaintCategoriesResponseCache()
    }

    override fun getQuestionCategoriesResponseCache(): ComplainQuestionCategoriesResponse? {
        return localDataSource.getQuestionCategoriesResponseCache()
    }

    override fun getQuestionCategories(token: String?): Single<ComplainQuestionCategoriesResponse> {
        return localDataSource.getQuestionCategoriesResponseCache()?.let {
            Single.just(it)
        } ?: remoteDataSource.getQuestionCategories(token).doOnSuccess {
            response -> localDataSource.saveQuestionCategories(response)
        }
    }

    override fun saveQuestionCategories(responseComplainQuestion: ComplainQuestionCategoriesResponse) {
        localDataSource.saveQuestionCategories(responseComplainQuestion)
    }

    override fun clearQuestionCategoriesResponseCache() {
        localDataSource.clearQuestionCategoriesResponseCache()
    }
}