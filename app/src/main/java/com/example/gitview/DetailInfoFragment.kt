package com.example.gitview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.gitview.databinding.FragmentDetailInfoBinding
import kotlinx.coroutines.launch
import android.util.Base64



class DetailInfoFragment : Fragment(R.layout.fragment_detail_info) {

    private var _binding: FragmentDetailInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var repositoriesAdapter: RepositoriesAdapter

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
        val repoOwner = arguments?.getString("repoOwner")

        // Устанавливаем название Toolbar
        (activity as? AppCompatActivity)?.supportActionBar?.title = repoName ?: "Repository"

        binding.repoUrl.text = repoUrl ?: "Нет ссылки"
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

        // Загружаем README.md
        val token = "token $repoOwner"  // Передайте токен и владельца
        val owner = repoOwner ?: "default_owner" // Получаем имя владельца
        val repo = repoName ?: "default_repo"   // Получаем имя репозитория

        lifecycleScope.launch {
            try {
                // Получаем содержимое README.md
                val response = RetrofitClient.instance.getReadme(token, owner, repo)

                if (response != null && response.content != null) {
                    val content = response.content

                    // Декодируем содержимое из Base64
                    val decodedContent = String(Base64.decode(content, Base64.DEFAULT))

                    // Обновляем репозиторий с контентом README.md
                    val repository = Repository(
                        name = repoName ?: "Unknown",
                        html_url = repoUrl ?: "",
                        description = "Description here",  // Ожидается описание, его можно передать через аргументы
                        language = "Kotlin",               // Например, язык репозитория
                        stargazers_count = repoStars,
                        forks_count = repoForks,
                        watchers_count = repoWatchers,
                        readmeContent = decodedContent // Добавляем содержимое README.md
                    )

                    // Обновляем адаптер с новым репозиторием
                    repositoriesAdapter.submitList(listOf(repository))

                } else {
                    Toast.makeText(context, "Не удалось загрузить README", Toast.LENGTH_SHORT).show()
                }

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, "Ошибка загрузки README", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
