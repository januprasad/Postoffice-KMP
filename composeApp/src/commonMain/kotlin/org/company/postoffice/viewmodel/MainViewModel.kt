package org.company.postoffice.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.domain.model.PostOfficeResponse
import com.example.network.domain.repository.Repository
import com.example.network.domain.usecase.ResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {
    private val _postOffice = MutableStateFlow<ResultState<PostOfficeResponse>>(ResultState.LOADING)
    var postOffice: StateFlow<ResultState<PostOfficeResponse>> = _postOffice.asStateFlow()

    fun getPostOfficeData(pincode: String) {
        viewModelScope.launch {
            _postOffice.value = ResultState.LOADING
            try {
                val response = repository.getPostOffices(pincode)
                _postOffice.value = ResultState.SUCCESS(response.first())
            } catch (e: Exception) {
                _postOffice.value = ResultState.ERROR(e.message.toString())
            }
        }
    }
}