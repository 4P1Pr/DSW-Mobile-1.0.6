package id.depok.depoksinglewindow.di

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.gson.GsonBuilder
import id.depok.depoksinglewindow.BuildConfig
import id.depok.depoksinglewindow.data.api.ApiService
import id.depok.depoksinglewindow.data.api.ApiSettings
import id.depok.depoksinglewindow.data.api.PrayTimeService
import id.depok.depoksinglewindow.data.source.*
import id.depok.depoksinglewindow.data.source.local.ComplaintQuestionCategoryLocalDataSource
import id.depok.depoksinglewindow.data.source.local.UserLocalDataSource
import id.depok.depoksinglewindow.data.source.remote.*
import id.depok.depoksinglewindow.manager.AnalyticsManager
import id.depok.depoksinglewindow.manager.NetManager
import id.depok.depoksinglewindow.ui.bphtb.BphtbActivity
import id.depok.depoksinglewindow.ui.bphtb.BphtbContract
import id.depok.depoksinglewindow.ui.bphtb.BphtbPresenter
import id.depok.depoksinglewindow.ui.complaint.*
import id.depok.depoksinglewindow.ui.editpassword.EditPasswordActivity
import id.depok.depoksinglewindow.ui.editpassword.EditPasswordContract
import id.depok.depoksinglewindow.ui.editpassword.EditPasswordPresenter
import id.depok.depoksinglewindow.ui.forgotpassword.ForgotPasswordActivity
import id.depok.depoksinglewindow.ui.forgotpassword.ForgotPasswordContract
import id.depok.depoksinglewindow.ui.forgotpassword.ForgotPasswordPresenter
import id.depok.depoksinglewindow.ui.home.HomeActivity
import id.depok.depoksinglewindow.ui.home.HomeContract
import id.depok.depoksinglewindow.ui.home.HomePresenter
import id.depok.depoksinglewindow.ui.layanankesehatan.*
import id.depok.depoksinglewindow.ui.layananpendidikan.LayananPendidikanActivity
import id.depok.depoksinglewindow.ui.layananpendidikan.LayananPendidikanContract
import id.depok.depoksinglewindow.ui.layananpendidikan.LayananPendidikanPresenter
import id.depok.depoksinglewindow.ui.layananperizinan.*
import id.depok.depoksinglewindow.ui.login.LoginActivity
import id.depok.depoksinglewindow.ui.login.LoginContract
import id.depok.depoksinglewindow.ui.login.LoginPresenter
import id.depok.depoksinglewindow.ui.onboarding.OnBoardingActivity
import id.depok.depoksinglewindow.ui.onboarding.OnBoardingContract
import id.depok.depoksinglewindow.ui.onboarding.OnBoardingPresenter
import id.depok.depoksinglewindow.ui.pbb.PbbActivity
import id.depok.depoksinglewindow.ui.pbb.PbbContract
import id.depok.depoksinglewindow.ui.pbb.PbbPresenter
import id.depok.depoksinglewindow.ui.pln.PlnActivity
import id.depok.depoksinglewindow.ui.pln.PlnContract
import id.depok.depoksinglewindow.ui.pln.PlnPresenter
import id.depok.depoksinglewindow.ui.profile.ProfileActivity
import id.depok.depoksinglewindow.ui.profile.ProfileContract
import id.depok.depoksinglewindow.ui.profile.ProfilePresenter
import id.depok.depoksinglewindow.ui.register.*
import id.depok.depoksinglewindow.ui.settings.SettingsActivity
import id.depok.depoksinglewindow.ui.settings.SettingsContract
import id.depok.depoksinglewindow.ui.settings.SettingsPresenter
import id.depok.depoksinglewindow.ui.splash.SplashActivity
import id.depok.depoksinglewindow.ui.splash.SplashContract
import id.depok.depoksinglewindow.ui.splash.SplashPresenter
import id.depok.depoksinglewindow.ui.zakat.ZakatActivity
import id.depok.depoksinglewindow.ui.zakat.ZakatContract
import id.depok.depoksinglewindow.ui.zakat.ZakatPresenter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.applicationContext
import id.depok.depoksinglewindow.util.rx.ApplicationSchedulerProvider
import id.depok.depoksinglewindow.util.rx.SchedulerProvider
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by PiNGUiN on 2017-12-08.
 */

/*
 * Module list
 */
fun appModules() = listOf(appModule, repositoryModule, rxModule)

/**
 * Koin main module
 */
