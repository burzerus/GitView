# 🚀 GitView 

## 📌 Описание
Это приложение позволяет пользователям авторизоваться через GitHub с использованием OAuth-токена. После авторизации пользователи могут просматривать свои репозитории и переходить на их страницы.

## 🛠 Технологии
- Язык: Kotlin
- Сетевые запросы: Retrofit
- OAuth: GitHub API

## 🔧 Установка и запуск
### 1. Клонирование репозитория
```sh
git clone https://github.com/burzerus/GitView
cd GitView
```

### 2. Установка зависимостей
Добавьте в `build.gradle`:
```gradle
dependencies {
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
}
```



### 3. Запуск приложения
Запустите проект через Android Studio или используйте Gradle:
```sh
gradlew assembleDebug
```

## 🔑 Авторизация через GitHub
1. Пользователь должен создать токен авторизации .
2. Ввести в access personal token -> token.
3. После успешного входа приложение получает репозитории пользователя.
4. Токен используется для выполнения API-запросов к GitHub.

## 📄 API Маршруты
- `GET /user/repos` — список репозиториев пользователя
- `GET /repos/{owner}/{repo}` — информация о репозитории

## 📌 Лицензия
Этот проект распространяется под лицензией MIT. Подробности в файле [LICENSE](LICENSE).

---
✉️ Автор проекта: [burzerus]


