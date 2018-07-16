package id.depok.depoksinglewindow.ui.register

import id.depok.depoksinglewindow.data.LocationData
import id.depok.depoksinglewindow.data.RegisterForm
import id.depok.depoksinglewindow.ui.MvpContract

/**
 * Created by PiNGUiN on 2017-12-14.
 */
interface Register2Contract {

    interface View: MvpContract.BaseView<Presenter> {

        fun close()

        fun showProvinceSpinner()

        fun showKabupatenSpinner()

        fun hideProvinceSpinner()

        fun hideKabupatenSpinner()

        fun setProvinces(provinceList: MutableList<LocationData>)

        fun setKabupatens(kabupatenList: MutableList<LocationData>)

        fun setKecamatans(kecamatanList: MutableList<LocationData>)

        fun setKelurahans(kelurahanList: MutableList<LocationData>)

        fun clearKabupatens()

        fun clearKecamatans()

        fun clearKelurahans()

        fun showErrorAddress(stringId: Int)

        fun showErrorPostalCode(stringId: Int)

        fun clearError()

        fun showMain()

    }

    interface Presenter: MvpContract.BasePresenter<View> {

        var initialFormData: RegisterForm?

        fun onDepokResidencesSelected(index: Int)

        fun onProvinceSelected(province: LocationData)

        fun onKabupatenSelected(kabupaten: LocationData)

        fun onKecamatanSelected(kecamatan: LocationData)

        fun onPressRegister(formData: RegisterForm)
    }
}