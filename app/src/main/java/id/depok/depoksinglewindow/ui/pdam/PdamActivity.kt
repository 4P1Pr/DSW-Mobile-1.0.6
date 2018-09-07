package id.depok.depoksinglewindow.ui.pdam

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.data.MenuPDAM
import id.depok.depoksinglewindow.data.api.ApiSettings
import id.depok.depoksinglewindow.databinding.ActivityPdamBinding
import id.depok.depoksinglewindow.ui.BaseActivity
import id.depok.depoksinglewindow.ui.layananpdam.LayananPdamActivity
import id.depok.depoksinglewindow.ui.pdam.registerpelangganlamadanbaru.RegisterPelangganLamadanBaruActivity
import id.depok.depoksinglewindow.ui.pembayaranpdam.PembayaranPdamActivity
import id.depok.depoksinglewindow.ui.shared.Arguments
import id.depok.depoksinglewindow.ui.shared.WebPageActivity
import org.koin.android.ext.android.inject

class PdamActivity: BaseActivity<PdamContract.Presenter>(),
        PdamContract.View {

    override val presenter by inject<PdamContract.Presenter>()
    lateinit var binding: ActivityPdamBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pdam)

        initialize()
    }

    override fun showSimulasiTagihan() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.simulasi))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_SIMULASI_TAGIHAN_PDAM)
        startActivity(intent)
    }

    override fun showInfoPembayaran() {
        startActivity(Intent(this, PembayaranPdamActivity::class.java))
    }

    override fun showCekTagihanPDAM() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.tagihanpdam))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_CEK_TAGIHAN_PDAM)
        startActivity(intent)
    }

    override fun showJaringanLayananPDAM() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.jaringanlayananpdam))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_JARINGAN_LAYANAN_PDAM)
        startActivity(intent)
    }

    override fun showInfoLayananPDAM() {
        startActivity(Intent(this, LayananPdamActivity::class.java))
    }

    override fun showRegisterPDAM() {
        startActivity(Intent(this, RegisterPelangganLamadanBaruActivity::class.java))
//        Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show()
    }

    private fun initialize() {
        setSupportActionBar(binding.toolbarPdam)

        binding.apply {
            toolbarPdam.setNavigationOnClickListener {finish()}

            viewPdamRegisteroverlay.setOnClickListener {
                presenter.onMenuSelected(MenuPDAM.REGISTRASI_PDAM)
            }
            viewPdamCektagihanoverlay.setOnClickListener {
                presenter.onMenuSelected(MenuPDAM.CEK_TAGIHAN_PDAM)
            }
            viewPdamInfopdamoverlay.setOnClickListener {
                presenter.onMenuSelected(MenuPDAM.INFO_LAYANAN_PDAM)
            }
            viewPdamInfopembayaranoverlay.setOnClickListener {
                presenter.onMenuSelected(MenuPDAM.INFO_PEMBAYARAN)
            }
            viewPdamJaringanLayananoverlay.setOnClickListener {
                presenter.onMenuSelected(MenuPDAM.JARINGAN_LAYANAN_PDAM)
            }
            viewPdamSimulasitagihanoverlay.setOnClickListener {
                presenter.onMenuSelected(MenuPDAM.SIMULASI_TAGIHAN)
            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        presenter.view = this
        presenter.start()
    }
}