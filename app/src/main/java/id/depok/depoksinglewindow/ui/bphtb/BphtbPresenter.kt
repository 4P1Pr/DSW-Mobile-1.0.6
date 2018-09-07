package id.depok.depoksinglewindow.ui.bphtb

import id.depok.depoksinglewindow.ui.GenericPresenter

/**
 * Created by PiNGUiN on 2018-01-08.
 */
class BphtbPresenter: GenericPresenter<BphtbContract.View>(), BphtbContract.Presenter {

    override var view: BphtbContract.View? = null

    override fun start() {

    }

    override fun onPressCheckBphtb() {
        view?.showCheckBphtb()
    }

    override fun onPressInfoBphtb() {
        view?.showInfoBphtb()
    }

    override fun onPressInfoPembayaranBphtb() {
        view?.showInfoPembayaranBphtb()
    }
}