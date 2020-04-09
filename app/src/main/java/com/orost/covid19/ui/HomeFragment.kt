package com.orost.covid19.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.orost.covid19.R
import com.orost.covid19.utils.addSpacesToNumberText
import com.orost.covid19.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.NumberFormat
import java.util.*

class HomeFragment : BaseFragment() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun initUI(savedInstanceState: Bundle?) { }

    override fun subscribeToLiveData() {
        viewModel.globalLatestLiveData.observe(viewLifecycleOwner, Observer { statistics ->
            total_confirmed.text = statistics.result.confirmed.toString().addSpacesToNumberText()
            total_deaths.text = statistics.result.deaths.toString().addSpacesToNumberText()
            total_recovered.text = statistics.result.recovered.toString().addSpacesToNumberText()
        })
    }

}
