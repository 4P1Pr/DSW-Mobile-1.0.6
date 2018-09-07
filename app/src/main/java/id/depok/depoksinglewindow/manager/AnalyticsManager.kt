package id.depok.depoksinglewindow.manager

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import id.depok.depoksinglewindow.data.api.ApiService
import id.depok.depoksinglewindow.data.api.LogActivityRequest
import id.depok.depoksinglewindow.data.source.UserDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


const val ACCESS_QUESTION_AND_ASPIRATION = "ACCESS_QUESTION_AND_ASPIRATION"
const val ACCESS_SETTINGS = "ACCESS_SETTINGS"
const val SEND_COMPLAINT = "SEND_COMPLAINT"
const val SEND_QUESTION = "SEND_QUESTION"
const val LOGOUT = "LOGOUT"

@Suppress("DEPRECATION")
class AnalyticsManager(private val apiService: ApiService, private val userDataSource: UserDataSource) {

    fun logSuccessfulLogin() {
        //if (!BuildConfig.DEBUG) {
            val bundle = Bundle()
            bundle.putString(FirebaseAnalytics.Param.SIGN_UP_METHOD, "email")
           //firebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle)
             apiService.sendLogActivity(userDataSource.getUser()!!.token,LogActivityRequest(userDataSource.getUser()!!.token, userDataSource.getUser()!!.id,FirebaseAnalytics.Event.LOGIN)).subscribeOn(Schedulers.io())
                     .observeOn(AndroidSchedulers.mainThread()).subscribe()
        //}
    }

    fun logSuccessfulLoginSigap() {
        //if (!BuildConfig.DEBUG) {
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SIGN_UP_METHOD, "email")
        //firebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle)
        apiService.sendLogActivity(userDataSource.getUserSigap()!!.authtoken,LogActivityRequest(userDataSource.getUserSigap()!!.authtoken, userDataSource.getUserSigap()!!.idsigap,FirebaseAnalytics.Event.LOGIN)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
        //}
    }

    fun logSuccessfulRegister() {
        //if (!BuildConfig.DEBUG) {
            val bundle = Bundle()
            bundle.putString(FirebaseAnalytics.Param.SIGN_UP_METHOD, "email")
            //firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SIGN_UP, bundle)
            apiService.sendLogActivity(userDataSource.getUser()!!.token,LogActivityRequest(userDataSource.getUser()!!.token, userDataSource.getUser()!!.id,FirebaseAnalytics.Event.SIGN_UP)).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe()

        //}
    }

    fun logEvent(page: String) {
        //if (!BuildConfig.DEBUG) {
        Bundle()
            apiService.sendLogActivity(userDataSource.getUser()!!.token,LogActivityRequest(userDataSource.getUser()!!.token, userDataSource.getUser()!!.id,page)).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe()
        //firebaseAnalytics.logEvent(page, bundle)
        //}
    }


}