val appModule = applicationContext {
        factory { SplashActivity() }
        factory { SplashPresenter(get()) as SplashContract.Presenter }

        factory { OnBoardingActivity() }
        factory { OnBoardingPresenter() as OnBoardingContract.Presenter }

        factory { LoginActivity() }
        factory { LoginPresenter(get(), get(), get(), get(), get()) as LoginContract.Presenter }

        factory { ForgotPasswordActivity() }
        factory {
            ForgotPasswordPresenter() as ForgotPasswordContract.Presenter
        }

        factory { RegisterActivity() }
        factory { RegisterPresenter() as RegisterContract.Presenter }

        factory { Register2Activity() }
        factory { Register2Presenter(get(), get(), get(), get(), get(), get()) as Register2Contract.Presenter }

        factory { HomeActivity() }
        factory {
            HomePresenter(get(), get(), get(), get(), get(), get()) as HomeContract.Presenter
        }

        factory { PbbActivity() }
        factory {
            PbbPresenter() as PbbContract.Presenter
        }

        factory { BphtbActivity() }
        factory {
            BphtbPresenter() as BphtbContract.Presenter
        }

        factory { LayananPerizinanActivity() }
        factory {
            LayananPerizinanPresenter() as LayananPerizinanContract.Presenter
        }

        factory { LacakPerizinanActivity() }
        factory {
            LacakPerizinanPresenter() as LacakPerizinanContract.Presenter
        }

        factory { HealthCareServiceActivity() }
        factory {
            LayananKesehatanPresenter() as HealthCareServiceContract.Presenter
        }

        factory { LayananPendidikanActivity() }
        factory {
            LayananPendidikanPresenter() as LayananPendidikanContract.Presenter
        }

        factory { ZakatActivity() }
        factory {
            ZakatPresenter() as ZakatContract.Presenter
        }

        factory { PlnActivity() }
        factory {
            PlnPresenter() as PlnContract.Presenter
        }

        factory { OldPatientQueuePageActivity() }
        factory {
            OldPatientQueuePagePresenter() as OldPatientQueuePageContract.Presenter
        }

        provide { ComplaintPageActivity() }
        factory {
            ComplaintPagePresenter(get(), get(), get(), get(), get(), get()) as ComplaintPageContract.Presenter
        }

        provide { ComplaintListActivity() }
        factory {
            ComplaintListPresenter(get(), get(), get(), get()) as ComplaintListContract.Presenter
        }

        factory { SettingsActivity() }
        factory {
            SettingsPresenter(get(), get()) as SettingsContract.Presenter
        }

        factory { EditPasswordActivity() }
        factory {
            EditPasswordPresenter(get(), get(), get(), get()) as EditPasswordContract.Presenter
        }

        factory { ProfileActivity() }
        factory {
            ProfilePresenter(get()) as ProfileContract.Presenter
        }

        provide { PrayTimeRepository(get()) }
        provide { AuthRepository(get()) }
        provide { ComplaintQuestionQuestionRepository(get()) }
        provide { NetManager(get()) }
        provide { ComplaintQuestionCategoryRepository(get("categoryLocalDataSource"), get("categoryRemoteDataSource")) }
        provide { LocationDataRepository(get()) }
        provide { ImageSliderRepository(get()) }
        provide { AnalyticsManager(createWebService(get(), BuildConfig.BASE_URL, ApiSettings.COMPLAINT_QUESTION_DATE_FORMAT), get("userLocalDataSource")) }
        provide { FirebaseAnalytics.getInstance(get()) }
    }

val repositoryModule = applicationContext {
        provide("userLocalDataSource") { UserLocalDataSource(get()) }
        provide { UserRepository(get("userLocalDataSource")) } bind (UserDataSource::class)

        // provided web components
        provide { createOkHttpClient() }

        // Fill property
        provide { createWebService<PrayTimeService>(get(), BuildConfig.PRAY_TIME_URL, ApiSettings.PRAY_TIME_DATE_FORMAT) }
        provide { PrayTimeRemoteDataSource(get()) } bind (PrayTimeDataSource::class)

        provide { createWebService<ApiService>(get(), BuildConfig.BASE_URL, ApiSettings.COMPLAINT_QUESTION_DATE_FORMAT) }
        provide { AuthRemoteDataSource(get()) } bind (AuthDataSource::class)
        provide { ComplaintQuestionRemoteDataSource(get()) } bind (ComplaintQuestionDataSource::class)
        provide("categoryLocalDataSource") { ComplaintQuestionCategoryLocalDataSource(get()) } bind (ComplaintQuestionCategoryDataSource::class)
        provide("categoryRemoteDataSource") { ComplaintQuestionCategoryRemoteDataSource(get()) } bind (ComplaintQuestionCategoryDataSource::class)
        provide("LogDataSource") { LogRemoteDataSource(get()) } bind (LogDataSource::class)
        provide { LocationDataRemoteDataSource(get()) } bind (LocationDataSource::class)
        provide { ImageSliderRemoteDataSource(get()) } bind (ImageSliderDataSource::class)
        provide { LogRemoteDataSource(get()) } bind (LogDataSource::class)
    }

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    val builder = OkHttpClient.Builder()
    builder.connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
    if(BuildConfig.DEBUG) {
        builder.addInterceptor(httpLoggingInterceptor)
    }
    return builder.build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String, dateFormat: String): T {
    val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setDateFormat(dateFormat).create() )
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
    return retrofit.create(T::class.java)
}

val rxModule = applicationContext {
        provide { ApplicationSchedulerProvider() } bind (SchedulerProvider::class)
    }

