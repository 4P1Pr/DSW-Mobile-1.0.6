package id.depok.depoksinglewindow.ui.splash

import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.databinding.ActivitySplashBinding
import id.depok.depoksinglewindow.ui.BaseActivity
import id.depok.depoksinglewindow.ui.home.HomeActivity
import id.depok.depoksinglewindow.ui.onboarding.OnBoardingActivity
import org.koin.android.ext.android.inject

class SplashActivity : BaseActivity<SplashContract.Presenter>(), SplashContract.View {

    override val presenter by inject<SplashContract.Presenter>()
    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        setAsFullScreen()

        presenter.view = this
        presenter.start()
    }

    override fun onBackPressed() {
        // do nothing when back button pressed
    }

    override fun showOnBoarding() {
        finish()
        startActivity(Intent(this, OnBoardingActivity::class.java))
    }

    override fun showMainPage() {
        finish()
        startActivity(Intent(this, HomeActivity::class.java))
    }

    private fun setAsFullScreen() {
        // set status bar flag
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        // set transparent status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.TRANSPARENT
        }
    }
}
