<div align="center">

<img src="https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white"/>
<img src="https://img.shields.io/badge/Language-Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white"/>

</div>
<br/><br/>

# GreenBin-FIT2025

GreenBin is an Android application designed to promote and incentivize proper waste management. Users can deposit trash at designated smart bins, earn points for their contributions, and redeem these points for various rewards. The app aims to create a community of environmentally conscious individuals through features like a competitive leaderboard and educational articles.

## Features

-   **User Authentication**: Secure user registration and login functionality.
-   **QR Code Scanning**: Scan QR codes on smart bins to initiate a trash deposit transaction using CameraX and ML Kit.
-   **Points & Rewards System**: Earn points for depositing waste and redeem them for a variety of rewards.
-   **Monthly Leaderboard**: Compete with other users and see who has contributed the most each month.
-   **Transaction History**: Keep track of all point earnings and reward redemptions.
-   **User Profile**: View your profile details, current points, and earned badges (e.g., Eco Starter, Eco Warrior).
-   **Educational Articles**: Access a list of articles to learn more about recycling and sustainability.
-   **Point Calculator**: An in-app tool to estimate the points you can earn based on the weight of your waste.

## App Architecture

This project follows the **MVVM (Model-View-ViewModel)** architectural pattern to create a scalable and maintainable codebase.

-   **View**: Activities and Fragments (`ui` package) are responsible for displaying data and forwarding user interactions to the ViewModel.
-   **ViewModel**: (`viewmodel` package) Holds and manages UI-related data, survives configuration changes, and communicates with the Repository.
-   **Model**: Data classes (`model` package) representing API requests and responses.
-   **Repository**: (`repository` package) Acts as a single source of truth for data, abstracting the data source (network API).
-   **Networking**: **Retrofit** is used for handling RESTful API calls to the backend.
-   **Asynchronous Operations**: **Kotlin Coroutines** are used for managing background threads and asynchronous tasks.
-   **Dependency Management**: Gradle with `libs.versions.toml` for managing project dependencies.
-   **Local Storage**: **DataStore** is used for persisting the user's authentication token.

## API Integration

The application communicates with a custom backend API to handle user data, transactions, and content.

### Base URL
`https://apigreenbin.kitobujavateknik.com/api/v1/`

### Key Endpoints
-   `/auth/login` & `/auth/register`: User authentication.
-   `/profile`, `/profile/point`, `/profile/badge`: Fetch user-specific data.
-   `/transaction/create`: Create a new transaction for depositing trash or redeeming a reward.
-   `/transaction/history`: Get the user's point history.
-   `/reward/get-all` & `/reward/detail/{id}`: Fetch available rewards.
-   `/article/get-all` & `/article/detail/{id}`: Fetch educational articles.
-   `/leaderboard`: Get the monthly leaderboard rankings.
