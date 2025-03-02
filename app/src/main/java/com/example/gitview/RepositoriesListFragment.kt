package com.example.gitview

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch

class RepositoriesListFragment : Fragment(R.layout.fragment_repositories_list) {
    private lateinit var adapter: RepositoriesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Устанавливаем название Toolbar
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Repositories"

        // Отключаем кнопку "Назад" в Toolbar
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)

        // Включаем поддержку меню
        setHasOptionsMenu(true)

        // Настройка RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Инициализация адаптера с передачей коллбэка
        adapter = RepositoriesAdapter { repo ->
            // Когда кликнули на репозиторий, передаем данные в DetailInfoFragment
            val bundle = Bundle().apply {
                putString("repoName", repo.name)
                putString("repoDescription", repo.description)
                putString("repoLanguage", repo.language)
                putString("repoUrl", repo.html_url)
                putInt("repoStars", repo.stargazers_count)
                putInt("repoForks", repo.forks_count)
                putInt("repoWatchers", repo.watchers_count)
            }
            findNavController().navigate(R.id.action_repositoriesListFragment_to_detailInfoFragment, bundle)
        }

        recyclerView.adapter = adapter

        // Получаем токен
        val token = arguments?.getString("token") ?: ""

        if (token.isNotEmpty()) {
            lifecycleScope.launch {
                try {
                    val repos = RetrofitClient.instance.getRepositories("token $token")
                    adapter.submitList(repos)  // Обновляем данные адаптера
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(context, "Ошибка загрузки репозиториев", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(context, "Токен не может быть пустым", Toast.LENGTH_SHORT).show()
        }
    }

    // Создаем меню в Toolbar
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_menu, menu) // Подключаем xml с меню
    }

    // Обработка нажатия на элементы меню (в данном случае, иконку выхода)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_exit -> {
                // Закрыть приложение
                requireActivity().finishAffinity()  // Закрыть все активити и завершить приложение
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

