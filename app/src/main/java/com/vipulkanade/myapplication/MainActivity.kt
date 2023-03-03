package com.vipulkanade.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vipulkanade.myapplication.ui.data.AppSwitchViewModel
import com.vipulkanade.myapplication.ui.theme.FordInterviewTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vipulkanade.myapplication.ui.data.PhysicalViewModel
import com.vipulkanade.myapplication.ui.data.SwitchViewModel
import com.vipulkanade.myapplication.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FordInterviewTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SwitchScreen()
                }
            }
        }
    }
}

@Composable
fun SwitchView(
    modifier: Modifier = Modifier,
    appSwitchViewModel: AppSwitchViewModel = hiltViewModel(),
    physicalViewModel: PhysicalViewModel = hiltViewModel()
) {
    val appSwitchUiState = appSwitchViewModel.uiState.collectAsState()
    val physicalSwitchUiState = physicalViewModel.uiState.collectAsState()

    Column {
        Column {
            Text(text = "App Switch")
            Switch(
                checked = appSwitchUiState.value.isSwitchOn,
                onCheckedChange = {
                    appSwitchViewModel.updateSwitchStatus(it)
                }
            )
        }

        Column {
            Text(text = "Physical")
            Switch(
                checked = physicalSwitchUiState.value.isSwitchOn,
                onCheckedChange = {
                    physicalViewModel.updateSwitchStatus(it)
                }
            )
        }
    }
}


@Composable
fun SwitchScreen(
    modifier: Modifier = Modifier,
    viewModel: SwitchViewModel = hiltViewModel()
) {
    val switchState by viewModel.switches.collectAsState()

    when (switchState) {
        is Resource.Success -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                switchState.data?.firstSwitch?.let {
                    Switch(
                        checked = it,
                        onCheckedChange = { viewModel.setFirstSwitch(it) }
                    )
                }
                switchState.data?.secondSwitch?.let {
                    Switch(
                        checked = it,
                        onCheckedChange = { viewModel.setSecondSwitch(it) }
                    )
                }
            }
        }
        is Resource.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is Resource.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Text(
                        text = switchState.message ?: "An error occurred",
                        style = MaterialTheme.typography.h5,
                    )

                    Spacer(modifier = modifier.padding(16.dp))

                    SwitchView()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FordInterviewTheme {
        SwitchScreen()
    }
}