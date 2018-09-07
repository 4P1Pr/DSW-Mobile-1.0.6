package id.depok.depoksinglewindow.ui.aspiration

import id.depok.depoksinglewindow.data.ComplaintAndQuestion
import id.depok.depoksinglewindow.ui.MvpContract

/**
 * Created by PiNGUiN on 2018-01-18.
 */
interface AspirationListContract {

    interface View: MvpContract.BaseView<Presenter> {

        fun showDataLoading()

        fun hideDataLoading()

        fun loadData(data: List<ComplaintAndQuestion>)

        fun showPlaceholderMessage(stringId: Int)

        fun showPlaceholderMessage(message: String)

        fun showCreatePage(isComplaint: Boolean)
    }

    interface Presenter: MvpContract.BasePresenter<View> {

        var isComplaint: Boolean

        fun onRefresh()

        fun onPressCreateButton()
    }
}