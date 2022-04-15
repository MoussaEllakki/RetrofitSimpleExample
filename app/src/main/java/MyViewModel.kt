import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitproject.Character


class MyViewModel(
    val ddg: String,
    val bdbfsdbf: Boolean,
) : ViewModel(){

    val getAllData: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val getAll: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    init {
        getAll.value = 0
    }


    var data: List<Character>? = null





}