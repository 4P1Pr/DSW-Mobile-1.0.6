package id.depok.depoksinglewindow.data.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by PiNGUiN on 2017-12-22.
 */
interface PrayTimeService {

    @GET("/pray/{city}")
    fun getPrayTime(@Path("city") city: String) : Single<PrayTimeResponse>
}