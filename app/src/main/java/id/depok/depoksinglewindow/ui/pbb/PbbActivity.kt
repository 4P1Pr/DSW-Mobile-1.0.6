package id.depok.depoksinglewindow.ui.pbb

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.data.CekPBBMenuType
import id.depok.depoksinglewindow.data.api.ApiSettings
import id.depok.depoksinglewindow.databinding.ActivityCekpbbBinding
import id.depok.depoksinglewindow.ui.BaseActivity
import id.depok.depoksinglewindow.ui.shared.Arguments.ARG_TITLE
import id.depok.depoksinglewindow.ui.shared.Arguments.ARG_URL
import id.depok.depoksinglewindow.ui.shared.WebPageActivity
import org.koin.android.ext.android.inject

class PbbActivity : BaseActivity<PbbContract.Presenter>(), PbbContract.View {

    override val presenter by inject<PbbContract.Presenter>()
    lateinit var binding: ActivityCekpbbBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cekpbb)

        initialize()
    }

    override fun showTagihanPBB() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(ARG_TITLE, getString(R.string.cekpbb_tagihanpbb))
        intent.putExtra(ARG_URL, ApiSettings.URL_TAGIHAN_PBB)
        startActivity(intent)
    }

    override fun showInformasiPBB() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(ARG_TITLE, getString(R.string.cekpbb_informasipbb))
        intent.putExtra(ARG_URL, ApiSettings.URL_INFORMASI_PBB)
        startActivity(intent)
    }

    override fun showInfoPembayaranPBB() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(ARG_TITLE, getString(R.string.cekpbb_infopembayaranpbb))
        intent.putExtra(ARG_URL, ApiSettings.URL_INFO_PEMBAYARAN_PBB)
        startActivity(intent)
    }

    private fun initialize() {
        setSupportActionBar(binding.toolbarCekpbb)

        binding.apply {
            toolbarCekpbb.setNavigationOnClickListener{ finish() }
            viewCekpbbTagihanpbboverlay.setOnClickListener {
                presenter.onPBBMenuClicked(CekPBBMenuType.TAGIHAN_PBB)
            }
            viewCekpbbInformasipbboverlay.setOnClickListener {
                presenter.onPBBMenuClicked(CekPBBMenuType.INFORMASI_PBB)
            }
            viewCekpbbInfopembayaranpbboverlay.setOnClickListener {
                presenter.onPBBMenuClicked(CekPBBMenuType.INFO_PEMBAYARAN_PBB)
            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        presenter.view = this
        presenter.start()
    }
}
