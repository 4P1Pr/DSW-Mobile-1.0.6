package id.depok.depoksinglewindow.ui.pdam.registerpelangganlamadanbaru

import id.depok.depoksinglewindow.data.RegisterPelangganMenuPDAMType
import id.depok.depoksinglewindow.ui.GenericPresenter
import org.koin.standalone.KoinComponent

class RegisterPelangganLamadanBaruPresenter:
        GenericPresenter<RegisterPelangganLamadanBaruInterface.View>(),
        RegisterPelangganLamadanBaruInterface.Presenter, KoinComponent {

    override var view: RegisterPelangganLamadanBaruInterface.View? = null

    override fun start() {

    }

    override fun onMenuSelected(menu: RegisterPelangganMenuPDAMType) {
        when(menu) {
            (RegisterPelangganMenuPDAMType.REGISTER_PELANGGAN_BARU_PDAM) -> view?.showRegisterPDAMPelangganBaru()
            (RegisterPelangganMenuPDAMType.REGISTER_PELANGGAN_LAMA_PDAM) -> view?.showRegisterPDAMPelangganLama()
            else -> {
            }
        }
    }
}