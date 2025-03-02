package com.example.gitview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.gitview.databinding.FragmentDetailInfoBinding

class DetailInfoFragment : Fragment(R.layout.fragment_detail_info) {

    private var _binding: FragmentDetailInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repoName = arguments?.getString("repoName")
        val repoUrl = arguments?.getString("repoUrl")
        val repoStars = arguments?.getInt("repoStars") ?: 0
        val repoForks = arguments?.getInt("repoForks") ?: 0
        val repoWatchers = arguments?.getInt("repoWatchers") ?: 0

        // Устанавливаем название Toolbar
        (activity as? AppCompatActivity)?.supportActionBar?.title = repoName ?: "Repository"

        binding.repoUrl.text = repoUrl ?: "Нет ссылки"

        // Обновляем статистику (каждый элемент отдельно)
        binding.starsCount.text = "⭐ $repoStars"
        binding.forksCount.text = "🍴 $repoForks"
        binding.watchersCount.text = "👀 $repoWatchers"

        // Делаем ссылку кликабельной
        binding.repoUrl.setOnClickListener {
            repoUrl?.let {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                startActivity(intent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
