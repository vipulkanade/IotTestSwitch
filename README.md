# Android App with Jetpack Compose, ViewModel, Retrofit
This is an Android app that demonstrates the use of Jetpack Compose, ViewModel, Retrofit to control two switches in a smart home. 
The app uses Hilt for dependency injection and follows the repository pattern for network calls. 
The app also displays a loading state and error message when there is no network connectivity.

# Features
The app includes the following features:

Two switches that can be toggled on and off.
- A loading state when making network requests.
- An error message when there is no network connectivity.
- Hilt for dependency injection.
- Repository pattern for network calls.
- Retrofit for network calls.
- ViewModel for each switch.
- Jetpack Compose for UI.

# Tech Stack
The app uses the following libraries:

- Android Architecture Components: ViewModel
- Hilt for dependency injection
- Retrofit for network calls
- Okhttp for HTTP client
- Jetpack Compose for UI

# Architecture
The app follows the MVVM architecture pattern. It consists of three layers:

- View: Jetpack Compose UI code
- ViewModel: Android Architecture ViewModel code
- Repository: Retrofit network calls and data sources

# Installation
Clone this repository and open the project in Android Studio. Run the app on an Android device or emulator.

# License
This project is licensed under the MIT License. See the LICENSE file for more information.
