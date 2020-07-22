package com.example.myfreecomm.ui.detail

import android.graphics.PorterDuff
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.myfreecomm.R
import com.example.myfreecomm.model.ItemDetail
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        setHeader()
        getData()
    }

    private fun setHeader() {
        title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val icon = ContextCompat.getDrawable(this, R.drawable.ic_back)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            icon?.setColorFilter(resources.getColor(R.color.colorWhite, null), PorterDuff.Mode.SRC_IN)
        }
        toolbar?.navigationIcon = icon
        toolbar.setNavigationOnClickListener { finish() }
    }

    private fun getData() {
        val detail = intent.extras.getSerializable("details") as ItemDetail
        itemNameTv.text = detail.name
        itemPriceTv.text = detail.price.toString()
        stockItemTv.text = getString(R.string.stock_available, detail.stock.toString())
        descriptionItemTv.text = detail.description
        val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
        Glide.with(this).load(detail.image_url).apply(requestOptions).into(itemLogoIv)
    }
}
