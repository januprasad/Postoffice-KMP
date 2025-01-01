package org.company.postoffice

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import org.company.postoffice.di.appModule
import org.company.postoffice.domain.model.PostOffice
import org.company.postoffice.domain.usecase.ResultState
import org.company.postoffice.viewmodel.MainViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

import postoffice.composeapp.generated.resources.Res
import postoffice.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        KoinApplication(
            application = {
                modules(appModule)
            }
        ) {
            AppContent()
        }
    }
}

@Composable
fun AppContent() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Post Office") })
        }, content = {
            Surface {
                MainView()
            }
        }
    )
}

@Composable
fun MainView(viewModel: MainViewModel = koinInject()) {
    LaunchedEffect(Unit) {
        viewModel.getPostOfficeData("678503")
    }
    var postOfficeData by remember { mutableStateOf<PostOffice?>(null) }
    val state by viewModel.postOffice.collectAsState()
    when (state) {
        is ResultState.ERROR -> {
            val error = (state as ResultState.ERROR).message
            ErrorBox(error)
        }

        is ResultState.LOADING -> {
            LoadingBox()
        }

        is ResultState.SUCCESS -> {
            val response = (state as ResultState.SUCCESS).response
            postOfficeData = response.PostOffice?.first()
        }
    }
    postOfficeData?.let {
        Text(
            text = "${it.Name}",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun ErrorBox(error: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        SelectionContainer {
            Text(
                text = error,
                color = Color.Red,
                fontSize = 24.sp
            )
        }
    }
}

@Composable
fun LoadingBox() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = Color.Blue
        )
    }
}