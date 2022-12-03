package com.example.newhorizon.staggeredgridlayout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newhorizon.Network.ImageRequester
import com.example.newhorizon.Network.ProductEntry
import com.example.newhorizon.R

class StaggeredProductCardRecyclerViewAdapter(private val _productList: List<ProductEntry>?) :
    RecyclerView.Adapter<StaggeredProductCardViewHolder>(){

    override fun getItemViewType(position: Int): Int {
        return position % 3
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaggeredProductCardViewHolder {
        var layoutId = R.layout.staggered_product_card_first
        if (viewType == 1) {
            layoutId = R.layout.staggered_product_card_second
        } else if (viewType == 2) {
            layoutId = R.layout.staggered_product_card_third
        }

        val layoutView = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return StaggeredProductCardViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: StaggeredProductCardViewHolder, position: Int) {
        if (_productList != null && position < _productList.size) {
            val product = _productList[position]
            holder.productTitle.text = product.title
            holder.productPrice.text = product.price
            ImageRequester.setImageFromUrl(holder.productImage, product.url)
        }
    }

    override fun getItemCount(): Int {
        return _productList?.size ?: 0
    }
}