package id.depok.depoksinglewindow.ui.pdam.registerpelangganlamadanbaru

import id.depok.depoksinglewindow.data.RegisterPelangganMenuPDAMType
import id.depok.depoksinglewindow.ui.MvpContract

interface RegisterPelangganLamadanBaruInterface {
interface View: MvpContract.BaseView<Presenter> {

    fun showRegisterPDAMPelangganLama()

    fun showRegisterPDAMPelangganBaru()


}

interface Presenter: MvpContract.BasePresenter<View> {

    fun onMenuSelected(menu: RegisterPelangganMenuPDAMType)
}
}