package id.depok.depoksinglewindow.ui.home

import id.depok.depoksinglewindow.data.AppMenuType
import id.depok.depoksinglewindow.data.ImageData
import id.depok.depoksinglewindow.data.PrayTime
import id.depok.depoksinglewindow.ui.MvpContract
import java.util.*

/**
 * Created by PiNGUiN on 2017-12-17.
 */
interface HomeContract {

    interface View: MvpContract.BaseView<Presenter> {

        fun showPanggilanDarurat()

        fun showPBB()

        fun showBHTP()

        fun showLayananPerizinan()

        fun showHealthCareService()

        fun showQuestionAndAspiration()

        fun showContactCenter()

        fun showPengaturan()

        fun showLogin()

        fun loadPrayTime(prayTime: PrayTime, date: Date?)

        fun showError(error: Throwable)

        fun showTelephone(phoneNumber: String)

        fun loadSlider(images: List<ImageData>)

        fun showLayananPendidikan()

        fun showLayananZakat()

        fun showPetaDepok()

        fun showBeritaDepok()

        fun showInfoKemacetan()

        fun showInfoCuaca()

        fun showInfoLowonganKerja()

        fun showPLNdanPDAM()

        fun showPengaduan()

    }

    interface Presenter: MvpContract.BasePresenter<View> {

        fun onMenuClicked(appMenu: AppMenuType)

        fun onContactTapped(phoneNumber: String)
    }
}