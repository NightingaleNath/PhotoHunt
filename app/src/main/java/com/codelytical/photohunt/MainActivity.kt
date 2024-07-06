package com.codelytical.photohunt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codelytical.photohunt.navigation.NavGraph
import com.codelytical.photohunt.ui.theme.PhotoHuntTheme
import com.codelytical.photohunt.viewmodel.HuntViewModel
import com.codelytical.photohunt.viewmodel.PredictionViewModel

class MainActivity : ComponentActivity() {
	private val huntViewModel: HuntViewModel by viewModels()
	private val predictionViewModel: PredictionViewModel by viewModels()
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			PhotoHuntApp(huntViewModel = huntViewModel, predictionViewModel = predictionViewModel)
		}
	}
}

@Composable
fun PhotoHuntApp(huntViewModel: HuntViewModel, predictionViewModel: PredictionViewModel) {
	PhotoHuntTheme {
		NavGraph(huntViewModel = huntViewModel, predictionViewModel = predictionViewModel)
	}
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	val huntViewModel = HuntViewModel()
	val predictionViewModel = PredictionViewModel()
	PhotoHuntApp(huntViewModel, predictionViewModel)
}