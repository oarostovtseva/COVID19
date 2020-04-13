package com.orost.covid19.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.orost.covid19.R
import com.orost.covid19.model.Statistics
import com.orost.covid19.utils.addSpacesToNumberText
import com.orost.covid19.utils.localeCountryISOMap
import inflate
import kotlinx.android.synthetic.main.item_country.view.*

internal class CountryAdapter : RecyclerView.Adapter<CountryAdapter.ItemViewHolder>() {

    var items = mutableListOf<CountryStatisticsItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class ItemViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.item_country)) {
        fun bind(item: CountryStatisticsItem) {
            itemView.country_name.text = localeCountryISOMap[item.country]?.displayCountry
            itemView.total_in_country.text =
                item.statistic.confirmed.toString().addSpacesToNumberText()
        }
    }
}

data class CountryStatisticsItem(val country: String, val statistic: Statistics)
