package id.depok.depoksinglewindow.ui.editpassword

import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.data.api.ChangePasswordRequest
import id.depok.depoksinglewindow.data.api.ChangePasswordResponse
import id.depok.depoksinglewindow.data.source.AuthRepository
import id.depok.depoksinglewindow.data.source.UserRepository
import id.depok.depoksinglewindow.manager.NetManager
import id.depok.depoksinglewindow.ui.GenericPresenter
import id.depok.depoksinglewindow.util.rx.SchedulerProvider
import id.depok.depoksinglewindow.util.rx.with

/**
 * Created by PiNGUiN on 2017-12-27.
 */
class EditPasswordPresenter(private val authRepository: AuthRepository,
                            private val schedulerProvider: SchedulerProvider,
                            private val netManager: NetManager,
                            private val userRepository: UserRepository): GenericPresenter<EditPasswordContract.View>(),
        EditPasswordContract.Presenter {

    override var view: EditPasswordContract.View? = null

    override fun start() {

    }

    override fun onPressSave(oldPassword: String, newPassword: String, newPasswordConfirm: String) {
        view?.hideKeyboard()
        if (isValid(oldPassword, newPassword, newPasswordConfirm)) {
            netManager.isConnectedToInternet?.let {
                if (it) {
                    view?.showLoading(null)
                    request?.dispose()
                    request = authRepository.changePassword(
                            ChangePasswordRequest(oldPassword,
                                    newPassword,
                                    userRepository.getUser()?.token))
                            .with(schedulerProvider)
                            .subscribe( { response -> handleEditPasswordResponse(response) },
                                    { handleEditPasswordError() })
                } else {
                    view?.showAlert(R.string.all_errorconnection)
                }
            }
        } else {
            view?.showAlert(R.string.all_datarequired)
        }
    }

    private fun isValid(oldPassword: String, newPassword: String, newPasswordConfirm: String): Boolean {
        var status = true

        if (oldPassword.isEmpty()) {
           status = false
            view?.showErrorOldPassword(R.string.all_fieldrequired)
        }
        if (newPassword.isEmpty()) {
            status = false
            view?.showErrorNewPassword(R.string.all_fieldrequired)
        }
        if (newPasswordConfirm.isEmpty()) {
            status = false
            view?.showErrorNewPasswordConfirm(R.string.all_fieldrequired)
        }

        if (status) {
            if (newPassword != newPasswordConfirm) {
                status = false
                view?.showErrorNewPasswordConfirm(R.string.editpassword_newpassordconfirm_notequal)
            }
        }

        return status
    }

    private fun handleEditPasswordResponse(response: ChangePasswordResponse) {
        if (response.status) {
            view?.hideLoading()
            view?.showAlert(R.string.editpassword_editpasswordsuccess)
        } else {
            view?.hideLoading()
            view?.showAlert(R.string.editpassword_editpasswordfailed, response.message)
        }
    }

    private fun handleEditPasswordError() {
        view?.hideLoading()
        view?.showAlert(R.string.editpassword_editpasswordfailed)
    }
}