import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binhistory.data.BinHistoryRepository
import com.example.binhistory.data.BinHistoryEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class BinHistoryViewModel(private val repository: BinHistoryRepository) : ViewModel() {
    private val _binHistory = MutableStateFlow<List<BinHistoryEntity>>(emptyList())
    val binHistory: StateFlow<List<BinHistoryEntity>> = _binHistory

    val toastMessage = MutableLiveData<String>()

    fun fetchBinInfo(bin: String) {
        viewModelScope.launch {
            try {
                val response = repository.getBinInfo(bin)
                if (response != null) {
                    _binHistory.value = repository.getBinHistory()
                    toastMessage.postValue("Информация успешно получена")
                } else {
                    toastMessage.postValue("Не удалось получить данные по BIN")
                }
            } catch (e: HttpException) {
                toastMessage.postValue("Ошибка сети: ${e.message}")
            } catch (e: Exception) {
                toastMessage.postValue("Произошла ошибка: ${e.message}")
            }
        }
    }

    fun clearHistory() {
        viewModelScope.launch {
            repository.clearHistory()
            _binHistory.value = emptyList()
        }
    }
}
