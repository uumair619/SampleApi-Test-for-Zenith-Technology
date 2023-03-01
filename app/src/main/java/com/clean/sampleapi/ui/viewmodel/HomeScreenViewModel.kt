package com.clean.sampleapi.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clean.sampleapi.Repository.NetworkRepository
import com.clean.sampleapi.api.NetworkResult
import com.clean.sampleapi.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel  @Inject constructor(
    private val networkRepository: NetworkRepository
) : ViewModel() {
    private var _productResponse = MutableLiveData<NetworkResult<List<Product>>>()
    val productResponse: LiveData<NetworkResult<List<Product>>> = _productResponse

    init {
        getAllProducts()
    }


    private fun getAllProducts() {
        viewModelScope.launch {
            networkRepository.getProduct().collect {
                _productResponse.postValue(it)
            }
        }
    }
}

