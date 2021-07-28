package com.rajand.core.presentation.ui.surah

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rajand.core.R
import com.rajand.core.databinding.ItemListSurahBinding
import com.rajand.core.domain.model.Surah

class SurahAdapter : RecyclerView.Adapter<SurahAdapter.ListViewHolder>() {

    private var listData = ArrayList<Surah>()
    var onItemClick: ((Surah) -> Unit)? = null

    fun setData(newListData: List<Surah>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_surah, parent, false))

    override fun onBindViewHolder(holder: SurahAdapter.ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemListSurahBinding.bind(itemView)

        fun bind(data: Surah) {
            with(binding) {
                tvNumberSurah.text = data.numberSurah.toString()
                tvNameSurah.text = data.name
                tvRevelationSurah.text = data.revelation
                tvNumberOfVerses.text = data.numberOfVerses.toString()
                tvNameSurahArab.text = data.arabName
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}