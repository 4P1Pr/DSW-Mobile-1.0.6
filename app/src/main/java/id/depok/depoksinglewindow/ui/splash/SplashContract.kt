package id.depok.depoksinglewindow.ui.splash

import id.depok.depoksinglewindow.ui.MvpContract

/**
 * Created by PiNGUiN on 2017-12-07.
 * Specifies contract between view and presenter.
 */
interface SplashContract {

    interface View : MvpContract.BaseView<Presenter> {

        fun showOnBoarding()

        fun showMainPage()
    }

    interface Presenter : MvpContract.BasePresenter<View> {

    }
}
