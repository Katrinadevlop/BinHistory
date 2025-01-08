package com.example.binhistory.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binhistory.data.BinHistoryRepository
import com.example.binhistory.data.BinHistoryEntity
import kotlinx.coroutines.launch

class BinHistoryViewModel(private val repository: BinHistoryRepository) : ViewModel() {
    val binHistory = mutableListOf<BinHistoryEntity>()

    fun fetchBinInfo(bin: String) {
        viewModelScope.launch {
            val binInfo = repository.getBinInfo(bin)
            binHistory.add(binInfo)
        }
    }

    fun clearHistory() {
        viewModelScope.launch {
            repository.clearHistory()
            binHistory.clear()
        }
    }
}
