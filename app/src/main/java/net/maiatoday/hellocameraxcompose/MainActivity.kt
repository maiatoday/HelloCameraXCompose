package net.maiatoday.hellocameraxcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import net.maiatoday.hellocameraxcompose.ui.Navigation
import net.maiatoday.hellocameraxcompose.ui.theme.HelloCameraXComposeTheme

class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                HelloCameraXApp()
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun HelloCameraXApp() {
    HelloCameraXComposeTheme {
        val navController = rememberNavController()
        Navigation(navController)
    }
}