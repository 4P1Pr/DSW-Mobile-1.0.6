package id.depok.depoksinglewindow.ui.pdamdanpln

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.data.PLNdanPDAMMenuType
import id.depok.depoksinglewindow.data.api.ApiSettings
import id.depok.depoksinglewindow.databinding.ActivityPdamDanPlnBinding
import id.depok.depoksinglewindow.ui.BaseActivity
import id.depok.depoksinglewindow.ui.pdam.PdamActivity
import id.depok.depoksinglewindow.ui.shared.Arguments
import id.depok.depoksinglewindow.ui.shared.WebPageActivity
import org.koin.android.ext.android.inject


class PdamdanPlnActivity : BaseActivity<PdamdanPlnContract.Presenter>(), PdamdanPlnContract.View {

    override val presenter by inject<PdamdanPlnContract.Presenter>()
    lateinit var binding : ActivityPdamDanPlnBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_pdam_dan_pln)

        initialize()
    }

    override fun showPDAM() {
        startActivity(Intent(this, PdamActivity::class.java))
    }

    override fun showPLN() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.pln))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_PLN)
        startActivity(intent)
    }

    private fun initialize() {
        setSupportActionBar(binding.toolbarPlndanpdam)

        binding.apply {
            toolbarPlndanpdam.setNavigationOnClickListener{ finish() }

            viewPdamoverlay.setOnClickListener {
                presenter.onMenuSelected(PLNdanPDAMMenuType.PDAM) }
            viewPlnoverlay.setOnClickListener {
                presenter.onMenuSelected(PLNdanPDAMMenuType.PLN) }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        presenter.view = this
        presenter.start()
    }
}
