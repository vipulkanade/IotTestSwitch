package com.vipulkanade.myapplication.ui.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vipulkanade.myapplication.repository.SwitchRepository
import com.vipulkanade.myapplication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SwitchViewModel @Inject constructor(
    private val switchRepository: SwitchRepository
) : ViewModel() {

    private val _switches = MutableStateFlow<Resource<SwitchState>>(Resource.Loading())
    val switches: StateFlow<Resource<SwitchState>> = _switches.asStateFlow()

    init {
        viewModelScope.launch {
            _switches.value = Resource.Loading()
            try {
                val switchesData = switchRepository.getSwitches()
                Resource.Success(switchesData).data?.let {
                    _switches.value = it
                }
            } catch (e: Exception) {
                _switches.value = Resource.Error(message = e.message.orEmpty())
            }
        }
    }

    fun setFirstSwitch(checked: Boolean) {
        _switches.value = Resource.Loading()
        viewModelScope.launch {
            try {
                switchRepository.setFirstSwitch(checked)
                val switchesData = switchRepository.getSwitches()
                Resource.Success(switchesData).data?.let {
                    _switches.value = it
                }
            } catch (e: Exception) {
                _switches.value = Resource.Error(message = e.message.orEmpty())
            }
        }
    }

    fun setSecondSwitch(checked: Boolean) {
        _switches.value = Resource.Loading()
        viewModelScope.launch {
            try {
                switchRepository.setSecondSwitch(checked)
                val switchesData = switchRepository.getSwitches()
                Resource.Success(switchesData).data?.let {
                    _switches.value = it
                }
            } catch (e: Exception) {
                _switches.value = Resource.Error(message = e.message.orEmpty())
            }
        }
    }
}
