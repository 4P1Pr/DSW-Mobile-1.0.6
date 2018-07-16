package id.depok.depoksinglewindow.data.source.local

import android.content.Context
import com.orhanobut.hawk.Hawk
import id.depok.depoksinglewindow.data.api.ComplainQuestionCategoriesResponse
import id.depok.depoksinglewindow.data.source.ComplaintQuestionCategoryDataSource
import io.reactivex.Single

/**
 * Created by PiNGUiN on 2017-12-28.
 */

const val PREF_KEY_COMPLAINT_CATEGORIES = "PREF_KEY_COMPLAINT_CATEGORIES"
const val PREF_KEY_QUESTION_CATEGORIES = "PREF_KEY_QUESTION_CATEGORIES"

class ComplaintQuestionCategoryLocalDataSource(val context: Context): ComplaintQuestionCategoryDataSource {

    override fun getComplaintCategoriesResponseCache(): ComplainQuestionCategoriesResponse? {
        return Hawk.get(PREF_KEY_COMPLAINT_CATEGORIES)
    }

    override fun getComplaintCategories(token: String?): Single<ComplainQuestionCategoriesResponse> {
        return Single.just(Hawk.get(PREF_KEY_COMPLAINT_CATEGORIES))
    }

    override fun saveComplaintCategories(responseComplainQuestion: ComplainQuestionCategoriesResponse) {
        Hawk.put(PREF_KEY_COMPLAINT_CATEGORIES, responseComplainQuestion)
    }

    override fun clearComplaintCategoriesResponseCache() {
        Hawk.delete(PREF_KEY_COMPLAINT_CATEGORIES)
    }

    override fun getQuestionCategoriesResponseCache(): ComplainQuestionCategoriesResponse? {
        return Hawk.get(PREF_KEY_QUESTION_CATEGORIES)
    }

    override fun getQuestionCategories(token: String?): Single<ComplainQuestionCategoriesResponse> {
        return Single.just(Hawk.get(PREF_KEY_QUESTION_CATEGORIES))
    }

    override fun saveQuestionCategories(responseComplainQuestion: ComplainQuestionCategoriesResponse) {
        Hawk.put(PREF_KEY_QUESTION_CATEGORIES, responseComplainQuestion)
    }

    override fun clearQuestionCategoriesResponseCache() {
        Hawk.delete(PREF_KEY_QUESTION_CATEGORIES)
    }
}