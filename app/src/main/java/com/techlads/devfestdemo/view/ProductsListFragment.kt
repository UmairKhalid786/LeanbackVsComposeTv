package com.techlads.devfestdemo.view

import android.os.Bundle
import android.view.View
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.*
import com.techlads.devfestdemo.data.DataModel
import com.techlads.devfestdemo.data.Product


class ProductsListFragment : RowsSupportFragment() {

    private var itemSelectedListener: ((Product) -> Unit)? = null
    private var rootAdapter = ArrayObjectAdapter(ListRowPresenter(FocusHighlight.ZOOM_FACTOR_MEDIUM))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = rootAdapter
        onItemViewSelectedListener = ItemViewSelectedListener()

        bindData(DataModel.products)
    }

    private fun bindData(dataList: List<Product>) {
        with(ArrayObjectAdapter(CardPresenter())){
            dataList.forEach { add(it) }
            rootAdapter.add(ListRow(this))
        }
    }

    fun setOnContentSelectedListener(listener : (Product) -> Unit){
        this.itemSelectedListener = listener
    }

    inner class ItemViewSelectedListener : OnItemViewSelectedListener{
        override fun onItemSelected(itemViewHolder: Presenter.ViewHolder?, item: Any?, rowViewHolder: RowPresenter.ViewHolder?, row: Row?) {
            if (item is Product){
                itemSelectedListener?.invoke(item)
            }
        }
    }
}