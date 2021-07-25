package net.maiatoday.hellocameraxcompose

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import net.maiatoday.hellocameraxcompose.databinding.FragmentCameraClassicBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CameraClassicFragment : Fragment() {

    private var _binding: FragmentCameraClassicBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCameraClassicBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonClassicPrev.setOnClickListener {
            findNavController().navigate(R.id.action_ClassicFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}