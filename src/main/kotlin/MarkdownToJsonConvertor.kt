import java.lang.StringBuilder
import kotlin.math.log

val input = """

- https://github.com/happysingh23828/Android-Clean-Architecture

    - This is a sample movie list  Android application 📱 built to demonstrate use of [**Clean Architecture**](http://github.com/happysingh23828/Android-Clean-Architecture#what-is-clean-architecture) tools with [**80% code coverage**](http://github.com/happysingh23828/Android-Clean-Architecture#--code-coverage-reports). Dedicated to all Android Developers with ❤️. 

    - Tech Stack = Dagger, Unit Testing for modules, Mockito, RxJava, Retrofit, Room, CI-CD, SOLID, Code Coverage, Jacoco, Detekt, ktlint, Stetho, LiveData, ViewModel


- https://github.com/igorwojda/android-showcase

    - 💎 Android application following best practices: Kotlin, coroutines, Clean Architecture, feature modules, tests, MVVM, static analysis...

    - Tech Stack = Kodein, Coroutines, Testing, Retrofit, KAndroid, Lottie, Detekt, Navigation, Dynamic Feature Modules, LiveData, ViewModel


- https://github.com/ferPrieto/Coroutines-Flows-Modularised

    - Clean Architecture Modular Project.

    - Tech Stack = Dagger, Coroutines, Testing, Retrofit, Data Binding, Navigation, LiveData, ViewModel


- https://github.com/akoufa/CoolWeather

    - Weather App that uses Android best practices. Android Jetpack, clean architecture. Written in Kotlin

    - Tech Stack = Dagger Hilt, Coroutines, Testing, Retrofit, Room, Navigation, LiveData, ViewModel

- https://github.com/odaridavid/Clean-MVVM-ArchComponents

    - A Star Wars API app that lets you search for characters, view details about them and save your favorite
     characters.

    - Tech Stack = Koin, Coroutines, Testing, Retrofit, Room, Data Binding, Motion Layout, LiveData, ViewModel
    
- https://github.com/andremion/Theatre

    - Pet project using Clean Architecture + MVVM + Reactive Extensions + Android Architecture Components. The data are fetched from LondonTheatreDirect API. performing_arts

    - Tech Stack = Dagger, RxJava, Testing, Retrofit, Room, Navigation, Data Binding, LiveData, ViewModel
    
- https://github.com/HamdiBoumaiza/CoronavirusWorldStatus

    - An Android App that shows the stats of coronavirus worldwide and per country .
    
    - Tech Stack = Dagger, Coroutines, Retrofit, Room, LiveData, ViewModel , Stetho
    
- https://github.com/SmartToolFactory/PropertyFindAR

    - 🏘 Real Estate App with many screens written with RxJava3+Flow with offline first/last using dynamic feature modules, and TDD, used ConcatAdapter to have RV with different layouts.
     
    - Tech Stack = RxJava3, Coroutines Flow, Retrofit, Room, Dagger Hilt, Dynamic Feature Modules, ConcatAdapter, LiveData, ViewModel, SavedStateHandle, WorkManager, Glide, Lottie, MpCharts, MockWebServer, MockK, FlowTestObserver, ktLint, detekt, Git Hooks, Git Flow
    
- https://github.com/sansets/android-clean-architecture

    - Food recipe app that uses Modularization, Single Activity Architecture, and Clean Architecture.
    
    - Tech Stack = Navigation Component, Dagger, Coroutines Flow, Room, Retrofit, LiveData, ViewModel, View Binding, Dynamic Feature Modules.
    
- https://github.com/VladimirWrites/BLTaxi

    - 🚕 BL Taxi is a simple app for calling a taxi in the city Banja Luka built using modern Android development tools (Clean Architecture, Kotlin, Coroutines, MVVM, Testing)

    - Tech Stack = Koin, Retrofit, Room, Data Binding, Live Data, View Model, Work Manager, Material Components
    
    
""".trimIndent()

private val urlRegEx = "(?<url>https:\\/\\/github\\.com\\/.+?\\/.+)".toRegex()
private val techStackRegEx = "Tech Stack = (?<stack>.+)".toRegex()

fun main(args: Array<String>) {
    val urlResults = urlRegEx.findAll(input)
    val urls = mutableListOf<String>()
    for ((index, url) in urlResults.withIndex()) {
        urls.add(url.value)
    }

    val stackResults = techStackRegEx.findAll(input)
    val stacks = mutableListOf<String>()
    for ((index, stack) in stackResults.withIndex()) {
        stacks.add(stack.value)
    }

    println(stacks.size)
    println(urls.size)
    // require(stacks.size == urls.size) { "Failed" }

    val sb = StringBuilder()
    for ((index, url) in urls.withIndex()) {
        val stack = stacks[index]
        val project = """
            {
                "github_url": "$url",
                "stack": "${stack.split("=")[1].trim()}"
            },
        """.trimIndent()
        sb.append(project)
    }

    println(sb)

}