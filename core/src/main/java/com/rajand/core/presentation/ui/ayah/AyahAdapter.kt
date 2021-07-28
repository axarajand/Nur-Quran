package com.rajand.core.presentation.ui.ayah

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rajand.core.R
import com.rajand.core.databinding.ItemListAyahBinding
import com.rajand.core.domain.model.Ayah

class AyahAdapter : RecyclerView.Adapter<AyahAdapter.ListViewHolder>() {

    private var listData = ArrayList<Ayah>()
    var onItemClick: ((Ayah) -> Unit)? = null

    fun setData(newListData: List<Ayah>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_ayah, parent, false))

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemListAyahBinding.bind(itemView)

        fun bind(data: Ayah) {
            with(binding) {
                tvNumberAyah.text = data.numberAyah.toString()
                tvArabText.text = data.text
                tvTranslationText.text = data.translationText
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}