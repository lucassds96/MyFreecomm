package com.example.myfreecomm.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myfreecomm.R
import com.example.myfreecomm.model.ItemDetail
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlinx.android.synthetic.main.item_detail.view.*

class HomeAdapter(val context: Context, private val item: List<ItemDetail>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private var itemClickListener: ItemClickListener? = null

    interface ItemClickListener {
        fun onItemClick(position: Int, item: ItemDetail)
    }

    fun setOnItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_detail, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) = holder.bind(item[position])

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(item: ItemDetail){
            Glide.with(context).load(item.image_url).into(itemView.itemLogoIv)
            itemView.itemNameTv.text = item.name
            itemView.itemPriceTv.text = item.price.toString()
            itemView.stockItemTv.text = item.stock.toString()
            itemView.descriptionItemTv.text = item.description
            itemView.itemContainerCl.setOnClickListener { itemClickListener!!.onItemClick(adapterPosition, item) }
        }
    }
}