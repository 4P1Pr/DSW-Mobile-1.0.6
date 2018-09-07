package id.depok.depoksinglewindow.ui.home

import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.GridLayout.VERTICAL
import com.daimajia.slider.library.SliderTypes.DefaultSliderView
import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.data.AppMenu
import id.depok.depoksinglewindow.data.AppMenuType
import id.depok.depoksinglewindow.data.ImageData
import id.depok.depoksinglewindow.data.PrayTime
import id.depok.depoksinglewindow.data.api.ApiSettings
import id.depok.depoksinglewindow.databinding.ActivityHomeBinding
import id.depok.depoksinglewindow.databinding.DialogEmergencycallBinding
import id.depok.depoksinglewindow.ui.BaseActivity
import id.depok.depoksinglewindow.ui.aspiration.ARG_IS_COMPLAINT
import id.depok.depoksinglewindow.ui.aspiration.AspirationListActivity
import id.depok.depoksinglewindow.ui.bphtb.BphtbActivity
import id.depok.depoksinglewindow.ui.callcenter.CallCenterMenuActivity
import id.depok.depoksinglewindow.ui.layanankesehatan.HealthCareServiceActivity
import id.depok.depoksinglewindow.ui.layananpendidikan.LayananPendidikanActivity
import id.depok.depoksinglewindow.ui.layananperizinan.LayananPerizinanActivity
import id.depok.depoksinglewindow.ui.login.LoginActivity
import id.depok.depoksinglewindow.ui.pbb.PbbActivity
import id.depok.depoksinglewindow.ui.pdamdanpln.PdamdanPlnActivity
import id.depok.depoksinglewindow.ui.settings.SettingsActivity
import id.depok.depoksinglewindow.ui.shared.Arguments
import id.depok.depoksinglewindow.ui.shared.WebPageActivity
import id.depok.depoksinglewindow.ui.sigap.loginsigap.SigapLoginActivity
import id.depok.depoksinglewindow.ui.zakat.ZakatActivity
import id.depok.depoksinglewindow.util.DateFormat.FULL_DAY_DATE_MONTH_YEAR
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


private const val SLIDER_AUTO_CYCLE_DELAY: Long = 1000
private const val SLIDER_AUTO_CYCLE_DURATION: Long = 5000
class HomeActivity : BaseActivity<HomeContract.Presenter>(), HomeContract.View {

    override val presenter by inject<HomeContract.Presenter>()
    lateinit var binding: ActivityHomeBinding

    //var pagerAdapter:CostompagerAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        val permissions = arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.CAMERA)
        ActivityCompat.requestPermissions(this, permissions,0)

        //pagerAdapter = CostompagerAdapter(supportFragmentManager)
        //pagerAdapter!!.addFragments(InformasiFragment(),"Informasi")
        //pagerAdapter!!.addFragments(PelayananFragment(),"Pelayanan")

