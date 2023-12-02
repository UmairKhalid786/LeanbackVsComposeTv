package com.techlads.devfestdemo.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.techlads.devfestdemo.R
import com.techlads.devfestdemo.data.Product
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LeanbackActivity : AppCompatActivity() {

    private lateinit var txtTitle: TextView
    private lateinit var txtDescription: TextView

    private lateinit var imgBanner: ImageView
    private lateinit var listFragment: ProductsListFragment

    private var updateJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgBanner = findViewById(R.id.img_banner)
        txtTitle = findViewById(R.id.title)
        txtDescription = findViewById(R.id.description)

        listFragment = ProductsListFragment()

        addFragment(R.id.list_fragment, listFragment)

        listFragment.setOnContentSelectedListener {
            updateJob?.cancel()
            updateJob = lifecycleScope.launch {
                delay(500)
                updateBanner(it)
            }
        }
    }

    private fun updateBanner(product: Product) {
        txtTitle.text = product.title
        txtDescription.text = product.description

        Glide.with(this@LeanbackActivity).load(product.backdropPath)
            .transition(DrawableTransitionOptions.withCrossFade(1000))
            .into(imgBanner)
    }

    private fun addFragment(containerId: Int, listFragment: ProductsListFragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(containerId, listFragment)
        transaction.commit()
    }
}