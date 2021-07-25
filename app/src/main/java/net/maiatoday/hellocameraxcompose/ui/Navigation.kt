package net.maiatoday.hellocameraxcompose.ui

import android.content.Intent
import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.maiatoday.hellocameraxcompose.CameraActivity

@ExperimentalAnimationApi
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationDirections.overview.destination) {
        composable(route = NavigationDirections.overview.destination) {
            val context = LocalContext.current
            OverviewScreen(
                onComposeCamera = {
                    Toast.makeText(context, "going to compose world camera", Toast.LENGTH_SHORT).show()
                    navController.navigate(NavigationDirections.cameraPreview.destination) },
                onViewCamera = {
                    Toast.makeText(context, "going to classic Android camera", Toast.LENGTH_SHORT).show()
                    navController.navigate(NavigationDirections.cameraPreviewView.destination) },
            )
        }
        composable(route = NavigationDirections.cameraPreview.destination) {
            PreviewScreen()
        }
        composable(route = NavigationDirections.cameraPreviewView.destination) {
            val context = LocalContext.current
            context.startActivity(Intent(context, CameraActivity::class.java))
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