        //costomViewPager.adapter = pagerAdapter
        //costomTabLayout.setupWithViewPager(costomViewPager)
        initialize()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.settings_menu -> {
                presenter.onMenuClicked(AppMenuType.PENGATURAN)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun initialize() {
        binding.apply {
            setSupportActionBar(toolbarHome)

            val menus = ArrayList<AppMenu>()
            menus.add(AppMenu(AppMenuType.PANGGILAN_DARURAT, R.drawable.icon_lineblue_emergency,
                    R.string.all_menuPanggilanDarurat))
            menus.add(AppMenu(AppMenuType.CEK_PBB, R.drawable.icon_lineblue_pbb,
                    R.string.all_menuPBB))
            menus.add(AppMenu(AppMenuType.CEK_BPHTB, R.drawable.icon_lineblue_bphtb,
                    R.string.all_menuBPHTB))
            menus.add(AppMenu(AppMenuType.LAYANAN_PERIZINAN, R.drawable.icon_lineblue_izin,
                    R.string.all_menuLayananPerizinan))
            menus.add(AppMenu(AppMenuType.LAYANAN_KESEHATAN, R.drawable.icon_lineblue_puskesmas,
                    R.string.all_menuLayananKesehatan))
            menus.add(AppMenu(AppMenuType.PERTANYAAN_DAN_ASPIRASI, R.drawable.icon_lineblue_aspirasi,
                    R.string.all_menuPertanyaanDanAspirasi))
            menus.add(AppMenu(AppMenuType.INFO_CUACA, R.drawable.icon_cuaca,
                    R.string.all_menuCuacaDepok))
            menus.add(AppMenu(AppMenuType.CONTACT_CENTER, R.drawable.icon_lineblue_contact,
                    R.string.all_menuContactCenter))
            menus.add(AppMenu(AppMenuType.LAYANAN_PENDIDIKAN, R.drawable.icon_edu,
                    R.string.all_menuLayananPendidikan))
            menus.add(AppMenu(AppMenuType.LAYANAN_ZAKAT, R.drawable.icon_zakat,
                    R.string.all_menuLayananZakat))
            menus.add(AppMenu(AppMenuType.PETA_DEPOK, R.drawable.icon_maps,
                    R.string.all_menuPetaDepok))
            menus.add(AppMenu(AppMenuType.BERITA_DEPOK, R.drawable.icon_rss,
                    R.string.all_menuLayananRSS))
            menus.add(AppMenu(AppMenuType.INFO_KEMACETAN, R.drawable.icon_kemacetan,
                    R.string.all_menuInfoKemacetan))
            menus.add(AppMenu(AppMenuType.INFO_LOWONGAN_KERJA, R.drawable.icon_work,
                    R.string.all_menuLowonganKerja))
            menus.add(AppMenu(AppMenuType.PLN_DAN_PDAM, R.drawable.lineblue_plnpdam,
                    R.string.all_menuPLNdanPDAM))
            /*menus.add(AppMenu(AppMenuType.PENGADUAN, R.drawable.icon_lineblue_pengaduan,
                    R.string.all_menuPengaduan))*/


            textviewHomeSubuhvalue.text = "-"
            textviewHomeDzuhurvalue.text = "-"
            textviewHomeAsharvalue.text = "-"
            textviewHomeMaghribvalue.text = "-"
            textviewHomeIsyavalue.text = "-"

            val menuAdapter = MenuRecyclerViewAdapter(this@HomeActivity, menus,
                    object: MenuRecyclerViewAdapter.MenuCallback{
                override fun onClick(appMenu: AppMenu) {
                    presenter.onMenuClicked(appMenu.typeApp)
                }
            })

            recyclerviewHomeHomemenu.layoutManager = GridLayoutManager(this@HomeActivity, 3,
                    VERTICAL, false)
            recyclerviewHomeHomemenu.adapter = menuAdapter

            Handler().postDelayed({ scrollviewHome.scrollTo(0, 0) }, 1000)
        }

        presenter.view = this
        presenter.start()
    }

    override fun showPanggilanDarurat() {
        val inflater = layoutInflater
        val dialogBinding : DialogEmergencycallBinding = DataBindingUtil.inflate(inflater,
                R.layout.dialog_emergencycall, null, false)


        val dialog = AlertDialog.Builder(this)
                .setView(dialogBinding.root)
                .setCancelable(true)
                .create()

        dialogBinding.apply {
            viewEmergencydialogEmergencycalloverlay.setOnClickListener {
                presenter.onContactTapped(textviewEmergencydialogEmergencycallnumber.text.toString())
            }
            viewEmergencydialogPoliceoverlay.setOnClickListener{
                presenter.onContactTapped(textviewEmergencydialogPolicenumber.text.toString())
            }
            viewEmergencydialogAmbulanceoverlay.setOnClickListener{
                presenter.onContactTapped(textviewEmergencydialogAmbulancenumber.text.toString())
            }
            viewEmergencydialogFirefighteroverlay.setOnClickListener{
                presenter.onContactTapped(textviewEmergencydialogFirefighternumber.text.toString())
            }
            viewEmergencydialogPlnoverlay.setOnClickListener{
                presenter.onContactTapped(textviewEmergencydialogPlnnumber.text.toString())
            }
            buttonEmergencycallBackbutton.setOnClickListener { dialog.dismiss() }
        }

        dialog.show()
    }

    override fun showPBB() {
        startActivity(Intent(this, PbbActivity::class.java))
    }

    override fun showBHTP() {
        startActivity(Intent(this, BphtbActivity::class.java))
    }

    override fun showLayananPerizinan() {
        startActivity(Intent(this, LayananPerizinanActivity::class.java))
    }

    override fun showHealthCareService() {
        startActivity(Intent(this, HealthCareServiceActivity::class.java))
    }

    override fun showLayananPendidikan() {
        startActivity(Intent(this, LayananPendidikanActivity::class.java))
    }

    override fun showQuestionAndAspiration() {
        val intent = Intent(this, AspirationListActivity::class.java)
        intent.putExtra(ARG_IS_COMPLAINT, true)
        startActivity(intent)
    }

    override fun showContactCenter() {
        startActivity(Intent(this, CallCenterMenuActivity::class.java))
    }

    override fun showBeritaDepok() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.all_menuLayananRSS))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_BERITA)
        startActivity(intent)
    }

    override fun showPetaDepok() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.all_menuPetaDepok))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_PETA_DEPOK)
        startActivity(intent)
    }


    override fun showLayananZakat() {
        startActivity(Intent(this, ZakatActivity::class.java))
    }

    override fun showInfoCuaca() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.all_menuCuacaDepok))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_INFO_CUACA)
        startActivity(intent)
    }

    override fun showInfoKemacetan() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.all_menuInfoKemacetan))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_INFO_KEMACETAN)
        startActivity(intent)
    }

    override fun showInfoLowonganKerja() {
        val intent = Intent(this, WebPageActivity::class.java)
        intent.putExtra(Arguments.ARG_TITLE, getString(R.string.all_menuLowonganKerja))
        intent.putExtra(Arguments.ARG_URL, ApiSettings.URL_INFO_LOWONGAN_KERJA)
        startActivity(intent)
    }

    override fun showPLNdanPDAM() {
        startActivity(Intent(this, PdamdanPlnActivity::class.java))
    }

    override fun showPengaduan() {
        startActivity(Intent(this, SigapLoginActivity::class.java))
    }

    override fun showPengaturan() {
        startActivity(Intent(this, SettingsActivity::class.java))
    }

    override fun showLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    override fun loadPrayTime(prayTime: PrayTime, date: Date?) {
        binding.apply {
            textviewHomeSubuhvalue.text = prayTime.fajr
            textviewHomeDzuhurvalue.text = prayTime.dhuhr
            textviewHomeAsharvalue.text = prayTime.asr
            textviewHomeMaghribvalue.text = prayTime.maghrib
            textviewHomeIsyavalue.text = prayTime.isha

            val indonesia = Locale("id", "ID", "ID")
            val simpleDateFormat = SimpleDateFormat(FULL_DAY_DATE_MONTH_YEAR, indonesia)
            textviewHomeDepokpraytimevalue.text = simpleDateFormat.format(date)
        }
    }

    override fun showError(error: Throwable) {

    }

    override fun showTelephone(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phoneNumber")
        startActivity(intent)
    }

    override fun loadSlider(images: List<ImageData>) {
        binding.apply {
            for (image: ImageData in images) {
                val sliderView = DefaultSliderView(this@HomeActivity)
                sliderView.image(image.image)
                viewpagerHomeImageviewpager.addSlider(sliderView)
            }
            viewpagerHomeImageviewpager.startAutoCycle(SLIDER_AUTO_CYCLE_DELAY, SLIDER_AUTO_CYCLE_DURATION, true)
            viewpagerHomeImageviewpager.setCustomIndicator(pageindicatorviewHomePageindicator)
        }
    }

}
