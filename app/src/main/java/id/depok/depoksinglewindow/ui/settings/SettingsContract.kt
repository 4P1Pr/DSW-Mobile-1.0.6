package id.depok.depoksinglewindow.ui.settings

import id.depok.depoksinglewindow.data.SettingsMenuType
import id.depok.depoksinglewindow.ui.MvpContract

/**
 * Created by PiNGUiN on 2017-12-20.
 */
interface SettingsContract {

    interface View: MvpContract.BaseView<Presenter> {

        fun showProfile()

        fun showEditPassword()

        fun showAbout()

        fun showDevTeam()

        fun initMenu(isLogin: Boolean)

        fun showLogoutConfirmation()

        fun showOnBoarding()
    }

    interface Presenter: MvpContract.BasePresenter<View> {

        fun onPressMenu(menu: SettingsMenuType)

        fun onLogOutConfirmed()
    }
}