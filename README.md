# Workit

Workit is an Android application developed in Kotlin that helps users find and explore job opportunities. The app provides a modern and user-friendly interface for job searching and company exploration.

## Features

- User authentication (Login/Register)
- Job listings with detailed information
- Company profiles and information
- Bottom navigation for easy access to different sections:
  - Home
  - Dashboard
  - Notifications
- Google and Apple sign-in options
- Password recovery functionality

## Technical Details

- **Language**: Kotlin
- **Minimum SDK**: 34
- **Target SDK**: 35
- **Build System**: Gradle (Kotlin DSL)

## Project Structure

The app follows a modern Android architecture with the following main components:

- `activities`: Main screens like HomePage, LoginPage, RegisterPage, and DetailPage
- `fragments`: UI components for different sections
- `adapters`: RecyclerView adapters for jobs and companies
- `models`: Data classes for Job and Company items
- `utils`: Helper classes and database operations

## Setup and Installation

1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle files
4. Run the application on an emulator or physical device

## Dependencies

- AndroidX Core KTX
- AndroidX AppCompat
- Material Design Components
- ConstraintLayout
- ViewBinding
- Navigation Components

## Build Configuration

```kotlin
android {
    namespace = "com.example.workit"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.workit"
        minSdk = 34
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }
}
```

## Contributing

Feel free to contribute to this project by submitting issues and/or pull requests.

## License

This project is licensed under the MIT License - see the LICENSE file for details.
