package com.codelytical.photohunt.ui.screens.huntview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codelytical.photohunt.navigation.Screen
import com.codelytical.photohunt.viewmodel.HuntViewModel

@Composable
fun HuntErrorScreen(navController: NavHostController,
                    huntViewModel: HuntViewModel) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "🫠", style = MaterialTheme.typography.displayLarge.copy(
                color = MaterialTheme.colorScheme.onPrimary))

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "AI decided to take a break...",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(50.dp))
        }

        Button(
            onClick = {
                huntViewModel.reset()
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) { inclusive = true }
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 35.dp)
        ) {
            Text(text = "Try again")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HuntErrorScreenPreview() {
    val navController = rememberNavController()
    val huntViewModel = HuntViewModel()
    HuntErrorScreen(navController, huntViewModel)
}