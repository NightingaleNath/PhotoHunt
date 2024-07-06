package com.codelytical.photohunt.ui.screens.itemhunt

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codelytical.photohunt.navigation.HandleBackPressToHome
import com.codelytical.photohunt.navigation.Screen
import com.codelytical.photohunt.ui.screens.itemhunt.components.HuntProgress
import com.codelytical.photohunt.viewmodel.HuntViewModel
import com.codelytical.photohunt.viewmodel.PredictionViewModel

@Composable
fun HuntItemPendingScreen(navController: NavHostController,
						  huntViewModel: HuntViewModel,
						  predictionViewModel: PredictionViewModel) {

	HandleBackPressToHome(navController, huntViewModel)

	val launcher = rememberLauncherForActivityResult(
		contract = ActivityResultContracts.TakePicturePreview()
	) { bitmap ->
		predictionViewModel.setCapturedImage(bitmap)
		predictionViewModel.predictImageName()
		navController.navigate(Screen.ItemValidating.route)
	}

	val currentItem = huntViewModel.currentItem.collectAsState()

	LaunchedEffect(huntViewModel) {
		huntViewModel.onFinished = {
			navController.navigate(Screen.Finish.route)
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
			Text(
				text = "Next item to find:",
				style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp, fontWeight = FontWeight.Medium)
			)
			Spacer(modifier = Modifier.height(10.dp))
			Text(
				text = currentItem.value ?: "No item",
				style = MaterialTheme.typography.displayLarge.copy(
					fontSize = 35.sp,
					fontWeight = FontWeight.W800)
			)
		}

		Row(
			horizontalArrangement = Arrangement.Center,
			verticalAlignment = Alignment.CenterVertically,
		) {
			OutlinedButton(onClick = {  huntViewModel.pickNextItem() }) {
				Text(text = "Skip")
			}

			Spacer(modifier = Modifier.width(8.dp))

			Button(onClick = { launcher.launch(null) }) {
				Text(text = "Take photo")
			}
		}

		Spacer(modifier = Modifier.height(35.dp))

	}
}

@Preview(showBackground = true)
@Composable
fun HuntItemPendingScreenPreview() {
	val navController = rememberNavController()
	val huntViewModel = HuntViewModel()
	val predictionViewModel = PredictionViewModel()
	HuntItemPendingScreen(navController, huntViewModel, predictionViewModel)
}
