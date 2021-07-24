package net.maiatoday.hellocameraxcompose.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import net.maiatoday.hellocameraxcompose.ui.theme.HelloCameraXComposeTheme

@Composable
fun OverviewScreen(
    onComposeCamera: () -> Unit = {},
    onViewCamera: () -> Unit = {}
) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = onComposeCamera) {
                Text("CameraX in compose")
            }
            Button(onClick = onViewCamera) {
                Text("CameraX view")
            }
        }
    }
}

@Preview
@Composable
private fun DefaultPreview () {
    HelloCameraXComposeTheme {
        OverviewScreen()
    }
}