package id.depok.depoksinglewindow.ui.splash

import android.os.Handler
import id.depok.depoksinglewindow.data.source.UserRepository
import id.depok.depoksinglewindow.ui.GenericPresenter
import org.koin.standalone.KoinComponent

/**
 * Created by PiNGUiN on 2017-12-07.
 */

const val SPLASH_DELAY: Long = 2000

class SplashPresenter(private val userRepository: UserRepository):
        GenericPresenter<SplashContract.View>(),
        SplashContract.Presenter,
        KoinComponent{

    override var view: SplashContract.View? = null

    override fun start() {
        Handler().postDelayed({ onSplashFinished() }, SPLASH_DELAY)
    }

    private fun onSplashFinished() {
        if(userRepository.userLocalDataSource.getUser() == null){
            // un logged in
            view?.showOnBoarding()
        } else{
            // already logged in
            view?.showMainPage()
        }
    }
}