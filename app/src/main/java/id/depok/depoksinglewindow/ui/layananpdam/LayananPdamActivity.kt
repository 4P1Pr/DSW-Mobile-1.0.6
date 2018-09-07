package id.depok.depoksinglewindow.ui.layananpdam

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.data.InfoLayananPDAM
import id.depok.depoksinglewindow.data.api.ApiSettings
import id.depok.depoksinglewindow.databinding.ActivityLayananPdamBinding
import id.depok.depoksinglewindow.ui.BaseActivity
import id.depok.depoksinglewindow.ui.shared.Arguments
import id.depok.depoksinglewindow.ui.shared.WebPageActivity
import org.koin.android.ext.android.inject

class LayananPdamActivity: BaseActivity<LayananPdamContract.Presenter>(),
        LayananPdamContract.View {

    override val presenter by inject<LayananPdamContract.Presenter>()
    lateinit var binding: ActivityLayananPdamBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_layanan_pdam)

        initialize()
    }

    override fun showHakdanKewajibanPelangganPDAM() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.hakdankewajiban))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_INFORMASI_HAK_DAN_KEWAJIBAN_PELANGGAN_PDAM)
        startActivity(intent)
    }

    override fun showTipsHematAirPDAM() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.tipshemat))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_INFORMASI_TIPS_PDAM)
        startActivity(intent)
    }

    override fun showKelompokTarifPDAM() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.kelompoktarif))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_INFORMASI_TARIF_LENGKAP_PDAM)
        startActivity(intent)
    }

    override fun showKeluhanPelanggan() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.keluhanpelanggan))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_KELUHAN_PELANGGAN_PDAM)
        startActivity(intent)
    }

    override fun showKeluhanNonPelanggan() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.keluhannonpelanggan))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_KELUHAN_NON_PELANGGAN_PDAM)
        startActivity(intent)
    }



    private fun initialize() {
        setSupportActionBar(binding.toolbarPdam)

        binding.apply {
            toolbarPdam.setNavigationOnClickListener { finish() }

            viewPdamHakKewajibanoverlay.setOnClickListener {
                presenter.onMenuSelected(InfoLayananPDAM.HAK_DAN_KEWAJIBAN)
            }
            viewPdamKelompokTarifoverlay.setOnClickListener {
                presenter.onMenuSelected(InfoLayananPDAM.KELOMPOK_TARIF)
            }
            viewPdamTipsHematoverlay.setOnClickListener {
                presenter.onMenuSelected(InfoLayananPDAM.TIPS_HEMAT_AIR)
            }
            viewPdamKeluhanpelangganoverlay.setOnClickListener {
                presenter.onMenuSelected(InfoLayananPDAM.KELUHAN_PELANGGAN)
            }
            viewPdamKeluhannonpelangganoverlay.setOnClickListener {
                presenter.onMenuSelected(InfoLayananPDAM.KELUHAN_NON_PELANGGAN)
            }
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        presenter.view = this
        presenter.start()
    }
}