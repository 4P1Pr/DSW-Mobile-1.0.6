package id.depok.depoksinglewindow.data.source

import id.depok.depoksinglewindow.data.api.ImageSliderResponse
import io.reactivex.Single

/**
 * Created by PiNGUiN on 2018-01-01.
 */
interface ImageSliderDataSource {

    fun getImageSlider(): Single<ImageSliderResponse>
}