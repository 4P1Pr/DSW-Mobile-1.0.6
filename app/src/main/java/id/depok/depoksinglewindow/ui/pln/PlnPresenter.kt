package id.depok.depoksinglewindow.ui.pln

import id.depok.depoksinglewindow.ui.GenericPresenter

class PlnPresenter: GenericPresenter<PlnContract.View>(), PlnContract.Presenter {
    override var view: PlnContract.View? = null

    override fun start() {

    }

    override fun onPressBayarPLN() {
        view?.showBayarPLN()
    }

    override fun onPressInfoPLN() {
        view?.showInfoPLN()
    }
}