package id.depok.depoksinglewindow.ui.layananpendidikan

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.data.LayananPendidikanMenuType
import id.depok.depoksinglewindow.data.api.ApiSettings
import id.depok.depoksinglewindow.databinding.ActivityLayananpendidikanBinding
import id.depok.depoksinglewindow.ui.BaseActivity
import id.depok.depoksinglewindow.ui.shared.Arguments
import id.depok.depoksinglewindow.ui.shared.WebPageActivity
import org.koin.android.ext.android.inject
/**
 * Created by fazhal on 11/01/18.
 */


class LayananPendidikanActivity : BaseActivity<LayananPendidikanContract.Presenter>(),
        LayananPendidikanContract.View{

    override val presenter by inject<LayananPendidikanContract.Presenter>()
    lateinit var binding: ActivityLayananpendidikanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_layananpendidikan)

        initialize()
    }

    override fun showSchoolRegisterPage() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.layananpendidikan_schoolregister))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_SCHOOL_REGISTER)
        startActivity(intent)
    }

    override fun showPassingGradePage() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.layananpendidikan_passinggrade))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_PASSINGGRADE)
        startActivity(intent)
    }

    override fun showCalendarSchool() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.layananpendidikan_schoolcalendar))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_KALENDER_PENDIDIKAN)
        startActivity(intent)
    }

    override fun showPPDB() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.layananpendidikan_ppdb))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_PPDB)
        startActivity(intent)
    }

    private fun initialize() {
        setSupportActionBar(binding.toolbarLayananpendidikan)

        binding.apply {
            toolbarLayananpendidikan.setNavigationOnClickListener{ finish() }

            viewLayananpendidikanSchoolregisteroverlay.setOnClickListener {
                presenter.onMenuSelected(LayananPendidikanMenuType.DAFTAR_SEKOLAH)
            }

            viewLayananpendidikanPassinggradeoverlay.setOnClickListener {
                presenter.onMenuSelected(LayananPendidikanMenuType.NILAI_PASSINGGRADE)
            }

            viewLayananpendidikanKalendersekolahoverlay.setOnClickListener {
                presenter.onMenuSelected(LayananPendidikanMenuType.KALENDER_PENDIDIKAN)
            }

            viewLayananpendidikanPpdboverlay.setOnClickListener {
                presenter.onMenuSelected(LayananPendidikanMenuType.PPDB)
            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        presenter.view = this
        presenter.start()
    }
}
