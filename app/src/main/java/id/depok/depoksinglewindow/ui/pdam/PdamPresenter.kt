package id.depok.depoksinglewindow.ui.pdam

import id.depok.depoksinglewindow.data.MenuPDAM
import id.depok.depoksinglewindow.ui.GenericPresenter
import org.koin.standalone.KoinComponent

class PdamPresenter:
        GenericPresenter<PdamContract.View>(),
        PdamContract.Presenter, KoinComponent {

    override var view: PdamContract.View? = null

    override fun start() {

    }

    override fun onMenuSelected(menu: MenuPDAM) {
        when(menu) {
            (MenuPDAM.REGISTRASI_PDAM) -> view?.showRegisterPDAM()
            (MenuPDAM.CEK_TAGIHAN_PDAM) -> view?.showCekTagihanPDAM()
            (MenuPDAM.INFO_LAYANAN_PDAM) -> view?.showInfoLayananPDAM()
            (MenuPDAM.INFO_PEMBAYARAN) -> view?.showInfoPembayaran()
            (MenuPDAM.JARINGAN_LAYANAN_PDAM) -> view?.showJaringanLayananPDAM()
            (MenuPDAM.SIMULASI_TAGIHAN) -> view?.showSimulasiTagihan()
            else -> {
            }
        }
    }
}