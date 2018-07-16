package id.depok.depoksinglewindow.ui.onboarding

import id.depok.depoksinglewindow.ui.MvpContract

/**
 * Created by PiNGUiN on 2017-12-11.
 */
interface OnBoardingContract {

    interface View : MvpContract.BaseView<Presenter> {

        fun nextSlide()

        fun showLogin()

        fun showMainPage()
    }

    interface Presenter : MvpContract.BasePresenter<View> {

        fun onPressNext()

        fun onPressSkip()

        fun onPressLogin()
    }
}