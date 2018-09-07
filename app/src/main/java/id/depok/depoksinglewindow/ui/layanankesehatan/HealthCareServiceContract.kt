package id.depok.depoksinglewindow.ui.layanankesehatan

import id.depok.depoksinglewindow.data.LayananKesehatanMenuType
import id.depok.depoksinglewindow.ui.MvpContract

/**
 * Created by PiNGUiN on 2017-12-20.
 */
interface HealthCareServiceContract {

    interface View: MvpContract.BaseView<Presenter> {


        fun showHealthCareServiceInformation()

        fun showHealthCareServiceRegistration()

        fun showHealthCareServiceHealthNews()
    }

    interface Presenter: MvpContract.BasePresenter<View> {

        fun onMenuSelected(menu: LayananKesehatanMenuType)
    }
}