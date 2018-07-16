package id.depok.depoksinglewindow.data.source

import id.depok.depoksinglewindow.data.api.ImageSliderResponse
import io.reactivex.Single

/**
 * Created by PiNGUiN on 2018-01-01.
 */
class ImageSliderRepository(private val imageSliderDataSource: ImageSliderDataSource):
        ImageSliderDataSource {

    override fun getImageSlider(): Single<ImageSliderResponse> {
        return imageSliderDataSource.getImageSlider()
    }
}