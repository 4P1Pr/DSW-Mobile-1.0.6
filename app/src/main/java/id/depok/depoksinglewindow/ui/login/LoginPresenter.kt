package id.depok.depoksinglewindow.ui.login

import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.data.api.LoginRequest
import id.depok.depoksinglewindow.data.api.LoginResponse
import id.depok.depoksinglewindow.data.source.AuthRepository
import id.depok.depoksinglewindow.data.source.UserRepository
import id.depok.depoksinglewindow.manager.AnalyticsManager
import id.depok.depoksinglewindow.manager.NetManager
import id.depok.depoksinglewindow.ui.GenericPresenter
import id.depok.depoksinglewindow.util.isValidEmailAddress
import id.depok.depoksinglewindow.util.rx.SchedulerProvider
import id.depok.depoksinglewindow.util.rx.with
import org.koin.standalone.KoinComponent

/**
 * Created by PiNGUiN on 2017-12-12.
 */
class LoginPresenter(private val userRepository: UserRepository,
                     private val authRepository: AuthRepository,
                     private val netManager: NetManager,
                     private val schedulerProvider: SchedulerProvider,
                     private val analyticsManager: AnalyticsManager) :
        GenericPresenter<LoginContract.View>(),
        LoginContract.Presenter,
        KoinComponent {

    override var view: LoginContract.View? = null

    override fun start() {

    }

    override fun onPressClose() {
        view?.close()
    }

    override fun onPressLogin(email: String, password: String) {
        view?.hideKeyboard()
        if (validate(email, password)) {
            netManager.isConnectedToInternet?.let {
                if (it) {
                    request?.dispose()
                    view?.showLoading(null)
                    request = authRepository.login(LoginRequest(email, password))
                            .with(schedulerProvider)
                            .subscribe(
                                    { loginResponse -> handleLoginResponse(loginResponse) },
                                    { handleLoginError()}

                            )
                } else {
                    view?.showAlert(R.string.all_errorconnection)
                }
            }
        }
    }

    override fun onPressForgotPassword() {
        view?.showForgotPassword()
    }

    override fun onPressRegister() {
        view?.showRegister()
    }

    private fun validate(email: String, password: String): Boolean {
        var status = true

        view?.clearError()
        if (email.isEmpty()) {
            status = false
            view?.showErrorEmail(R.string.all_fieldrequired)
        } else if (!email.isValidEmailAddress()) {
            status = false
            view?.showErrorEmail(R.string.all_errorinvalidemail)
        }

        if (password.isEmpty()) {
            status = false
            view?.showErrorPassword(R.string.all_fieldrequired)
        }

        return status
    }

    private fun handleLoginResponse(loginResponse: LoginResponse) {
        view?.hideLoading()
        if(loginResponse.status) {
            // log successful login
            userRepository.saveUser(loginResponse.user)
            analyticsManager.logSuccessfulLogin()
            view?.showToast(R.string.login_loginsuccess)
            view?.showMain()
        } else {
            view?.showAlert(R.string.login_loginfailed, loginResponse.message)
        }
    }

    private fun handleLoginError() {
        view?.hideLoading()
        view?.showAlert(R.string.login_loginfailed)
    }
}
