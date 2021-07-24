package net.maiatoday.hellocameraxcompose.ui

import android.content.Intent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@ExperimentalAnimationApi
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationDirections.overview.destination) {
        composable(route = NavigationDirections.overview.destination) {
            OverviewScreen(
                onComposeCamera = { navController.navigate(NavigationDirections.cameraPreview.destination) },
                onViewCamera = { navController.navigate(NavigationDirections.cameraPreviewView.destination) },
            )
        }
        composable(route = NavigationDirections.cameraPreview.destination) {
            // navigate to old view fragment
//            val viewModel: CounterViewModel = hiltViewModel()
//            val count by viewModel.counter.collectAsState()
        //    CameraPreview()
        }
        composable(route = NavigationDirections.cameraPreviewView.destination) {
//            val context = LocalContext
//            context.startActivity(Intent(context, CameraActivity::class.java))
        }
    }
}

interface NavigationCommand {
    val arguments: List<NamedNavArgument>
    val destination: String
}

object NavigationDirections {
    val overview = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "overview"
    }
    val cameraPreview = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "cameraPreview"
    }

    val cameraPreviewView = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "cameraPreviewView"
    }
}