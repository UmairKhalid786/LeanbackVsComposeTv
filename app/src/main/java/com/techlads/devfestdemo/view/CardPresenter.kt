package com.techlads.devfestdemo.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.techlads.devfestdemo.R
import com.techlads.devfestdemo.data.Product
import com.techlads.devfestdemo.utils.getHeightInPercent
import com.techlads.devfestdemo.utils.getWidthInPercent

class CardPresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false).apply {
            layoutParams = layoutParams.apply {
                width = parent.context.getWidthInPercent(12)
                height = parent.context.getHeightInPercent(32)
            }
        })
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any?) {
        val content = item as? Product ?: return
        val imageview = viewHolder.view.findViewById<ImageView>(R.id.poster_image) ?: return

        Glide.with(viewHolder.view.context.applicationContext)
            .load(content.image)
            .into(imageview)
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {
        val imageview = viewHolder.view.findViewById<ImageView>(R.id.poster_image) ?: return
        Glide.with(viewHolder.view.context.applicationContext).clear(imageview)
    }
}