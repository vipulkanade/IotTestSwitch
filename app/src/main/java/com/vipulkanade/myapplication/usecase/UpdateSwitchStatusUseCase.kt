package com.vipulkanade.myapplication.usecase

import com.vipulkanade.myapplication.repository.AppSwitchRepository
import com.vipulkanade.myapplication.repository.PhysicalSwitchRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

interface UseCase {
    fun execute(isSwitchOn: Boolean)
}

class UpdatePhysicalSwitchUseCase @Inject constructor(private val physicalRepository: PhysicalSwitchRepository) : UseCase {
    override fun execute(isSwitchOn: Boolean) {
        // create a CoroutineScope
        val scope = CoroutineScope(Dispatchers.Main)
        // launch a coroutine in the scope
        scope.launch {
            physicalRepository.updatePhysicalSwitchStatus(isSwitchOn)
        }
    }
}

class UpdateAppSwitchUseCase @Inject constructor(private val appRepository: AppSwitchRepository) : UseCase {
    override fun execute(isSwitchOn: Boolean) {
        // create a CoroutineScope
        val scope = CoroutineScope(Dispatchers.Main)
        // launch a coroutine in the scope
        scope.launch {
            appRepository.updateAppSwitchStatus(isSwitchOn)
        }
    }
}