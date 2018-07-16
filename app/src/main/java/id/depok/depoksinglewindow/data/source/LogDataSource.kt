package id.depok.depoksinglewindow.data.source

import id.depok.depoksinglewindow.data.api.*
import io.reactivex.Single

/**
 * Created by PiNGUiN on 2017-12-10.
 */
interface LogDataSource {
    fun saveLogActivity(logActivityRequest: LogActivityRequest) : Single<ChangePasswordResponse>

}