package id.depok.depoksinglewindow.ui.onboarding

import id.depok.depoksinglewindow.ui.GenericPresenter

/**
 * Created by PiNGUiN on 2017-12-11.
 */
class OnBoardingPresenter :
        GenericPresenter<OnBoardingContract.View>(),
        OnBoardingContract.Presenter {
    override var view: OnBoardingContract.View? = null

    override fun start() {

    }

    override fun onPressNext() {
        view?.nextSlide()
    }

    override fun onPressSkip() {
        view?.showMainPage()
    }

    override fun onPressLogin() {
        view?.showLogin()
    }
}