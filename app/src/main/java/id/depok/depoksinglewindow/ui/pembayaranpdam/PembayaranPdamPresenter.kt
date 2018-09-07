package id.depok.depoksinglewindow.ui.pembayaranpdam

import id.depok.depoksinglewindow.data.BankMenuType
import id.depok.depoksinglewindow.ui.GenericPresenter
import org.koin.standalone.KoinComponent

class PembayaranPdamPresenter:
        GenericPresenter<PembayaranPdamContract.View>(),
        PembayaranPdamContract.Presenter, KoinComponent {

    override var view: PembayaranPdamContract.View? = null

    override fun start() {

    }

    override fun onMenuSelected(menu: BankMenuType) {
        when(menu) {
            (BankMenuType.BNI) -> view?.showInfoBNI()
            (BankMenuType.MANDIRI) -> view?.showInfoMandiri()
            (BankMenuType.BCA) -> view?.showInfoBCA()
            (BankMenuType.PPOB_DAN_BANK_LAIN) -> view?.showPPOBdanBankLain()
            else -> {
            }
        }
    }
}