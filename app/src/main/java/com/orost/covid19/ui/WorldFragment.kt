package com.orost.covid19.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.orost.covid19.R
import com.orost.covid19.ui.adapter.CountryStatisticsItem
import com.orost.covid19.ui.adapter.WorldAdapter
import com.orost.covid19.viewmodel.WorldViewModel
import kotlinx.android.synthetic.main.fragment_world.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WorldFragment : BaseFragment() {

    private val viewModel: WorldViewModel by viewModel()
    private val worldAdapter = WorldAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_world, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.actionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun initUI(savedInstanceState: Bundle?) {
        country_recycler.adapter = worldAdapter
        country_recycler.layoutManager = LinearLayoutManager(context)
    }

    override fun subscribeToLiveData() {
        viewModel.worldStatistic.observe(this, Observer { globalLatestStatistic ->
            val statistic : MutableList<CountryStatisticsItem> = mutableListOf()
            globalLatestStatistic.result.forEach { item ->
                statistic.add(CountryStatisticsItem(item.entries.first().key, item.entries.first().value))
            }
            statistic.sortByDescending { it.statistic.confirmed }
            worldAdapter.items = statistic
        })
    }

}
