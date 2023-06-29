package com.propertysearchassignment.app.ui.base.features

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import com.propertysearchassignment.app.R
import com.propertysearchassignment.app.model.data.Facility
import com.propertysearchassignment.app.model.data.Option
import kotlinx.android.synthetic.main.activity_facility.rv_list

class FacilityActivity : MvpActivity<FacilityContract.View, FacilityContract.Presenter>(),
    FacilityContract.View {
    private lateinit var adapter: FacilityAdapter

    override fun createPresenter(): FacilityContract.Presenter {
        return FacilityPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facility)
        adapter = FacilityAdapter(object : FacilityAdapter.OptionSelectionListener {
            override fun onOptionSelected(facility: Facility, option: Option) {
                presenter.selectOption(facility, option)
            }
        })

        rv_list.layoutManager = LinearLayoutManager(this)
        rv_list.adapter = adapter
        presenter.loadFacilities()
    }

    override fun showFacilities(facilities: List<Facility>) {
        adapter.setFacilities(facilities)
    }

    override fun showExclusionError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionSelected(facility: Facility, option: Option) {
        presenter.selectOption(facility, option)
    }
}
