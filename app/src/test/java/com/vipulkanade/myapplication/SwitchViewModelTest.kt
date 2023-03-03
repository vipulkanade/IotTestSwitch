package com.vipulkanade.myapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.vipulkanade.myapplication.repository.SwitchRepository
import com.vipulkanade.myapplication.ui.data.SwitchState
import com.vipulkanade.myapplication.ui.data.SwitchViewModel
import com.vipulkanade.myapplication.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@ExperimentalCoroutinesApi
class SwitchViewModelTest {

    @get:Rule
    val rule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var switchRepository: SwitchRepository

    private lateinit var viewModel: SwitchViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = SwitchViewModel(switchRepository)
    }

    @Test
    fun `given switch state, when setFirstSwitch is called with checked true, then update switches stateflow`() = runBlocking {
        // given
        val switchState = SwitchState(true, false)
        `when`(switchRepository.getSwitches()).thenReturn(Resource.Success(switchState))

        // when
        viewModel.setFirstSwitch(true)

        // then
        val expectedSwitchState = SwitchState(true, false)
        assertEquals(expectedSwitchState, (viewModel.switches.value as Resource.Success).data)
    }

    @Test
    fun `given switch state, when setSecondSwitch is called with checked true, then update switches stateflow`() = runBlocking {
        // given
        val switchState = SwitchState(false, true)
        `when`(switchRepository.getSwitches()).thenReturn(Resource.Success(switchState))

        // when
        viewModel.setSecondSwitch(true)

        // then
        val expectedSwitchState = SwitchState(false, true)
        assertEquals(expectedSwitchState, (viewModel.switches.value as Resource.Success).data)
    }
}
