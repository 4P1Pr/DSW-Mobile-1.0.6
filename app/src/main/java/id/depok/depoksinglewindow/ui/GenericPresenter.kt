package id.depok.depoksinglewindow.ui

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by PiNGUiN on 2017-12-23.
 */
abstract class GenericPresenter<V: MvpContract.BaseView<*>> : MvpContract.BasePresenter<V> {

    var request: Disposable? = null
    val compositeRequest: CompositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        request?.dispose()
        compositeRequest.dispose()
        view = null
    }
}