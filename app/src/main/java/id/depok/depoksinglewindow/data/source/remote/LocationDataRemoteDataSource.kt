package id.depok.depoksinglewindow.data.source.remote

import id.depok.depoksinglewindow.data.api.ApiService
import id.depok.depoksinglewindow.data.api.LocationDataResponse
import id.depok.depoksinglewindow.data.source.LocationDataSource
import io.reactivex.Single

/**
 * Created by PiNGUiN on 2017-12-29.
 */
class LocationDataRemoteDataSource(private val apiService: ApiService): LocationDataSource {

    override fun getProvinces(): Single<LocationDataResponse> {
        return apiService.getProvinces()
    }

    override fun getKabupatens(provinceId: Long): Single<LocationDataResponse> {
        return apiService.getKabupatens(provinceId)
    }

    override fun getKecamatans(kabupatenId: Long): Single<LocationDataResponse> {
        return apiService.getKecamatans(kabupatenId)
    }

    override fun getKelurahans(kecamatanId: Long): Single<LocationDataResponse> {
        return apiService.getKelurahans(kecamatanId)
    }
}