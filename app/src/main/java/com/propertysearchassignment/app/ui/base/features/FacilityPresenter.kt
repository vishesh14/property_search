package com.propertysearchassignment.app.ui.base.features

import com.propertysearchassignment.app.model.data.Facility
import com.propertysearchassignment.app.model.data.Option
import com.propertysearchassignment.app.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class FacilityPresenter : FacilityContract.Presenter {

    private var view: FacilityContract.View? = null
    private lateinit var apiService: ApiService
    private lateinit var realm: Realm


    override fun attachView(view: FacilityContract.View) {
        this.view = view
        apiService = createApiService()
        realm = Realm.getDefaultInstance()
    }

    override fun detachView(retainInstance: Boolean) {
        view = null
        realm.close()
    }

    override fun detachView() {
        view = null
        realm.close()
    }

    override fun destroy() {
        view = null
        realm.close()
    }

    override fun loadFacilities() {
        apiService.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response -> view?.showFacilities(response.facilities) },
                { error -> view?.showExclusionError(error.message ?: "Unknown error occurred") }
            )
    }

    override fun selectOption(facility: Facility, option: Option) {
        if (isExclusionCombination(facility, option)) {
            view?.showExclusionError("Cannot select this combination.")
            return
        }

        // Save the selected option using Realm or any other persistence mechanism
        realm.executeTransactionAsync { realm ->
            // Save the selected option to Realm
            //val realmFacility = realm.copyToRealmOrUpdate(option)
          //  realmFacility.options.add(option)
        }
    }

    private fun isExclusionCombination(facility: Facility, option: Option): Boolean {
        val exclusions = facility.exclusions

        if (exclusions != null) {
            for (exclusion in exclusions) {
                val excludedFacility = exclusion.facility
                val excludedOptions = exclusion.options

                if (excludedFacility == facility && excludedOptions.contains(option)) {
                    return true
                }
            }
        }

        return false
    }

    private fun createApiService(): ApiService {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/iranjith4/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }
}
