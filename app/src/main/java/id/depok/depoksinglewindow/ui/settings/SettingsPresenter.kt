package id.depok.depoksinglewindow.ui.settings

import id.depok.depoksinglewindow.data.SettingsMenuType
import id.depok.depoksinglewindow.data.source.UserRepository
import id.depok.depoksinglewindow.manager.AnalyticsManager
import id.depok.depoksinglewindow.manager.LOGOUT
import id.depok.depoksinglewindow.ui.GenericPresenter
import org.koin.standalone.KoinComponent

/**
 * Created by PiNGUiN on 2017-12-20.
 */
class SettingsPresenter(private val userRepository: UserRepository,
                        private val analyticsManager: AnalyticsManager):
        GenericPresenter<SettingsContract.View>(),
        SettingsContract.Presenter,
        KoinComponent {

    override var view:SettingsContract.View? = null

    override fun start() {
        view?.initMenu(userRepository.getUser() != null)
    }

    override fun onPressMenu(menu: SettingsMenuType) {
        when(menu){
            (SettingsMenuType.PROFILE) -> view?.showProfile()
            (SettingsMenuType.EDIT_PASSWORD) -> view?.showEditPassword()
            (SettingsMenuType.ABOUT) -> view?.showAbout()
            (SettingsMenuType.DEV_TEAM) -> view?.showDevTeam()
            (SettingsMenuType.LOGOUT) -> {
                view?.showLogoutConfirmation()
            }
            else -> {
            }
        }
    }

    override fun onLogOutConfirmed() {
        analyticsManager.logEvent(LOGOUT)
        userRepository.deleteUser()
        view?.showOnBoarding()
    }
}