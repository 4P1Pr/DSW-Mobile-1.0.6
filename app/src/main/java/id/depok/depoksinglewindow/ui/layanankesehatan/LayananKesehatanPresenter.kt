package id.depok.depoksinglewindow.ui.layanankesehatan

import id.depok.depoksinglewindow.data.LayananKesehatanMenuType
import id.depok.depoksinglewindow.ui.GenericPresenter
import org.koin.standalone.KoinComponent

/**
 * Created by PiNGUiN on 2017-12-20.
 */
class LayananKesehatanPresenter:
        GenericPresenter<HealthCareServiceContract.View>(),
        HealthCareServiceContract.Presenter, KoinComponent {

    override var view: HealthCareServiceContract.View? = null

    override fun start() {

    }

    override fun onMenuSelected(menu: LayananKesehatanMenuType) {
        when(menu) {
            (LayananKesehatanMenuType.HEALTHCARE_SERVICE_INFORMATION) ->
                view?.showHealthCareServiceInformation()
            (LayananKesehatanMenuType.HEALTHCARE_SERVICE_REGISTRATION) ->
                view?.showHealthCareServiceRegistration()
            else -> {
            }
        }
    }
}