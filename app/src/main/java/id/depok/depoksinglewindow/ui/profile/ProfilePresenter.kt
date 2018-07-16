package id.depok.depoksinglewindow.ui.profile

import id.depok.depoksinglewindow.data.source.UserRepository
import id.depok.depoksinglewindow.ui.GenericPresenter

/**
 * Created by PiNGUiN on 2018-01-01.
 */
class ProfilePresenter(private val userRepository: UserRepository): GenericPresenter<ProfileContract.View>(), ProfileContract.Presenter {

    override var view: ProfileContract.View? = null

    override fun start() {
        view?.loadProfile(userRepository.getUser())
    }
}