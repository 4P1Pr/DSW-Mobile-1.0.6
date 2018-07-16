package id.depok.depoksinglewindow.data.source

import id.depok.depoksinglewindow.data.api.PrayTimeResponse
import io.reactivex.Single

/**
 * Created by PiNGUiN on 2017-12-22.
 */
interface PrayTimeDataSource {

    fun getPrayTimeSchedule(city: String) : Single<PrayTimeResponse>
}