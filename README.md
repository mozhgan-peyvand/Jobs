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

# Optional features:

* Pagination: In the PopularMoviesFragment, the list is displayed to the user by pagination. But the Paging 3 library has not been used to implement this feature! Why? Because Paging 3 is very complicated and weird! For example, if you want to filter the data or change the color of one row of the list for any reason, you will face a severe challenge. For this reason, I have used a straightforward and, at the same time, flexible and testable solution that you can see.

* Night/Day mode: there is a button in the toolbar to change the theme to night and day mode.

# Package structure:
* base: This module includes api packages for provide retrofit and connecting with the server, models package for maintaining database models, routers package for maintaining routes, and an util package for maintaining extenstions and functions that are used as an app.
* builder: Since we use builder pattern, these modules have access to all modules, and you can use this module to make SDK and put it in any application. BottomNavigation , splash and roomProvider are in this module.
* buldeSrc: All the libraries that are used in the modules are placed in this module, and the custom plugin is also in this module, which is placed in all the modules to determine the build variant and so on.
* common-ui-view: Frequently used themes and dialogues for errors are used in this module, which are used in all other modules.
* feature-jobs: This module includes data, domain and ui modules related to jobs
* feature-user: This module includes data, domain and ui modules related to user
