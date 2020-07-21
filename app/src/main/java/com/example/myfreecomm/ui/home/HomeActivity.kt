package com.example.myfreecomm.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfreecomm.R
import com.example.myfreecomm.ui.detail.DetailActivity
import com.example.myfreecomm.model.ItemDetail
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    lateinit var adapterDetail: HomeAdapter
    lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        setObservers()
    }

    private fun setObservers() {
        homeViewModel.run {
            homeViewModel.showDetails.observe(this@HomeActivity, Observer { initDetails(it) })
            homeViewModel.showError.observe(this@HomeActivity, Observer { errorConection(it) })
        }
    }

    private fun errorConection(it: Throwable?) {
        Toast.makeText(this, it?.message, Toast.LENGTH_LONG).show()
    }

    private fun initDetails(items: List<ItemDetail>) {
        adapterDetail = HomeAdapter(this, items)
        itemsRv.layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.VERTICAL, false)
        itemsRv.adapter = adapterDetail

        adapterDetail.setOnItemClickListener(object:
            HomeAdapter.ItemClickListener {
            override fun onItemClick(position: Int, item: ItemDetail) {
                val intent = Intent(this@HomeActivity, DetailActivity::class.java)
                intent.putExtra("details", item)
                startActivity(intent)
            }
        })
    }
}
