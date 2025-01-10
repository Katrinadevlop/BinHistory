package com.example.binhistory.viewmodel

import BinHistoryViewModel
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.binhistory.data.BinHistoryRepository

class BinHistoryViewModelFactory(
    private val repository: BinHistoryRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BinHistoryViewModel::class.java)) {
            return BinHistoryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


