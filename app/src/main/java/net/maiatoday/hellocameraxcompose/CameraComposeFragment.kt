package net.maiatoday.hellocameraxcompose

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import net.maiatoday.hellocameraxcompose.databinding.FragmentCameraComposeBinding

/**
 * A simple [Fragment] subclass To hold the compose version of the camera implementaion
 */
class CameraComposeFragment : Fragment() {

    private var _binding: FragmentCameraComposeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCameraComposeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonComposePrev.setOnClickListener {
            findNavController().navigate(R.id.action_ComposeFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}