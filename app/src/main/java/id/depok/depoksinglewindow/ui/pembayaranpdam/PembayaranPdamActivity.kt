package id.depok.depoksinglewindow.ui.pembayaranpdam

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.data.BankMenuType
import id.depok.depoksinglewindow.data.api.ApiSettings
import id.depok.depoksinglewindow.databinding.ActivityPembayaranPdamBinding
import id.depok.depoksinglewindow.ui.BaseActivity
import id.depok.depoksinglewindow.ui.shared.Arguments
import id.depok.depoksinglewindow.ui.shared.WebPageActivity
import org.koin.android.ext.android.inject

class PembayaranPdamActivity: BaseActivity<PembayaranPdamContract.Presenter>(),
        PembayaranPdamContract.View {

    override val presenter by inject<PembayaranPdamContract.Presenter>()
    lateinit var binding: ActivityPembayaranPdamBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pembayaran_pdam)

        initialize()
    }

    override fun showInfoMandiri() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.mandiri))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_INFO_ATM_MANDIRI_PDAM)
        startActivity(intent)
    }

    override fun showInfoBNI() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.bni))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_INFO_ATM_BNI_PDAM)
        startActivity(intent)
    }

    override fun showPPOBdanBankLain() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.ppobdanbanklain))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_INFO_BANK_DAN_PPOB_PDAM)
        startActivity(intent)
    }

    override fun showInfoBCA() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.bca))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_INFO_ATM_BCA_PDAM)
        startActivity(intent)
    }

    private fun initialize() {
        setSupportActionBar(binding.toolbarPembayaranPdam)

        binding.apply{
            toolbarPembayaranPdam.setNavigationOnClickListener { finish() }

            viewPdamBnioverlay.setOnClickListener {
                presenter.onMenuSelected(BankMenuType.BNI)
            }
            viewPdamMandirioverlay.setOnClickListener {
                presenter.onMenuSelected(BankMenuType.MANDIRI)
            }
            viewPdamBcaoverlay.setOnClickListener {
                presenter.onMenuSelected(BankMenuType.BCA)
            }
            viewPdamPpoboverlay.setOnClickListener {
                presenter.onMenuSelected(BankMenuType.PPOB_DAN_BANK_LAIN)
            }
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        presenter.view = this
        presenter.start()
    }
}
