package com.example.binhistory.activities

import BinHistoryViewModel
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.binhistory.apiData.ApiClient
import com.example.binhistory.data.BinHistoryDataBase
import com.example.binhistory.data.BinHistoryRepository
import com.example.binhistory.databinding.ActivitySecondBinding
import com.example.binhistory.viewmodel.BinHistoryViewModelFactory
import kotlinx.coroutines.flow.collectLatest

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: BinHistoryViewModel by viewModels {
            BinHistoryViewModelFactory(
                BinHistoryRepository(
                    ApiClient.apiService,
                    BinHistoryDataBase.getInstance(applicationContext).binHistoryDao()
                )
            )
        }

        val adapter = BinHistoryAdapter()
        binding.recyclerView.adapter = adapter

        lifecycleScope.launchWhenStarted {
            viewModel.binHistory.collectLatest { history ->
                adapter.submitList(history)
            }
        }
    }
}
