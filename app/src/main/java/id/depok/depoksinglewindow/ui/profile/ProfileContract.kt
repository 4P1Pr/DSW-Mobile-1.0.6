package id.depok.depoksinglewindow.ui.profile

import id.depok.depoksinglewindow.data.User
import id.depok.depoksinglewindow.ui.MvpContract

/**
 * Created by PiNGUiN on 2018-01-01.
 */
interface ProfileContract {

    interface View: MvpContract.BaseView<Presenter> {

        fun loadProfile(user: User?)

    }

    interface Presenter: MvpContract.BasePresenter<View> {

    }
}