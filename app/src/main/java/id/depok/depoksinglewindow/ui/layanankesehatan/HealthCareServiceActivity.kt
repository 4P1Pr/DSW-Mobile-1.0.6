package id.depok.depoksinglewindow.ui.layanankesehatan

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.widget.Toast
import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.data.LayananKesehatanMenuType
import id.depok.depoksinglewindow.data.api.ApiSettings
import id.depok.depoksinglewindow.databinding.ActivityHealthcareserviceBinding
import id.depok.depoksinglewindow.ui.BaseActivity
import id.depok.depoksinglewindow.ui.shared.Arguments
import id.depok.depoksinglewindow.ui.shared.WebPageActivity
import org.koin.android.ext.android.inject

class HealthCareServiceActivity : BaseActivity<HealthCareServiceContract.Presenter>(),
    HealthCareServiceContract.View{

    override val presenter by inject<HealthCareServiceContract.Presenter>()
    lateinit var binding: ActivityHealthcareserviceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_healthcareservice)

        initialize()
    }


    override fun showHealthCareServiceRegistration() {
        Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show()
    }

    override fun showHealthCareServiceInformation() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.layanankesehatan_healthcareserviceinformation))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_INFORMASI_LAYANAN_KESEHATAN)
        startActivity(intent)
    }

    override fun showHealthCareServiceHealthNews() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.layanankesehatan_healtnews))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_BERITA_KESEHATAN)
        startActivity(intent)
    }

    private fun initialize() {
        setSupportActionBar(binding.toolbarLayanankesehatan)

        binding.apply {
            toolbarLayanankesehatan.setNavigationOnClickListener{ finish() }

            viewLayanankesehatanHealthcareserviceregisoverlay.setOnClickListener {
                presenter.onMenuSelected(LayananKesehatanMenuType.HEALTHCARE_SERVICE_REGISTRATION)
            }

            viewLayanankesehatanHealthcareserviceinfooverlay.setOnClickListener {
                presenter.onMenuSelected(LayananKesehatanMenuType.HEALTHCARE_SERVICE_INFORMATION)
            }

            viewLayanankesehatanHealthcareservicehealthnewsoverlay.setOnClickListener {
                presenter.onMenuSelected(LayananKesehatanMenuType.HEALTHCARE_SERVICE_HEALTH_NEWS)
            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        presenter.view = this
        presenter.start()
    }
}
