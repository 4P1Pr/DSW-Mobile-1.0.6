package id.depok.depoksinglewindow.ui.settings

import android.content.DialogInterface
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.data.SettingsMenuType
import id.depok.depoksinglewindow.databinding.ActivitySettingsBinding
import id.depok.depoksinglewindow.ui.BaseActivity
import id.depok.depoksinglewindow.ui.editpassword.EditPasswordActivity
import id.depok.depoksinglewindow.ui.onboarding.OnBoardingActivity
import id.depok.depoksinglewindow.ui.profile.ProfileActivity
import id.depok.depoksinglewindow.util.ui.DialogUtil
import org.koin.android.ext.android.inject

class SettingsActivity : BaseActivity<SettingsContract.Presenter>(), SettingsContract.View {

    override val presenter by inject<SettingsContract.Presenter>()
    lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_settings)

        initialize()
    }

    override fun showProfile() {
        startActivity(Intent(this, ProfileActivity::class.java))
    }

    override fun showEditPassword() {
        startActivity(Intent(this, EditPasswordActivity::class.java))
    }

    override fun showAbout() {

    }

    override fun showDevTeam() {

    }

    override fun showLogoutConfirmation() {
        DialogUtil().createPositiveNegativeDialog(this, R.string.settings_logoutconfirmation,
                DialogInterface.OnClickListener { _, _ -> presenter.onLogOutConfirmed() }, null).show()
    }

    override fun initMenu(isLogin: Boolean) {
        if (!isLogin) {
            // hide account & logout
            binding.apply {
                textviewSettingsAccountheader.visibility = View.GONE
                viewSettingsSeeprofile.visibility = View.GONE
                viewSettingsSeeprofileoverlay.visibility = View.GONE
                textviewSettingsSeeprofile.visibility = View.GONE
                imageviewSettingsChevronprofile.visibility = View.GONE
                viewSettingsEditpassword.visibility = View.GONE
                viewSettingsEditpasswordoverlay.visibility = View.GONE
                textviewSettingsEditpassword.visibility = View.GONE
                imageviewSettingsChevroneditpassword.visibility = View.GONE
                viewSettingsLogout.visibility = View.GONE
                viewSettingsLogoutoverlay.visibility = View.GONE
                textviewSettingsLogout.visibility = View.GONE
                imageviewSettingsChevronlogout.visibility = View.GONE
            }
        }
    }

    override fun showOnBoarding() {
        finish()
        val intent = Intent(this, OnBoardingActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun initialize() {
        setSupportActionBar(binding.toolbarSettings)

        binding.apply {
            toolbarSettings.setNavigationOnClickListener{ finish() }

            viewSettingsSeeprofileoverlay.setOnClickListener { presenter.onPressMenu(SettingsMenuType.PROFILE) }
            viewSettingsEditpasswordoverlay.setOnClickListener { presenter.onPressMenu(SettingsMenuType.EDIT_PASSWORD) }
            viewSettingsAboutappoverlay.setOnClickListener { presenter.onPressMenu(SettingsMenuType.ABOUT) }
            viewSettingsAboutdevoverlay.setOnClickListener { presenter.onPressMenu(SettingsMenuType.DEV_TEAM) }
            viewSettingsLogoutoverlay.setOnClickListener { presenter.onPressMenu(SettingsMenuType.LOGOUT) }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        presenter.view = this
        presenter.start()
    }
}
