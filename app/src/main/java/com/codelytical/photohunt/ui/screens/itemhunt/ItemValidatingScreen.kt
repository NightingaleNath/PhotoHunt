package com.codelytical.photohunt.ui.screens.itemhunt

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codelytical.photohunt.navigation.HandleBackPressToHome
import com.codelytical.photohunt.navigation.Screen
import com.codelytical.photohunt.ui.screens.itemhunt.components.HuntProgress
import com.codelytical.photohunt.viewmodel.HuntViewModel
import com.codelytical.photohunt.viewmodel.PredictionViewModel

@Composable
fun ItemValidatingScreen(navController: NavHostController,
                         huntViewModel: HuntViewModel,
                         predictionViewModel: PredictionViewModel
) {

    HandleBackPressToHome(navController, huntViewModel)

    val predictedName = predictionViewModel.predictedName.collectAsState()

    LaunchedEffect(predictedName.value) {
        if (predictedName.value != null) {
            Log.d("ItemValidatingScreen", "Predicted name does not match the current item: ${predictedName.value} - ${predictedName.value?.trim()?.lowercase() == huntViewModel.currentItem.value?.trim()?.lowercase()} ${huntViewModel.currentItem.value}")
            if (predictedName.value.equals(huntViewModel.currentItem.value, ignoreCase = true)) {
                predictionViewModel.resetRetryCount()
                navController.navigate(Screen.ItemValidationSuccess.route)
            } else {
                navController.navigate(Screen.ItemValidationFailure.route)
                Log.d("ItemValidatingScreen", "Predicted name does not match the current item")
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(15.dp))

        HuntProgress(huntViewModel)

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(text = "🤖", style = MaterialTheme.typography.displayLarge)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "AI is validating the photo...",
                style = MaterialTheme.typography.titleLarge)
        }

        Box {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 35.dp)
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ItemValidatingScreenPreview() {
    val navController = rememberNavController()
    val huntViewModel = HuntViewModel()
    val predictionViewModel = PredictionViewModel()
    ItemValidatingScreen(navController, huntViewModel, predictionViewModel)
}
