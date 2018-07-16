package id.depok.depoksinglewindow.ui.layanankesehatan

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.data.api.ApiSettings
import id.depok.depoksinglewindow.databinding.ActivityOldpatientqueuepageBinding
import id.depok.depoksinglewindow.ui.BaseActivity
import id.depok.depoksinglewindow.ui.shared.Arguments.ARG_TITLE
import id.depok.depoksinglewindow.ui.shared.Arguments.ARG_URL
import id.depok.depoksinglewindow.ui.shared.WebPageActivity
import org.koin.android.ext.android.inject

class OldPatientQueuePageActivity : BaseActivity<OldPatientQueuePageContract.Presenter>(),
    OldPatientQueuePageContract.View{

    override val presenter by inject<OldPatientQueuePageContract.Presenter>()
    lateinit var binding: ActivityOldpatientqueuepageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_oldpatientqueuepage)

        initialize()
    }

    override fun back() {
        finish()
    }

    override fun search() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(ARG_TITLE, getString(R.string.layanankesehatan_oldpatien))
        intent.putExtra(ARG_URL, ApiSettings.URL_OLD_PATIENT_QUEUE)
        startActivity(intent)
    }

    private fun initialize() {
        setSupportActionBar(binding.toolbarOldpatientqueue)

        binding.apply {
            toolbarOldpatientqueue.setNavigationOnClickListener{ finish() }

            buttonOldpatientqueueBackbutton.setOnClickListener { presenter.onPressBack() }
            buttonOldpatientqueueSearchbutton.setOnClickListener { presenter.onPressSearch() }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        presenter.view = this
        presenter.start()
    }
}
