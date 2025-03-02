package com.example.gitview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.gitview.databinding.FragmentAuthBinding

class AuthFragment : Fragment() {

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Скрываем Toolbar
        (activity as? AppCompatActivity)?.supportActionBar?.hide()



        // Обработчик нажатия на кнопку "SIGN IN"
        binding.button.setOnClickListener {
            val accessToken = binding.AccessTextView.text.toString()

            if (accessToken.isNotEmpty()) {
                val bundle = Bundle().apply {
                    putString("token", accessToken)
                }
                findNavController().navigate(R.id.action_authFragment_to_repositoriesListFragment, bundle)
            } else {
                Toast.makeText(requireContext(), "Please enter a valid token", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Показываем Toolbar снова, когда фрагмент уничтожается
        (activity as? AppCompatActivity)?.supportActionBar?.show()
        _binding = null
    }

}
