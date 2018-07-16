package id.depok.depoksinglewindow.ui.zakat

import id.depok.depoksinglewindow.ui.GenericPresenter

class ZakatPresenter: GenericPresenter<ZakatContract.View>(), ZakatContract.Presenter {
    override var view: ZakatContract.View? = null

    override fun start() {

    }

    override fun onPressBayarZakat() {
        view?.showBayarZakat()
    }

    override fun onPressInfoZakat() {
        view?.showInfoZakat()
    }
}