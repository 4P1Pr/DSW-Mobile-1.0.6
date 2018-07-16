package id.depok.depoksinglewindow.ui.editpassword

import id.depok.depoksinglewindow.ui.MvpContract

/**
 * Created by PiNGUiN on 2017-12-27.
 */
interface EditPasswordContract {

    interface View: MvpContract.BaseView<Presenter> {

        fun showErrorOldPassword(stringId: Int)

        fun showErrorNewPassword(stringId: Int)

        fun showErrorNewPasswordConfirm(stringId: Int)
    }

    interface Presenter: MvpContract.BasePresenter<View> {

        fun onPressSave(oldPassword: String, newPassword: String, newPasswordConfirm: String)
    }
}