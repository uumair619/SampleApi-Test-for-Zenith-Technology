package com.clean.sampleapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.clean.sampleapi.api.NetworkResult
import com.clean.sampleapi.databinding.ActivityHomeScreenBinding
import com.clean.sampleapi.model.Product
import com.clean.sampleapi.ui.viewmodel.HomeScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeScreen : AppCompatActivity() {


    private lateinit var binding: ActivityHomeScreenBinding
    private lateinit var homeScreenViewModel: HomeScreenViewModel
    @Inject
    lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeScreenViewModel = ViewModelProvider(this)[HomeScreenViewModel::class.java]
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvProduct.adapter = productAdapter
        productAdapter.setItemClick(object : ProductAdapter.ClickInterface<Product> {
            override fun onClick(data: Product) {
                Toast.makeText(this@HomeScreen, data.title, Toast.LENGTH_SHORT).show()
            }
        })

        homeScreenViewModel.productResponse.observe(this) {
            when(it) {
                is NetworkResult.Loading -> {
                    binding.progressbar.isVisible = it.isLoading
                }

                is NetworkResult.Failure -> {
                    Toast.makeText(this, it.errorMessage, Toast.LENGTH_SHORT).show()
                    binding.progressbar.isVisible = false
                }

                is  NetworkResult.Success -> {
                    productAdapter.updateProducts(it.data)
                    binding.progressbar.isVisible = false

                }
            }
        }


    }
}