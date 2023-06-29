package com.propertysearchassignment.app.ui.base.features

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.propertysearchassignment.app.model.data.Facility
import com.propertysearchassignment.app.model.data.Option

interface FacilityContract {
    interface View : MvpView {
        fun showFacilities(facilities: List<Facility>)
        fun showExclusionError(errorMessage: String)
        fun onOptionSelected(facility: Facility, option: Option)
    }

    interface Presenter : MvpPresenter<View> {
        fun loadFacilities()
        fun selectOption(facility: Facility, option: Option)
    }
}