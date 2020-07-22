package com.example.myfreecomm.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.myfreecomm.R
import com.example.myfreecomm.model.ItemDetail
import kotlinx.android.synthetic.main.item_detail.view.*

class HomeAdapter(val context: Context, private val item: List<ItemDetail>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private var itemClickListener: ItemClickListener? = null

    interface ItemClickListener {
        fun onItemClick(position: Int, item: ItemDetail)
    }

    fun setOnItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_detail, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(item[position])

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(item: ItemDetail){
            val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
            Glide.with(context).load(item.image_url).apply(requestOptions).into(itemView.iconItemIv)
            itemView.iconNameTv.text = item.name
            itemView.iconPriceTv.text = item.price.toString()
            itemView.iconStockTv.text = item.stock.toString()
            itemView.itemContainerCl.setOnClickListener { itemClickListener!!.onItemClick(adapterPosition, item) }
        }
    }
}