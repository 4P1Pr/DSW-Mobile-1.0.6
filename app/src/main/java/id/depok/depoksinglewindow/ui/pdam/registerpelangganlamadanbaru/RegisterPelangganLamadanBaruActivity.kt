package id.depok.depoksinglewindow.ui.pdam.registerpelangganlamadanbaru

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.data.RegisterPelangganMenuPDAMType
import id.depok.depoksinglewindow.data.api.ApiSettings
import id.depok.depoksinglewindow.databinding.ActivityRegisterpdamsubmenuBinding
import id.depok.depoksinglewindow.ui.BaseActivity
import id.depok.depoksinglewindow.ui.shared.Arguments
import id.depok.depoksinglewindow.ui.shared.WebPageActivity
import org.koin.android.ext.android.inject

class RegisterPelangganLamadanBaruActivity: BaseActivity<RegisterPelangganLamadanBaruInterface.Presenter>(),
        RegisterPelangganLamadanBaruInterface.View {

    override val presenter by inject<RegisterPelangganLamadanBaruInterface.Presenter>()
    lateinit var binding: ActivityRegisterpdamsubmenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registerpdamsubmenu)

        initialize()
    }

    override fun showRegisterPDAMPelangganBaru() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.registerpdam_pelangganbaru))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_LAYANAN_REGISTER_BARU)
        startActivity(intent)

    }


    override fun showRegisterPDAMPelangganLama() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.registerpdam_pelangganlama))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_LAYANAN_REGISTER_LAMA)
        startActivity(intent)
    }



    private fun initialize() {
        setSupportActionBar(binding.toolbarRegpdam)

        binding.apply {
            toolbarRegpdam.setNavigationOnClickListener {finish()}

            viewRegpdamPelangganbaruoverlay.setOnClickListener {
                presenter.onMenuSelected(RegisterPelangganMenuPDAMType.REGISTER_PELANGGAN_BARU_PDAM)
            }
            viewRegpdamPelangganlamaoverlay.setOnClickListener {
                presenter.onMenuSelected(RegisterPelangganMenuPDAMType.REGISTER_PELANGGAN_LAMA_PDAM)
            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        presenter.view = this
        presenter.start()
    }
}