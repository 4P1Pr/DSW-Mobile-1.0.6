package id.depok.depoksinglewindow.ui.layananperizinan

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.data.LayananPerizinanMenuType
import id.depok.depoksinglewindow.data.api.ApiSettings
import id.depok.depoksinglewindow.databinding.ActivityLayananperizinanBinding
import id.depok.depoksinglewindow.ui.BaseActivity
import id.depok.depoksinglewindow.ui.shared.Arguments
import id.depok.depoksinglewindow.ui.shared.WebPageActivity
import org.koin.android.ext.android.inject

class LayananPerizinanActivity : BaseActivity<LayananPerizinanContract.Presenter>(),
    LayananPerizinanContract.View{

    override val presenter by inject<LayananPerizinanContract.Presenter>()
    lateinit var binding: ActivityLayananperizinanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_layananperizinan)

        initialize()
    }

    override fun showLacakPerizinan() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.layananperizinan_lacakperizinan))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_LACAK_PERIZINAN)
        startActivity(intent)
    }

    override fun showInformasiPerizinan() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.layananperizinan_informasiperizinan))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_INFORMASI_PERIZINAN)
        startActivity(intent)
    }

    override fun showPerizinanOnline() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.layananperizinan_perizinanonline))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_PERIZINAN_ONLINE)
        startActivity(intent)
    }

    private fun initialize() {
        setSupportActionBar(binding.toolbarLayananperizinan)

        binding.apply {
            toolbarLayananperizinan.setNavigationOnClickListener{ finish() }

            viewLayananperizinanLacakperizinanoverlay.setOnClickListener {
                presenter.onMenuSelected(LayananPerizinanMenuType.LACAK_PERIZINAN)
            }

            viewLayananperizinanInformasiperizinanoverlay.setOnClickListener {
                presenter.onMenuSelected(LayananPerizinanMenuType.INFORMASI_PERIZINAN)
            }

            viewLayananperizinanPerizinanonlineoverlay.setOnClickListener {
                presenter.onMenuSelected(LayananPerizinanMenuType.PERIZINAN_ONLINE)
            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        presenter.view = this
        presenter.start()
    }
}
