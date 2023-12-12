package com.D121211009.nasa.ui.activities.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.D121211009.nasa.MyApplication
import com.D121211009.nasa.data.models.Photo
import com.D121211009.nasa.data.repository.NasaRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MainUiState {
    data class Success(val photos: List<Photo>) : MainUiState
    object Error : MainUiState
    object Loading : MainUiState
}

class MainViewModel(private val nasaRepository: NasaRepository): ViewModel() {

    // initial state
    var mainUiState: MainUiState by mutableStateOf(MainUiState.Loading)
        private set

    fun getNasa() = viewModelScope.launch {
        mainUiState = MainUiState.Loading
        try {
            val result = nasaRepository.getNasa()
            mainUiState = MainUiState.Success(result.photos.orEmpty())
        } catch (e: IOException) {
            mainUiState = MainUiState.Error
        }
    }

    // block yg prtama dipanggil ktika ini dibuka
    init {
        getNasa()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication)
                val nasaRepository = application.container.nasaRepository
                MainViewModel(nasaRepository)
            }
        }
    }
}