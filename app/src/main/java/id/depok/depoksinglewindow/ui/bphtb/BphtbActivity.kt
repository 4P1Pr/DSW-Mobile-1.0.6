package id.depok.depoksinglewindow.ui.bphtb

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.data.api.ApiSettings
import id.depok.depoksinglewindow.databinding.ActivityBphtbBinding
import id.depok.depoksinglewindow.ui.BaseActivity
import id.depok.depoksinglewindow.ui.shared.Arguments
import id.depok.depoksinglewindow.ui.shared.WebPageActivity
import org.koin.android.ext.android.inject

/**
 * Created by PiNGUiN on 2018-01-08.
 */
class BphtbActivity: BaseActivity<BphtbContract.Presenter>(), BphtbContract.View {

    override val presenter by inject<BphtbContract.Presenter>()
    lateinit var binding: ActivityBphtbBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bphtb)

        initialize()
    }

    override fun showCheckBphtb() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.cekbphtb_cekbphtb))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_CHECK_BPHTB)
        startActivity(intent)
    }

    override fun showInfoBphtb() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.cekbphtb_infobphtb))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_INFO_BPHTB)
        startActivity(intent)
    }

    override fun showInfoPembayaranBphtb() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.cekbphtb_infopembayaranbphtb))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_INFO_PEMBAYARAN_BPHTB)
        startActivity(intent)
    }

    private fun initialize() {
        setSupportActionBar(binding.toolbarBphtb)

        binding.apply {
            toolbarBphtb.setNavigationOnClickListener{ finish() }

            viewBphtbCheckbphtboverlay.setOnClickListener { presenter.onPressCheckBphtb() }
            viewBphtbInfobphtboverlay.setOnClickListener { presenter.onPressInfoBphtb() }
            viewBphtbInfopembayaranbphtboverlay.setOnClickListener { presenter.onPressInfoPembayaranBphtb() }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        presenter.view = this
        presenter.start()
    }
}