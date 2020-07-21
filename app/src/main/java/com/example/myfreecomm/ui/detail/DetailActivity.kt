package com.example.myfreecomm.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.myfreecomm.R
import com.example.myfreecomm.model.ItemDetail
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        getData()
    }

    private fun getData() {
        val detail = intent.extras.getSerializable("details") as ItemDetail
        itemNameTv.text = detail.name
        itemPriceTv.text = detail.price.toString()
        stockItemTv.text = detail.stock.toString()
        descriptionItemTv.text = detail.description
        Glide.with(this).load(detail.image_url).into(itemLogoIv)
    }
}
