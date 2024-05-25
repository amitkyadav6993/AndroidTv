package amit.yadav.tvpiptask.view.viewmodel

import amit.yadav.domain.fold
import amit.yadav.usecase.match.MatchRemoteUseCase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(
    private val matchRemoteUseCase: MatchRemoteUseCase
) : ViewModel() {

    private val _match = MutableLiveData<MatchViewStateData>(MatchViewStateData.Initial)
    val matchLiveData: LiveData<MatchViewStateData> get() = _match

    fun fetchMatchScore(isFirst: Boolean = false) {
        viewModelScope.launch {
            if (isFirst) _match.value = MatchViewStateData.Loading
            matchRemoteUseCase.execute("").collect {
                it.fold(onSuccess = { response ->
                    if (response.data.isNullOrEmpty()) {
                        _match.value = MatchViewStateData.LoadFailed
                    } else {
                        val firstData = response.data?.firstOrNull()
                        _match.value = MatchViewStateData.Success(data = firstData)
                    }
                }, onFailure = { throwable ->
                    _match.value = MatchViewStateData.Failure(throwable)
                })
            }
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

}