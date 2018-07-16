package id.depok.depoksinglewindow.ui.onboarding

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.databinding.ActivityOnboardingBinding
import id.depok.depoksinglewindow.ui.BaseActivity
import id.depok.depoksinglewindow.ui.home.HomeActivity
import id.depok.depoksinglewindow.ui.login.LoginActivity
import org.koin.android.ext.android.inject

const val SLIDE_COUNT = 3
class OnBoardingActivity : BaseActivity<OnBoardingContract.Presenter>(), OnBoardingContract.View{

    override val presenter by inject<OnBoardingContract.Presenter>()

    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var mPagerAdapter: OnBoardingSlidePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding)

        initialize()
    }

    /*
     * View contract
     */
    override fun nextSlide() {
        if(binding.viewpagerOnboardingSlidepager.currentItem < SLIDE_COUNT - 1){
            binding.viewpagerOnboardingSlidepager.currentItem = binding
                                                                    .viewpagerOnboardingSlidepager
                                                                    .currentItem + 1
        } else{
            presenter.onPressSkip()
        }
    }

    override fun showLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    override fun showMainPage() {
        finish()
        startActivity(Intent(this, HomeActivity::class.java))
    }

    /*
     * End View contract
     */

    private inner class OnBoardingSlidePagerAdapter(fm: FragmentManager?) :
            FragmentStatePagerAdapter(fm) {


        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> OnBoardingSlidePageFragment.newInstance(getString(R.string.onboardingslidepage_title1),
                        getString(R.string.onboardingslidepage_body1),
                        R.drawable.get_started_1)
                1 -> OnBoardingSlidePageFragment.newInstance(getString(R.string.onboardingslidepage_title2),
                        getString(R.string.onboardingslidepage_body2),
                        R.drawable.get_started_2)
                2 -> OnBoardingSlidePageFragment.newInstance(getString(R.string.onboardingslidepage_title3),
                        getString(R.string.onboardingslidepage_body3),
                        R.drawable.get_started_3)
                else ->
                    OnBoardingSlidePageFragment.newInstance(getString(R.string.onboardingslidepage_title1),
                            getString(R.string.onboardingslidepage_body1),
                            R.drawable.get_started_1)
            }
        }

        override fun getCount(): Int {
            return SLIDE_COUNT
        }
    }

    private fun initialize() {
        // Init slide pager
        mPagerAdapter = OnBoardingSlidePagerAdapter(supportFragmentManager)

        binding.apply {
            viewpagerOnboardingSlidepager.adapter = mPagerAdapter
            viewpagerOnboardingSlidepager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
                override fun onPageScrollStateChanged(state: Int) {

                }

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

                }

                override fun onPageSelected(position: Int) {
                    if(position < SLIDE_COUNT - 1){
                        buttonOnboardingNextbutton.text = getString(R.string.onboarding_next)
                        buttonOnboardingSkipbutton.text = getString(R.string.onboarding_skip)

                        buttonOnboardingNextbutton.setOnClickListener { presenter.onPressNext() }
                    } else{
                        buttonOnboardingNextbutton.text = getString(R.string.onboarding_dologin)
                        buttonOnboardingSkipbutton.text = getString(R.string.onboarding_gotomain)

                        buttonOnboardingNextbutton.setOnClickListener { presenter.onPressLogin() }
                    }
                }
            })

            buttonOnboardingNextbutton.setOnClickListener { presenter.onPressNext() }
            buttonOnboardingSkipbutton.setOnClickListener { presenter.onPressSkip() }
        }

        presenter.view = this
        presenter.start()
    }
}
