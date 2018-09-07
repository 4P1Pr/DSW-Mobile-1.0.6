package id.depok.depoksinglewindow.ui.callcenter

import id.depok.depoksinglewindow.data.CallCenterMenuType
import id.depok.depoksinglewindow.ui.MvpContract

interface CallCenterMenuContract {
    interface View: MvpContract.BaseView<Presenter> {

        fun showCallCenterDepok()

        fun showCallCenterOPD()

        fun showTelephone(phoneNumber: String)

    }

    interface Presenter: MvpContract.BasePresenter<View> {

        fun onMenuSelected(menu: CallCenterMenuType)

        fun onContactTapped(phoneNumber: String)
    }
}