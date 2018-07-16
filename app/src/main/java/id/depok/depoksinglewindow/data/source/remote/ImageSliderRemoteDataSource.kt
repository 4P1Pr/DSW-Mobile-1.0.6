package id.depok.depoksinglewindow.data.source.remote

import id.depok.depoksinglewindow.data.api.ApiService
import id.depok.depoksinglewindow.data.api.ImageSliderResponse
import id.depok.depoksinglewindow.data.source.ImageSliderDataSource
import io.reactivex.Single

/**
 * Created by PiNGUiN on 2018-01-01.
 */
class ImageSliderRemoteDataSource(private val apiService: ApiService): ImageSliderDataSource {

    override fun getImageSlider(): Single<ImageSliderResponse> {
        return apiService.getImageSlider()
    }
}