package com.vipulkanade.myapplication.repository

import com.vipulkanade.myapplication.ui.data.SwitchState
import com.vipulkanade.myapplication.ui.data.SwitchUpdate
import com.vipulkanade.myapplication.util.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SwitchRepositoryImpl @Inject constructor(
    private val switchService: SwitchService
): SwitchRepository {
    override suspend fun getSwitches(): Resource<SwitchState> {
        return try {
            val switchesData = switchService.getSwitches()
            Resource.Success(switchesData)
        } catch (exception: Exception) {
            Resource.Error(message = exception.message ?: "An error occurred")
        }
    }

    override suspend fun setFirstSwitch(checked: Boolean): Resource<SwitchState> {
        return try {
            val switchesData = switchService.updateSwitches(SwitchUpdate(checked))
            Resource.Success(switchesData)
        } catch (exception: Exception) {
            Resource.Error(message = exception.message ?: "An error occurred")
        }
    }

    override suspend fun setSecondSwitch(checked: Boolean): Resource<SwitchState> {
        return try {
            val switchesData = switchService.updateSwitches(SwitchUpdate(secondSwitch = checked))
            Resource.Success(switchesData)
        } catch (exception: Exception) {
            Resource.Error(message = exception.message ?: "An error occurred")
        }
    }
}