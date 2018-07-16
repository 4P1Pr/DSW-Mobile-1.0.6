package id.depok.depoksinglewindow.data.source.remote

import id.depok.depoksinglewindow.data.api.PrayTimeResponse
import id.depok.depoksinglewindow.data.api.PrayTimeService
import id.depok.depoksinglewindow.data.source.PrayTimeDataSource
import io.reactivex.Single

/**
 * Created by PiNGUiN on 2017-12-22.
 */
class PrayTimeRemoteDataSource(val praytimeService: PrayTimeService): PrayTimeDataSource {

    override fun getPrayTimeSchedule(city: String): Single<PrayTimeResponse> {
        return praytimeService.getPrayTime(city)
    }
}