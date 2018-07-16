package id.depok.depoksinglewindow.ui.pln

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.data.api.ApiSettings
import id.depok.depoksinglewindow.databinding.ActivityPlnBinding
import id.depok.depoksinglewindow.ui.BaseActivity
import id.depok.depoksinglewindow.ui.shared.Arguments
import id.depok.depoksinglewindow.ui.shared.WebPageActivity
import org.koin.android.ext.android.inject


class PlnActivity : BaseActivity<PlnContract.Presenter>(), PlnContract.View {

    override val presenter by inject<PlnContract.Presenter>()
    lateinit var binding : ActivityPlnBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_pln)

        initialize()
    }

    override fun showBayarPLN() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.pembayaranpln))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_BAYAR_ZAKAT)
        startActivity(intent)
    }

    override fun showInfoPLN() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.infopln))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_INFO_ZAKAT)
        startActivity(intent)
    }

    private fun initialize() {
        setSupportActionBar(binding.toolbarPln)

        binding.apply {
            toolbarPln.setNavigationOnClickListener{ finish() }

            viewPlnBayarplnoverlay.setOnClickListener { presenter.onPressBayarPLN() }
            viewPlnInfoplnoverlay.setOnClickListener { presenter.onPressInfoPLN() }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        presenter.view = this
        presenter.start()
    }
}
