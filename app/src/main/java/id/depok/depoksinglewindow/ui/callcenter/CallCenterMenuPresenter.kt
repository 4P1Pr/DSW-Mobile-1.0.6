package id.depok.depoksinglewindow.ui.callcenter

import id.depok.depoksinglewindow.data.CallCenterMenuType
import id.depok.depoksinglewindow.ui.GenericPresenter
import org.koin.standalone.KoinComponent

class CallCenterMenuPresenter:
        GenericPresenter<CallCenterMenuContract.View>(),
        CallCenterMenuContract.Presenter, KoinComponent {

    override var view: CallCenterMenuContract.View? = null

    override fun start() {

    }

    override fun onMenuSelected(menu: CallCenterMenuType) {
        when(menu) {
            (CallCenterMenuType.CALL_CENTER_DEPOK) -> view?.showCallCenterDepok()
            (CallCenterMenuType.CALL_CENTER_OPD) -> view?.showCallCenterOPD()
            else -> {
            }
        }
    }

    override fun onContactTapped(phoneNumber: String) {
        view?.showTelephone(phoneNumber)
    }
}