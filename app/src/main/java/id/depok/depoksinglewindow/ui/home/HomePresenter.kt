package id.depok.depoksinglewindow.ui.home

import id.depok.depoksinglewindow.data.AppMenuType
import id.depok.depoksinglewindow.data.api.ImageSliderResponse
import id.depok.depoksinglewindow.data.source.ComplaintQuestionCategoryRepository
import id.depok.depoksinglewindow.data.source.ImageSliderRepository
import id.depok.depoksinglewindow.data.source.PrayTimeRepository
import id.depok.depoksinglewindow.data.source.UserRepository
import id.depok.depoksinglewindow.manager.ACCESS_QUESTION_AND_ASPIRATION
import id.depok.depoksinglewindow.manager.ACCESS_SETTINGS
import id.depok.depoksinglewindow.manager.AnalyticsManager
import id.depok.depoksinglewindow.ui.GenericPresenter
import id.depok.depoksinglewindow.util.rx.SchedulerProvider
import id.depok.depoksinglewindow.util.rx.with
import org.koin.standalone.KoinComponent

/**
 * Created by PiNGUiN on 2017-12-17.
 */
class HomePresenter (private val userRepository: UserRepository,
                     private val prayTimeRepository: PrayTimeRepository,
                     private val complaintCategoryRepository: ComplaintQuestionCategoryRepository,
                     private val imageSliderRepository: ImageSliderRepository,
                     private val schedulerProvider: SchedulerProvider,
                     private val analyticsManager: AnalyticsManager):
        GenericPresenter<HomeContract.View>(),
        HomeContract.Presenter, KoinComponent {

    override var view: HomeContract.View? = null

    override fun start() {
        // clear complaint & question category response cache
        complaintCategoryRepository.clearComplaintCategoriesResponseCache()
        complaintCategoryRepository.clearQuestionCategoriesResponseCache()

        // get pray time from api
        getPrayTime()

        // get slider from api
        getImageSlider()


    }

    override fun onMenuClicked(appMenu: AppMenuType) {
        when(appMenu) {
            AppMenuType.PANGGILAN_DARURAT -> {
                view?.showPanggilanDarurat()
            }
            AppMenuType.CEK_PBB -> {
                view?.showPBB()
            }
            AppMenuType.CEK_BPHTB -> {
                view?.showBHTP()
            }
            AppMenuType.LAYANAN_PERIZINAN -> {
                view?.showLayananPerizinan()
            }
            AppMenuType.LAYANAN_KESEHATAN -> {
                view?.showHealthCareService()
            }
            AppMenuType.PERTANYAAN_DAN_ASPIRASI -> {
                if(userRepository.getUser() != null) {
                    analyticsManager.logEvent(ACCESS_QUESTION_AND_ASPIRATION)
                    view?.showQuestionAndAspiration()
                } else {
                    view?.showLogin()
                }
            }
            AppMenuType.INFO_CUACA -> {
                view?.showInfoCuaca()
            }
            AppMenuType.CONTACT_CENTER -> {
                view?.showContactCenter()
            }
            AppMenuType.PENGATURAN -> {
                if (userRepository.getUser() != null) {
                    analyticsManager.logEvent(ACCESS_SETTINGS)
                    view?.showPengaturan()
                } else {
                    view?.showLogin()
                }
            }
            AppMenuType.LAYANAN_PENDIDIKAN -> {
                view?.showLayananPendidikan()
            }
            AppMenuType.LAYANAN_ZAKAT -> {
                view?.showLayananZakat()
            }
            AppMenuType.PETA_DEPOK -> {
                view?.showPetaDepok()
            }
            AppMenuType.BERITA_DEPOK -> {
                view?.showBeritaDepok()
            }
            AppMenuType.INFO_KEMACETAN -> {
                view?.showInfoKemacetan()
            }
            AppMenuType.INFO_LOWONGAN_KERJA -> {
                view?.showInfoLowonganKerja()
            }
            AppMenuType.PLN -> {
                view?.showPLN()
            }
            AppMenuType.PENGADUAN -> TODO()
        }
    }

    override fun onContactTapped(phoneNumber: String) {
        view?.showTelephone(phoneNumber)
    }

    private fun getPrayTime() {
        compositeRequest.add(prayTimeRepository.getPrayTimeSchedule("depok")
                .with(schedulerProvider)
                .subscribe(
                        { prayTimeResponse -> view?.loadPrayTime(prayTimeResponse.prayTime,
                                                                prayTimeResponse.time.date) },
                        { error -> view?.showError(error) }))
    }

    private fun getImageSlider() {
        request?.dispose()
        request = imageSliderRepository.getImageSlider()
                .with(schedulerProvider)
                .subscribe(
                        { response -> handleImageSliderResponse(response) },
                        { handleImageSliderError() }
                )
    }

    private fun handleImageSliderResponse(imageSliderResponse: ImageSliderResponse) {
        if (imageSliderResponse.status) {
            view?.loadSlider(imageSliderResponse.images)
        } else {
            view?.showToast(imageSliderResponse.message)
        }
    }

    private fun handleImageSliderError() {

    }
}