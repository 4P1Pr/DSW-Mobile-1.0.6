package id.depok.depoksinglewindow.data.source

import id.depok.depoksinglewindow.data.api.LocationDataResponse
import io.reactivex.Single

/**
 * Created by PiNGUiN on 2017-12-29.
 */
interface LocationDataSource {

    fun getProvinces(): Single<LocationDataResponse>

    fun getKabupatens(provinceId: Long): Single<LocationDataResponse>

    fun getKecamatans(kabupatenId: Long): Single<LocationDataResponse>

    fun getKelurahans(kecamatanId: Long): Single<LocationDataResponse>

}