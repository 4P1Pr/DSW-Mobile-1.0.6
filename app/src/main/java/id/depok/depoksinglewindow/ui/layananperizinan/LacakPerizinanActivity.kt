package id.depok.depoksinglewindow.ui.layananperizinan

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.data.api.ApiSettings
import id.depok.depoksinglewindow.databinding.ActivityLacakperizinanBinding
import id.depok.depoksinglewindow.ui.BaseActivity
import id.depok.depoksinglewindow.ui.shared.Arguments
import id.depok.depoksinglewindow.ui.shared.WebPageActivity
import org.koin.android.ext.android.inject

class LacakPerizinanActivity : BaseActivity<LacakPerizinanContract.Presenter>(),
    LacakPerizinanContract.View{

    override val presenter by inject<LacakPerizinanContract.Presenter>()
    lateinit var binding: ActivityLacakperizinanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_lacakperizinan)

        initialize()
    }

    override fun back() {
        finish()
    }

    override fun search(token: String) {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.layananperizinan_lacakperizinan))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_LACAK_PERIZINAN)
        startActivity(intent)
    }

    private fun initialize() {
        setSupportActionBar(binding.toolbarLacakperizinan)

        binding.apply {
            toolbarLacakperizinan.setNavigationOnClickListener{ finish() }

            buttonLacakperizinanBackbutton.setOnClickListener{ presenter.onPressBack() }
            buttonLacakperizinanSearchbutton.setOnClickListener {
                presenter.onPressSearch(edittextLacakperizinanTokeninput.text.toString())
            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        presenter.view = this
        presenter.start()
    }
}
