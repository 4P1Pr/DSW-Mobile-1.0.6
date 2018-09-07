package id.depok.depoksinglewindow.ui.callcenter

import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.data.CallCenterMenuType
import id.depok.depoksinglewindow.data.api.ApiSettings
import id.depok.depoksinglewindow.databinding.ActivityCallCenterBinding
import id.depok.depoksinglewindow.ui.BaseActivity
import id.depok.depoksinglewindow.ui.shared.Arguments
import id.depok.depoksinglewindow.ui.shared.WebPageActivity
import org.koin.android.ext.android.inject


private const val CONTACT_CENTER_NUMBER = "08111232222"
class CallCenterMenuActivity: BaseActivity<CallCenterMenuContract.Presenter>(),
        CallCenterMenuContract.View{

    override val presenter by inject<CallCenterMenuContract.Presenter>()
    lateinit var binding: ActivityCallCenterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_call_center)

        initialize()
    }

    override fun showCallCenterOPD() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.layanancallcenter_contactopd))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_CONTACT_CENTER_OPD)
        startActivity(intent)
    }

    override fun showCallCenterDepok() {
        showTelephone(CONTACT_CENTER_NUMBER)
    }

    override fun showTelephone(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phoneNumber")
        startActivity(intent)
    }

    private fun initialize() {
        setSupportActionBar(binding.toolbarCallcenter)

        binding.apply {
            toolbarCallcenter.setNavigationOnClickListener { finish() }

            viewCallcenterCalldepokoverlay.setOnClickListener {
                presenter.onMenuSelected(CallCenterMenuType.CALL_CENTER_DEPOK)
            }
            viewCallcenterCallopdoverlay.setOnClickListener {
                presenter.onMenuSelected(CallCenterMenuType.CALL_CENTER_OPD)
            }
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        presenter.view = this
        presenter.start()
    }
}