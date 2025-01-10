package com.example.binhistory.activities

import BinHistoryViewModel
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.binhistory.data.BinHistoryRepository
import com.example.binhistory.data.BinHistoryDataBase
import com.example.binhistory.databinding.ActivityMainBinding
import com.example.binhistory.apiData.ApiClient
import com.example.binhistory.viewmodel.BinHistoryViewModelFactory
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val repository: BinHistoryRepository by lazy {
        val apiService = ApiClient.apiService
        val dao = BinHistoryDataBase.getInstance(applicationContext).binHistoryDao()
        BinHistoryRepository(apiService, dao)
    }

    private val viewModel: BinHistoryViewModel by viewModels {
        BinHistoryViewModelFactory(repository)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearch.setOnClickListener {
            val bin = binding.binEditText.text.toString()
            viewModel.fetchBinInfo(bin)
        }

        viewModel.toastMessage.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        lifecycleScope.launchWhenStarted {
            try {
                viewModel.binHistory.collectLatest { history ->
                    binding.binInfoText.text =
                        if (history.isNullOrEmpty()) {
                            "История запросов пуста."
                        } else {
                            history.joinToString("\n") { item ->
                                item?.let {
                                    """
                                    Страна: ${it.country ?: "Не указана"}
                                    Координаты: ${it.coordinates ?: "Не указаны"}
                                    Тип карты: ${it.cardType ?: "Не указан"}
                                    Url: ${it.bankUrl ?: "Не указан"}
                                    Телефон: ${it.bankPhone ?: "Не указан"}
                                    Город: ${it.bankCity ?: "Не указан"}
                                    """.trimIndent()
                                } ?: "Данные отсутствуют."
                            }
                        }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@MainActivity, "Произошла ошибка: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnOpenHistory.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }
}
