package id.depok.depoksinglewindow.data.source.remote

import id.depok.depoksinglewindow.data.api.ApiService
import id.depok.depoksinglewindow.data.api.ChangePasswordResponse
import id.depok.depoksinglewindow.data.api.LogActivityRequest
import id.depok.depoksinglewindow.data.source.LogDataSource
import io.reactivex.Single

/**
 * Created by PiNGUiN on 2017-12-10.
 */
class LogRemoteDataSource(private val apiService: ApiService) : LogDataSource {

    override fun saveLogActivity(logActivityRequest: LogActivityRequest): Single<ChangePasswordResponse> {
        return apiService.sendLogActivity(logActivityRequest.token, logActivityRequest)
    }

}