package id.depok.depoksinglewindow.ui.sigap.loginsigap

import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.data.api.LoginSigapRequest
import id.depok.depoksinglewindow.data.api.LoginSigapResponse
import id.depok.depoksinglewindow.data.source.AuthRepository
import id.depok.depoksinglewindow.data.source.UserRepository
import id.depok.depoksinglewindow.manager.AnalyticsManager
import id.depok.depoksinglewindow.manager.NetManager
import id.depok.depoksinglewindow.ui.GenericPresenter
import id.depok.depoksinglewindow.util.isValidEmailAddress
import id.depok.depoksinglewindow.util.rx.SchedulerProvider
import id.depok.depoksinglewindow.util.rx.with
import org.koin.standalone.KoinComponent

class SigapLoginPresenter (private val userRepository: UserRepository,
                           private val authRepository: AuthRepository,
                           private val netManager: NetManager,
                           private val schedulerProvider: SchedulerProvider,
                           private val analyticsManager: AnalyticsManager) :
    GenericPresenter<SigapLoginContract.View>(),
    SigapLoginContract.Presenter,
    KoinComponent{

    override var view: SigapLoginContract.View? = null

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
                    request = authRepository.loginSigap(LoginSigapRequest(email, password))
                            .with(schedulerProvider)
                            .subscribe(
                                    { loginResponseSigap -> handleLoginResponse(loginResponseSigap) },
                                    { handleLoginError() }
                            )
                } else {
                    view?.showAlert(R.string.all_errorconnection)
                }
            }
        }
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

    private fun handleLoginResponse(loginResponse: LoginSigapResponse) {
        view?.hideLoading()
        if(loginResponse.statussigap) {
            // log successful login
            userRepository.saveUserSigap(loginResponse.usersigap)
            analyticsManager.logSuccessfulLoginSigap()
            view?.showToast(loginResponse.msg)
            //view?.showMain()
        } else {
            view?.showAlert(R.string.login_loginfailed, loginResponse.msg)
        }
    }

    private fun handleLoginError() {
        view?.hideLoading()
        view?.showAlert(R.string.login_loginfailed)
    }
}