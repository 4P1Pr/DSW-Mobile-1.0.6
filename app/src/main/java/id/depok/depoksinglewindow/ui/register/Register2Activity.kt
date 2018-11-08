package id.depok.depoksinglewindow.ui.register

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.data.*
import id.depok.depoksinglewindow.databinding.ActivityRegister2Binding
import id.depok.depoksinglewindow.ui.BaseActivity
import id.depok.depoksinglewindow.ui.home.HomeActivity
import org.koin.android.ext.android.inject

const val ARG_FORM_DATA = "form_data"
class Register2Activity : BaseActivity<Register2Contract.Presenter>(), Register2Contract.View {

    override val presenter by inject<Register2Contract.Presenter>()

    lateinit var binding: ActivityRegister2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register2)

        initialize()
    }

    override fun close() {
        finish()
    }

    override fun showProvinceSpinner() {
        binding.spinnerRegisterProvince.visibility = View.VISIBLE
    }

    override fun showKabupatenSpinner() {
        binding.spinnerRegisterKabupaten.visibility = View.VISIBLE
    }

    override fun hideProvinceSpinner() {
        binding.spinnerRegisterProvince.visibility = View.GONE
    }

    override fun hideKabupatenSpinner() {
        binding.spinnerRegisterKabupaten.visibility = View.GONE
    }

    override fun setProvinces(provinceList: MutableList<LocationData>) {
        binding.apply {
            val placeholder = MockData().getProvincePlaceHolder(this@Register2Activity, MOCK_ID)
            provinceList.add(0, placeholder)
            spinnerRegisterProvince.adapter = ArrayAdapter<LocationData>(this@Register2Activity,
                    android.R.layout.simple_list_item_1, provinceList)
            spinnerRegisterProvince.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    presenter.onProvinceSelected(spinnerRegisterProvince.selectedItem as LocationData)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }
        }
    }

    override fun setKabupatens(kabupatenList: MutableList<LocationData>) {
        binding.apply {
            val placeholder = MockData().getKabupatenPlaceHolder(this@Register2Activity, MOCK_ID)
            kabupatenList.add(0, placeholder)
            spinnerRegisterKabupaten.adapter = ArrayAdapter<LocationData>(this@Register2Activity,
                    android.R.layout.simple_list_item_1, kabupatenList)
            spinnerRegisterKabupaten.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    presenter.onKabupatenSelected(spinnerRegisterKabupaten.selectedItem as LocationData)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }
        }
    }

    override fun setKecamatans(kecamatanList: MutableList<LocationData>) {
        binding.apply {
            val placeholder = MockData().getKecamatanPlaceHolder(this@Register2Activity, MOCK_ID)
            kecamatanList.add(0, placeholder)
            spinnerRegisterKecamatan.adapter = ArrayAdapter<LocationData>(this@Register2Activity,
                    android.R.layout.simple_list_item_1, kecamatanList)
            spinnerRegisterKecamatan.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    presenter.onKecamatanSelected(spinnerRegisterKecamatan.selectedItem as LocationData)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }
        }
    }

    override fun setKelurahans(kelurahanList: MutableList<LocationData>) {
        val placeholder = MockData().getKelurahanPlaceHolder(this@Register2Activity, MOCK_ID)
        kelurahanList.add(0, placeholder)
        binding.apply {
            spinnerRegisterKelurahan.adapter = ArrayAdapter<LocationData>(this@Register2Activity,
                    android.R.layout.simple_list_item_1, kelurahanList)
        }
    }

    override fun clearKabupatens() {
        binding.apply {
            spinnerRegisterKabupaten.onItemSelectedListener = null
            spinnerRegisterKabupaten.adapter = createInitialKabupatenAdapter()
        }
    }

    override fun clearKecamatans() {
        binding.apply {
            spinnerRegisterKecamatan.onItemSelectedListener = null
            spinnerRegisterKecamatan.adapter = createInitialKecamatanAdapter()
        }
    }

    override fun clearKelurahans() {
        binding.apply {
            spinnerRegisterKelurahan.onItemSelectedListener = null
            spinnerRegisterKelurahan.adapter = createInitialKelurahanAdapter()
        }
    }

    override fun showErrorAddress(stringId: Int) {
        binding.edittextRegisterAddress.error = getString(stringId)
    }

    override fun showErrorPostalCode(stringId: Int) {
        binding.edittextRegisterPostalcode.error = getString(stringId)
    }

    override fun clearError() {
        binding.apply {
            edittextRegisterAddress.error = null
            edittextRegisterPostalcode.error = null
        }
    }

    override fun showMain() {
        finish()
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun initialize() {
        setSupportActionBar(binding.toolbarRegister)

        binding.apply {
            toolbarRegister.setNavigationOnClickListener{ finish() }

            spinnerRegisterIsdepokresidence.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    presenter.onDepokResidencesSelected(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }

            spinnerRegisterProvince.adapter = createInitialProvinceAdapter()
            spinnerRegisterKabupaten.adapter = createInitialKabupatenAdapter()
            spinnerRegisterKecamatan.adapter = createInitialKecamatanAdapter()
            spinnerRegisterKelurahan.adapter = createInitialKelurahanAdapter()

            buttonRegisterRegisterbutton.setOnClickListener {
                presenter.onPressRegister(RegisterForm(
                        "",
                        "",
                        0,
                        MockData().getPekerjaanList(this@Register2Activity, WORKER_ID),
                        "",
                        "",
                        (spinnerRegisterIsdepokresidence.selectedItemPosition + 1) % 2,
                        edittextRegisterAddress.text.toString(),
                        // if not depok residence, give selected province
                        if (spinnerRegisterIsdepokresidence.selectedItemPosition != 0)
                            spinnerRegisterProvince.selectedItem as LocationData
                        else
                            MockData().getJawaBaratLocation(this@Register2Activity),
                        // if not depok residence, give selected kabupaten
                        if (spinnerRegisterIsdepokresidence.selectedItemPosition != 0)
                            spinnerRegisterKabupaten.selectedItem as LocationData
                        else
                            MockData().getDepokLocation(this@Register2Activity),
                        spinnerRegisterKecamatan.selectedItem as LocationData,
                        spinnerRegisterKelurahan.selectedItem as LocationData,
                        edittextRegisterPostalcode.text.toString(),
                        ""
                ))
            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val formData: RegisterForm? = intent.extras.getSerializable(ARG_FORM_DATA) as RegisterForm?
        presenter.initialFormData = formData

        presenter.view = this
        presenter.start()
    }

    private fun createInitialProvinceAdapter(): ArrayAdapter<LocationData> {
        val provinceList = ArrayList<LocationData>()
        provinceList.add(MockData().getProvincePlaceHolder(this@Register2Activity, MOCK_ID))
        return ArrayAdapter(
                this@Register2Activity,
                android.R.layout.simple_list_item_1,
                provinceList)
    }

    private fun createInitialKabupatenAdapter(): ArrayAdapter<LocationData> {
        val kabupatenList = ArrayList<LocationData>()
        kabupatenList.add(MockData().getKabupatenPlaceHolder(this@Register2Activity, MOCK_ID))
        return ArrayAdapter(
                this@Register2Activity,
                android.R.layout.simple_list_item_1,
                kabupatenList
        )
    }

    private fun createInitialKecamatanAdapter(): ArrayAdapter<LocationData> {
        val kecamatanList = ArrayList<LocationData>()
        kecamatanList.add(MockData().getKecamatanPlaceHolder(this@Register2Activity, MOCK_ID))
        return ArrayAdapter(
                this@Register2Activity,
                android.R.layout.simple_list_item_1,
                kecamatanList
        )
    }

    private fun createInitialKelurahanAdapter(): ArrayAdapter<LocationData> {
        val kelurahanList = ArrayList<LocationData>()
        kelurahanList.add(MockData().getKelurahanPlaceHolder(this@Register2Activity, MOCK_ID))
        return ArrayAdapter(
                this@Register2Activity,
                android.R.layout.simple_list_item_1,
                kelurahanList
        )
    }
}