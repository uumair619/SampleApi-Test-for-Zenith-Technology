package com.clean.sampleapi.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.clean.sampleapi.databinding.ItemPostBinding
import com.clean.sampleapi.model.Product
import javax.inject.Inject

class ProductAdapter  @Inject constructor() : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {


    var products = mutableListOf<Product>()
    private var clickInterface: ClickInterface<Product>? = null

    fun setItemClick(clickInterface: ClickInterface<Product>) {
        this.clickInterface = clickInterface
    }

    fun updateProducts(products: List<Product>) {
        this.products = products.toMutableList()
        notifyItemRangeInserted(0, products.size)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding =
            ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.view.tvTitle.text = product.title
        holder.view.tvPrice.text = "${product.price} USD"
        holder.view.tvDes.text = "${product.description}"
        Glide
            .with(holder.view.imgProductImage.context)
            .load(product.thumbnail)
            .into(holder.view.imgProductImage)
        holder.view.movieCard.setOnClickListener {
            clickInterface?.onClick(product)
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }


    class ProductViewHolder(val view: ItemPostBinding) : RecyclerView.ViewHolder(view.root)

    interface ClickInterface<T> {
        fun onClick(data: T)
    }
}