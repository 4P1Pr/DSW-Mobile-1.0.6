package id.depok.depoksinglewindow.ui.zakat

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.data.api.ApiSettings
import id.depok.depoksinglewindow.databinding.ActivityZakatBinding
import id.depok.depoksinglewindow.ui.BaseActivity
import id.depok.depoksinglewindow.ui.shared.Arguments
import id.depok.depoksinglewindow.ui.shared.WebPageActivity
import org.koin.android.ext.android.inject


class ZakatActivity : BaseActivity<ZakatContract.Presenter>(), ZakatContract.View {

    override val presenter by inject<ZakatContract.Presenter>()
    lateinit var binding: ActivityZakatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_zakat)

        initialize()
    }

    override fun showBayarZakat() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.layananzakat_pembayaranzakat))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_BAYAR_ZAKAT)
        startActivity(intent)
    }

    override fun showInfoZakat() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.layananzakat_infopembayaranzakat))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_INFO_ZAKAT)
        startActivity(intent)
    }

    private fun initialize() {
        setSupportActionBar(binding.toolbarZakat)

        binding.apply {
            toolbarZakat.setNavigationOnClickListener{ finish() }

            viewZakatBayarzakatoverlay.setOnClickListener { presenter.onPressBayarZakat() }
            viewZakatInfozakatoverlay.setOnClickListener { presenter.onPressInfoZakat() }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        presenter.view = this
        presenter.start()
    }
}
