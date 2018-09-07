package id.depok.depoksinglewindow.ui.layanankesehatan.registerpuskesmas

import id.depok.depoksinglewindow.data.RegisterPatienType
import id.depok.depoksinglewindow.ui.MvpContract
import id.depok.depoksinglewindow.ui.layanankesehatan.HealthCareServiceContract

interface RegistrasiPuskesmasContract {

    interface View: MvpContract.BaseView<HealthCareServiceContract.Presenter>{

        fun showRegistrationOldPatien()

        fun showRegistrationNewPatien()
    }

    interface Presenter : MvpContract.BasePresenter<View> {

        fun onMenuSelected(menu: RegisterPatienType)
    }
}