# Movies Assessment Application

A modern Android application for browsing and exploring movie information with user authentication and detailed movie descriptions.

## 🎬 Features

- **Secure Login System**: User authentication with username and student ID
- **Movie Dashboard**: Browse through a comprehensive list of movies
- **Detailed Movie Information**: View detailed descriptions, director, genre, and release year
- **Modern UI**: Clean and intuitive user interface
- **RESTful API Integration**: Seamless data fetching from backend services

## 🚀 Getting Started

### Prerequisites

- Android Studio Arctic Fox or later
- Android SDK 28 (API level 28) or higher
- Kotlin 1.8+
- Gradle 7.0+

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd MyAssssmentApplication
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an existing project"
   - Navigate to the project directory and select it

3. **Sync Project**
   - Android Studio will automatically sync the project
   - Wait for the Gradle sync to complete

4. **Run the Application**
   - Connect an Android device or start an emulator
   - Click the "Run" button or press `Shift + F10`

## 📱 App Structure

### Activities

- **MainActivity**: Login screen with username and student ID authentication
- **DashboardActivity**: Movie listing with RecyclerView and progress indicators
- **DetailsActivity**: Detailed movie information display

### Key Components

- **ApiService**: Retrofit-based API service for backend communication
- **EntityAdapter**: RecyclerView adapter for movie list display
- **RetrofitClient**: HTTP client configuration with logging

## 🎨 UI Components

### Login Screen
- Clean login form with username and student ID fields
- Movie-themed logo and branding
- Responsive design with proper spacing

### Dashboard
- Welcome message for authenticated users
- Movie count display
- Loading indicators during data fetch
- Scrollable movie list with dividers

### Movie Details
- Comprehensive movie information display
- Back navigation button
- Formatted subtitle with director, genre, and year
- Full description text

## 🔧 Technical Details

### Architecture
- **Language**: Kotlin
- **Architecture Pattern**: MVVM (Model-View-ViewModel)
- **Networking**: Retrofit with OkHttp
- **UI**: Android Views with RecyclerView

### Dependencies
- AndroidX Core KTX
- AndroidX AppCompat
- Material Design Components
- Retrofit for API calls
- Gson for JSON parsing
- OkHttp Logging Interceptor

### API Integration
- Base URL: Configured in RetrofitClient
- Authentication endpoint: `/sydney/auth`
- Dashboard endpoint: `/sydney/dashboard`
- Error handling with proper user feedback

## 📋 Usage

1. **Login**: Enter your first name and student ID to authenticate
2. **Browse Movies**: View the list of available movies on the dashboard
3. **View Details**: Tap on any movie to see detailed information
4. **Navigate Back**: Use the back button to return to the dashboard

## 🛠️ Development

### Project Structure
```
app/
├── src/main/
│   ├── java/com/example/myassssmentapplication/
│   │   ├── MainActivity.kt
│   │   ├── DashboardActivity.kt
│   │   ├── DetailsActivity.kt
│   │   ├── ApiService.kt
│   │   ├── EntityAdapter.kt
│   │   └── RetrofitClient.kt
│   ├── res/
│   │   ├── layout/
│   │   ├── values/
│   │   └── drawable/
│   └── AndroidManifest.xml
├── build.gradle.kts
└── proguard-rules.pro
```

### Building the Project
```bash
./gradlew assembleDebug
```

### Running Tests
```bash
./gradlew test
```

## 🎯 Future Enhancements

- [ ] Search functionality for movies
- [ ] Favorites/bookmark system
- [ ] Movie rating and reviews
- [ ] Dark theme support
- [ ] Offline data caching
- [ ] Push notifications for new releases

## 📄 License

This project is developed for assessment purposes. Please refer to your institution's guidelines for usage and distribution.

## 🤝 Contributing

This is an assessment project. For any questions or issues, please contact your course instructor.

---

**Note**: This application is designed for educational and assessment purposes. Ensure you have proper authorization before using any external APIs or services.
