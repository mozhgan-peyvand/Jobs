# Jobs
Example MVI implementation, based on Google's architectural samples.

# Project features 🚀
100% Kotlin

Kotlin Coroutines with Flow

Clean Architecture with MVI (Uni-directional data flow)

Modern architecture (Clean Architecture, Model-View-ViewModel)

Navigation, single-activity architecture with Jetpack Navigation

Cache local data with Room Persistence Library

ViewModel, LiveData, Lifecycle, ... with Android Jetpack

Dependency injection hilt

Gradle Kotlin DSL, Custom plugin

Optional features:
* Pagination: In the PopularMoviesFragment, the list is displayed to the user by pagination. But the Paging 3 library has not been used to implement this feature! Why? Because Paging 3 is very complicated and weird! For example, if you want to filter the data or change the color of one row of the list for any reason, you will face a severe challenge. For this reason, I have used a straightforward and, at the same time, flexible and testable solution that you can see.
* Night/Day mode: there is a button in the toolbar to change the theme to night and day mode.
