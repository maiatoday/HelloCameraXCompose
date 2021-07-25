package net.maiatoday.hellocameraxcompose

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.ImageCapture
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import net.maiatoday.hellocameraxcompose.ui.CameraPreview
import net.maiatoday.hellocameraxcompose.ui.theme.HelloCameraXComposeTheme

/**
 * A simple [Fragment] subclass To hold the compose version of the camera implementaion
 */
class CameraComposeFragment : Fragment() {
    private var imageCapture: ImageCapture? = ImageCapture.Builder()
        .build()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                HelloCameraXComposeTheme() {
                    Box(Modifier.fillMaxSize()) {
                        CameraPreview(imageCapture)
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Bottom,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(onClick = {
                                takePhoto(imageCapture, requireContext())
                            }) {
                                Text(text = getString(R.string.take_photo))
                            }
                        }
                    }
                }
            }
        }
    }
}