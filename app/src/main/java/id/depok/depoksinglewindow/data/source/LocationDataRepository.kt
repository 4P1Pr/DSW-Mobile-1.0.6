package id.depok.depoksinglewindow.data.source

import id.depok.depoksinglewindow.data.api.LocationDataResponse
import io.reactivex.Single

/**
 * Created by PiNGUiN on 2017-12-29.
 */
class LocationDataRepository(private val remoteDataSource: LocationDataSource): LocationDataSource {

    override fun getProvinces(): Single<LocationDataResponse> {
        return remoteDataSource.getProvinces()
    }

    override fun getKabupatens(provinceId: Long): Single<LocationDataResponse> {
        return remoteDataSource.getKabupatens(provinceId)
    }

    override fun getKecamatans(kabupatenId: Long): Single<LocationDataResponse> {
        return remoteDataSource.getKecamatans(kabupatenId)
    }

    override fun getKelurahans(kecamatanId: Long): Single<LocationDataResponse> {
        return remoteDataSource.getKelurahans(kecamatanId)
    }
}