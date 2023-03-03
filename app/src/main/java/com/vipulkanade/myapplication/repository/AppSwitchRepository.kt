package com.vipulkanade.myapplication.repository

import com.vipulkanade.myapplication.ui.data.AppSwitchState
import com.vipulkanade.myapplication.ui.data.PhysicalSwitchState
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppSwitchRepository @Inject constructor(): SwitchRepositoryLocal {
    private val _uiState = MutableStateFlow(AppSwitchState())
    override suspend fun getAppSwitchState(): AppSwitchState = _uiState.value
    override suspend fun getPhysicalSwitchState(): PhysicalSwitchState {
        TODO("Not yet implemented")
    }

    override suspend fun updateAppSwitchStatus(isSwitchOn: Boolean) {
        _uiState.emit(_uiState.value.copy(isSwitchOn = isSwitchOn))
    }

    override suspend fun updatePhysicalSwitchStatus(isSwitchOn: Boolean) {
        TODO("Not yet implemented")
    }
}