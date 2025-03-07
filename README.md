# 🚀 GitView 

## 📌 Description

This application allows users to log in via GitHub using an OAuth token. After logging in, users can view their repositories and navigate to their pages.

## 🛠 Technologies

- Language: Kotlin
- Network requests: Retrofit
- OAuth: GitHub API

## 🔧 Installation and launch
### 1. Cloning a repository
```sh
git clone https://github.com/burzerus/GitView
cd GitView
```

### 2. Installing dependencies
Add it to the `build.gradle`:
```gradle
dependencies {
 implementation 'com.squareup.retrofit2:retrofit:2.9.0'
 implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
}
```


### 3. Launching the app
Run the project through Android Studio or use Gradle:
```sh
gradlew assembleDebug
```

# Authorization via GitHub
1. The user must create an authorization token.
2. Enter the personal token -> token in access.
3. After successful login, the application receives the user's repositories.
4. The token is used to make API requests to GitHub.

## 📄 API Routes
- `GET /user/repos` — list of user repositories
- `GET /repos/{owner}/{repo}` — information about the repository

## 📌 License
This project is distributed under the MIT license. The details are in the [LICENSE](LICENSE) file.

---
✉️ Project author: burzerus


