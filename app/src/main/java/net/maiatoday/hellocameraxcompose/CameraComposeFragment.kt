package net.maiatoday.hellocameraxcompose

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.fragment.findNavController
import net.maiatoday.hellocameraxcompose.ui.theme.HelloCameraXComposeTheme

/**
 * A simple [Fragment] subclass To hold the compose version of the camera implementaion
 */
class CameraComposeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                HelloCameraXComposeTheme() {
                    Column {
                        Text(text = "Hello world.")
                        Button(onClick = {
                            // TODO: 2021/07/25  take photo stuff here
                        }) {
                            Text(text = getString(R.string.take_photo))
                        }
                        Button(onClick = {
                            findNavController().navigate(R.id.action_ComposeFragment_to_FirstFragment)
                        }) {
                            Text(text = getString(R.string.previous))
                        }
                    }
                }
            }
        }
    }
}