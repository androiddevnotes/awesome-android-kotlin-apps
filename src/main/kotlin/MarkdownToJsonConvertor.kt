import java.lang.StringBuilder
import kotlin.math.log

val input = """
    
    #### Normal

    - https://github.com/PatilShreyas/NotyKT

        - 📒 NotyKT is a complete 💎Kotlin-stack (Backend + Android) 📱 application built to demonstrate the use of Modern development tools with best practices implementation.

        - Tech Stack = Backend - Ktor, PostgreSQL; Android = Coroutines, Flow, Navigation Architecture, MVVM, LiveData, ViewModel, Room DB, DataStore, Jetpack Security, WorkManager, Dagger Hilt DI, Jetpack Compose, Material UI, Retrofit, Moshi

    - https://github.com/Sharkaboi/DrawingsApp

        - An app to add and manage floor plan drawings with markers.

        - Tech Stack = Dagger Hilt, Coroutines, Room, Dhaval2404/ImagePicker, Subsampling Scale Image View, Navigation, LiveData, ViewModel 

    - https://github.com/b-lam/Resplash

        - Unofficial Unsplash Android App

        - Tech Stack = Koin, Coroutines, Testing, Retrofit, Room, Firestore, Firebase In-App Messaging, Paging, Navigation, Google Play Billing, Muzei, LiveData, ViewModel  

    - https://github.com/utsmannn/hiya-hiya-hiya

        - Whatsapp Clone base on Firebase Cloud Messaging

        - Tech Stack = Koin, Coroutines, Retrofit, Room, Firebase Messaging, Firebase Auth, WorkManager, Google Maps, Paging,  JSoup, vanniktech/Emoji, afollestad/inline-activity-result, LiveData, ViewModel

    - https://github.com/ashwini009/TvFlix

      - TVFlix connects with TVDB API to give you popular shows and let you mark anyone as favorite.

      - Tech Stack = Dagger Hilt, Coroutines, Testing, Retrofit, Room, Paging, Navigation, LiveData, ViewModel

    - https://github.com/fevziomurtekin/DeezerClone

        - Deezer Clone application. Fetching data from the network and integrating local data in the database via repository pattern.

        - Tech Stack = Dagger Hilt, Coroutines, Flow, Jetpack (Room, ViewModel,Navigation LiveData), Retrofit, Paging, Testing
      
    - https://github.com/vidit135g/Replify-Messenger [Kotlin + Java]

        - Minimal text messenger with a ton of features.


        - Tech Stack = Dagger, Coroutines, RxJava, Testing, Realm, ExoPlayer, Conductor, Mixpanel Android, libphonenumber-android, Call Control DataShare, LiveData, ViewModel


    - https://github.com/jnkforks/PokemonGo

        - Jetpack 实战项目 PokemonGo（神奇宝贝）基于 MVVM 架构和 Repository 设计模式


        - Tech Stack = Dagger Hilt, Koin, Coroutines, RxJava, Testing, Retrofit, Room, WorkManager, Paging, LiveData, ViewModel


    - https://github.com/andyb129/AppDevToolbox

        - Collection of tools for Android app development in one place 🔧 🔨


        - Tech Stack =  Dagger, Coroutines, RxJava, Room, Venom, LiveData, ViewModel


    - https://github.com/nahzur-h/awaker

        - article app for android


        - Tech Stack = RxJava, Testing, Retrofit, Room, ExoPlayer, LiveData, ViewModel


    - https://github.com/theapache64/topcorn

        - A minimalistic movie listing app to browse IMDB's top 250 movies.


        - Tech Stack = Dagger, Coroutines, Testing, Retrofit, Room, LiveData, ViewModel


    - https://github.com/vmiklos/plees-tracker

        - Plees Tracker is a simple sleep tracker for your Android phone.


        - Tech Stack = Room, LiveData, ViewModel


    - https://github.com/Chesire/Nekome

        - Android application to manage tracked Anime and Manga lists.


        - Tech Stack = Dagger, Coroutines, Testing, Retrofit, Room, WorkManager, Navigation, LiveData, ViewModel

    - https://github.com/ardakazanci/Heyyoo

        - Heyyoo is a sample social media Android application 📱 built to demonstrate use of Modern Android development tools.

        - Tech Stack = Coroutines, Retrofit, Room, Algolia, LocGetter, EasyValidation, Dexter, Splashy, secure-preferences, Paging, Data Binding, Navigation, LiveData, ViewModel


    - https://github.com/lulululbj/wanandroid/

        - Jetpack MVVM For Wanandroid 最佳实践 ！


        - Tech Stack = Koin, Coroutines, Testing, Retrofit, Navigation, FlowLayout, LiveData, ViewModel

    - https://github.com/SIKV/Photos

        - Browse, search, download, and share amazing free photos provided by talented photographers on Unsplash and Pexels.


        - Tech Stack = Dagger, Coroutines, RxJava, Testing, Retrofit, Room, Firestore, Firebase-ML, Firebase-Analytics, Paging, Navigation, LiveData, ViewModel


    - https://github.com/ValterKasper/space-app

        - An android app that shows time-line of upcoming rocket launches.


        - Tech Stack = Dagger, Coroutines, Testing, Retrofit, Room, WorkManager, Data Binding, Navigation, LiveData, ViewModel


    - https://github.com/nominalista/expenses

        - App written in Kotlin for budget tracking.


        - Tech Stack = Dagger, Coroutines, RxJava, Testing, Retrofit, Room, Firestore, Firebase Auth, Firebase Messaging, WorkManager, Navigation, LiveData, ViewModel


    - https://github.com/lmj0011/jetpack-release-tracker

        - Stay up to date on the latest AndroidX library releases.


        - Tech Stack = Coroutines, Testing, Fuel, Room,  WorkManager, Data Binding, Navigation, LiveData, ViewModel


    - https://github.com/willowtreeapps/vocable-android

        - Vocable AAC allows those with conditions such as MS, stroke, ALS, or spinal cord injuries to communicate using an app that tracks head movements, without the need to spend tens of thousands of dollars on technology to do so.


        - Tech Stack = Koin, Coroutines, Testing, Room, Data Binding, AR, LiveData, ViewModel



    - https://github.com/Shashank02051997/GitExplorer-Android

        - Find the right git commands 🔥 without digging through the web.😊😊😉


        - Tech Stack = Kotlin, LiveData, ViewModel


    - https://github.com/VIPyinzhiwei/Eyepetizer

        - 🔥基于 Kotlin 语言仿写「开眼 Eyepetizer」的一个短视频 Android 客户端项目，采用 Jetpack + 协程实现的 MVVM 架构。


        - Tech Stack = Coroutines, Testing, Retrofit, WorkManager, GSYVideoPlayer, Data Binding, PermissionX, EventBus, LiveData, ViewModel


    - https://github.com/PatilShreyas/Foodium

        - 🍲Foodium is a sample food blog Android application 📱


        - Tech Stack = Dagger Hilt, Coroutines, Testing, Retrofit, Room, LiveData, ViewModel


    - https://github.com/michaldrabik/Showly-2.0

        - Showly 2.0 is modern, slick, open-sourced and completely free Android TV Shows Tracker.


        - Tech Stack = Dagger, Coroutines, Testing, Retrofit, Room, Firebase Messaging, WorkManager, Navigation, Dynamicanimation, LiveData, ViewModel

    - https://github.com/bernaferrari/ChangeDetection

        - Automatically track websites changes on Android in background.

        - Tech Stack = Dagger, Coroutines, RxJava, Retrofit, Room, Firestore, WorkManager, Paging, Data Binding, Navigation, JSoup, js-evaluator-for-android, LiveData, ViewModel


    - https://github.com/YahiaAngelo/Noted-Android

        - Notes and Tasks app where you can add notes and tasks with Reminders also with categories.

        - Tech Stack = Koin dependency injection, Coroutines, Realm db, Material Components, Markdown, Navigation, LiveData, ViewModel


    - https://github.com/igorescodro/alkaa

        - A simple to-do app created to study the latest components, architecture, tools, and APIs released in Android Platform. The project evolved a lot in the last year and now is available on Google Play! :heart:


        - Tech Stack = Koin, Espresso, UiAutomator, Mockk, Coroutines, Navigation, Room, MotionLayout, KTX, Modularization, Dynamic Delivery, Dark Theme, klint, Detekt, codebeat, CodeFactor, Codacy, MPAndroidChart, Groupie, LiveData, ViewModel

    - https://github.com/HariKulhari06/Covid-19-Tracker

        - A Sample to track COVID-19 cases in India and globally.

        - Tech Stack = Dagger Hilt, Coroutines, Retrofit, Room, Firestore, WorkManager, Navigation, MPAndroidChart, LiveData, ViewModel


    - https://github.com/zedlabs/WallPortal

        - Minimal Wallpapers for Android. The goal of this project is to create a responsive application and with optimized networking, a good place to see implementation. moved to wallhaven.cc api from unsplashed api due to rate limits.

        - Tech Stack = Dagger Hilt, Coroutines, Retrofit, Room, Paging, Navigation, LiveData, ViewModel


    - https://github.com/ricknout/rugby-ranker

        - An Android app for viewing and predicting the latest World Rugby rankings 🏉

        
        - Tech Stack = Dagger Hilt, Coroutines, Testing, Retrofit, Room, WorkManager, Navigation, insetter, LiveData, ViewModel


    - https://github.com/GeorgCantor/WallpaperApp
        
        - **WallpaperApp** App for viewing and downloading wallpapers.
        
        - Tech Stack = Koin, Coroutines, Retrofit, Room, Lottie, Zoomy, Navigation, LiveData, ViewModel



    - https://github.com/DheerajKotwani/GithubVisualizer

        - **Github Visualizer** Android Application to track any user activity on Github built using the Github Developers Api. Implementation for almost all methods Github Developers Api.🔥🔥

        - Tech Stack = Retrofit, Firebase Auth, Coroutines, LiveData, ViewModel


    - https://github.com/PHELAT/Tedu

        - **📝 Tedu** Todo app, but minimal, open-source and free. It lets you to sync your todos on your cloud provider. This project is architected in a modular structure and you can learn a lot from it.

        - Tech Stack = Dagger, Room, Coroutines, Firebase Messaging, Navigation, LiveData, ViewModel

    - https://github.com/abhinav0612/PasswordVault

        - **:lock: Password Vault** is an all in one offline password storing application where you can also store debit/credit cards and bank details with one :key: Master PIN.

        - Tech Stack = Dagger Hilt, Room, Navigation, LiveData, ViewModel


    - https://github.com/TheCodeMonks/NYTimes-App

        - **🗽 NY Times App** is an Minimal News 🗞 Android application built to describe the use of JSoup with Modern Android development tools.


        - Tech Stack = Coroutines, Room, JSoup, Navigation, LiveData, ViewModel


    - https://github.com/satyamurti/LetsChat

        - **An opensource Indian chat app** with new cool concepts.


        - Tech Stack = Coroutines, Retrofit, Firestore, Firebase Auth, Firebase Messaging, Firebase Storage, Cloud Functions, Data Binding, Navigation, LiveData, ViewModel


    - https://github.com/mayokunthefirst/Instant-Weather

        - Instant Weather fetches data from the OpenWeatherMap API.


        - Tech Stack = Dagger, Coroutines, Testing, Retrofit, Room, WorkManager, Paging, Data Binding, Navigation, Algolia Search, LiveData, ViewModel


    - https://github.com/qingmei2/MVVM-Architecture

        - The practice of MVVM + Jetpack architecture in Android.


        - Tech Stack = Dagger Hilt, Coroutines, RxJava, Testing, Retrofit, Room, Paging, Navigation, LiveData, ViewModel


    - https://github.com/nuhkoca/libbra

        - A currency tracker app demonstration.


        - Tech Stack = Dagger, Coroutines, Testing, Retrofit, Data Binding, Navigation, LiveData, ViewModel


    - https://github.com/adityam49/Updoot

        - A reddit client built for android


        - Tech Stack = Dagger, Coroutines, Testing, Retrofit, Room, WorkManager, Data Binding, Navigation, LiveData, ViewModel


    - https://github.com/weylar/Movie

        - A simple movie app


        - Tech Stack = Dagger, Coroutines, Retrofit, Room, WorkManager, Paging, Data Binding, Navigation, LiveData, ViewModel


    - https://github.com/xiaoyanger0825/wanandroid

        - Kotlin+JetPack+协程实现的MVVM架构Wanandroid客户端


        - Tech Stack = Coroutines, Retrofit, Room, LiveData, ViewModel


    - https://github.com/gs-ts/TrackMyPath

        - An android app that tracks your walk with images every 100 meters.


        - Tech Stack = Koin, Coroutines, Testing, Retrofit, Room, LiveData, ViewModel


    - https://github.com/KumarManas04/NotesSync

        - It can encrypt and sync notes to the user's own Google Drive or Dropbox accounts.


        - Tech Stack = Coroutines, Testing, Room, Google Drive, Dropbox, WorkManager, Navigation, LiveData, ViewModel


    - https://github.com/CalvinNor/MovieMan/

        - An open-source Android app for viewing Movies / TV information.


        - Tech Stack = Koin, Coroutines, Testing, Retrofit, Room, Navigation, LiveData, ViewModel


    - https://github.com/commonpepper/Photosen

        - Android app for viewing and downloading Flickr photos.


        - Tech Stack = Retrofit, Room, Paging, LiveData, ViewModel


    - https://github.com/OMIsie11/SpaceXFollower

        - Android app that helps You keep up with SpaceX 🚀


        - Tech Stack = Koin, Coroutines, Testing, Retrofit, Room, WorkManager, MPAndroidChart, Navigation, LiveData, ViewModel


    - https://github.com/dievskiy/feedapp

        - Calorie tracker for android that supports recipes and products search.


        - Tech Stack = Dagger, Coroutines, RxJava, Testing, Retrofit, Room, Firestore, Firebase Auth, Facebook Login, WorkManager, MPAndroidChart, Data Binding, Navigation, LiveData, ViewModel


    - https://github.com/phicdy/MyCuration

        - RSS Reader for Android with article filtering and curation.


        - Tech Stack = Koin, Coroutines, Testing, Retrofit,  Jsoup, WorkManager, Data Binding, Navigation, LiveData, ViewModel


    - https://github.com/droidconKE/droidconKE2020App

        - Android app fully written in Kotlin for droidconKE2020


        - Tech Stack = Koin, Coroutines, Testing, Retrofit, Room,  Google Auth, Data Binding, Navigation, LiveData, ViewModel


    - https://github.com/fossasia/open-event-attendee-android

        - Open Event Attendee Android General App


        - Tech Stack = Koin, RxJava, Testing, Retrofit, Room, Stripe, PayPal, Mapbox, Paging, Data Binding, Navigation, LiveData, ViewModel


    - https://github.com/dangquanuet/The-Movie-DB-Kotlin

        - The Movie DB app using Kotlin with updated Android features.


        - Tech Stack = Koin, Coroutines, RxJava, Testing, Retrofit, Room,  Paging, Data Binding, Easy Permissions, Navigation, LiveData, ViewModel


    - https://github.com/CharlieChristensen/Cryptotracker

        - Displays live prices of many types of cryptocoins as well as create a wallet to track total portfolio value.


        - Tech Stack = Dagger, Coroutines, Testing, socketIO, Retrofit, Room, MPAndroidChart, Navigation, LiveData, ViewModel


    - https://github.com/haroldadmin/MoonShot

        - A SpaceX companion app for Android


        - Tech Stack = Dagger, Coroutines, Testing, Retrofit, Room, WorkManager, Navigation, LiveData, ViewModel


    - https://github.com/ganainy/Our_chat

        - Private chat app with realtime notification and support audio messages, image sharing, file sharing.


        - Tech Stack = Coroutines, Retrofit, Room, Firestore, Firebase Auth, Firebase Messaging,  Facebook Login, WorkManager, Dexter, Data Binding, Navigation, LiveData, ViewModel


    - https://github.com/flexbooru/flexbooru

        - A booru client for Android, support Danbooru, Moebooru, Gelbooru, Sankaku, etc


        - Tech Stack = Kodein, Coroutines, Testing, Retrofit, Room, WorkManager, Exoplayer, Navigation, Tikxml, LiveData, ViewModel


    - https://github.com/flexbooru/flexbooru-ap

        - An anime-pictures.net client for Android.


        - Tech Stack = Kodein, Coroutines, Retrofit, Room, WorkManager, Navigation, Markwon, LiveData, ViewModel


    - https://github.com/gs-ts/BitfinexClient

        - An android app that showing the details of the BTC/USD currency pair, at real-time.


        - Tech Stack = Koin, RxJava, Testing, Scarlet, Room, Data Binding, LiveData, ViewModel


    - https://github.com/SoftwareEngineeringDaily/software-engineering-daily-android

        - Android client for Software Engineering Daily


        - Tech Stack = Koin, Coroutines, Testing, Retrofit, Room, WorkManager, Exoplayer, Navigation, Android-Permissions, LiveData, ViewModel


    - https://github.com/GreyLabsDev/PexWalls

        - Wallpaper app based on pexels.com API.


        - Tech Stack = Koin, Coroutines, RxJava, Retrofit, Room, Navigation, Markwon, LiveData, ViewModel


    - https://github.com/iammert/AppLocker

        - 🔐 Open source app locker, vault, call blocker application


        - Tech Stack = Dagger, RxJava, Room,  WorkManager, Data Binding, RxPermissions, LiveData, ViewModel


    - https://github.com/xiprox/Upgur

        - An offline-first Imgur client app that lets you view your albums and photos and upload new ones even if you are offline.

        - Tech Stack = Dagger, Retrofit, Room, WorkManager, Navigation, android-upload-service, LiveData, ViewModel


    - https://github.com/AbduallahAtta/Social-Note

        - Social Note - Note-taking, sharing, time & location reminder

        - Tech Stack = Koin, RxJava, Room, Firestore, Firebase Auth, Firebase Storage, Firebase Messaging,  WorkManager, Data Binding, Paging, LiveData, ViewModel


    - https://github.com/kacperczyk-dev/ExchangeRateApp

        - Exchange Rates application

        - Tech Stack = Dagger, Coroutines, Retrofit, Room, WorkManager, Data Binding, MPAndroidChart, Navigation, LiveData, ViewModel


    - https://github.com/cuongpm/youtube-dl-android

        - 📦📦Video downloader for Android - Download videos from Youtube, Facebook, Twitter, Instagram, Dailymotion, Vimeo and more than 1000 other sites

        - Tech Stack = Dagger, RxJava, Testing, Retrofit, Room, Data Binding, LiveData, ViewModel


    - https://github.com/PhilippeBoisney/ArchApp

        - Simple Android app to show how to design a multi-modules MVVM Android app (fully tested)

        - Tech Stack = Koin, Coroutines, Testing, Retrofit, Room, Data Binding, Navigation, LiveData, ViewModel


    - https://github.com/rumaan/file.io-Android-Client

        - ☁️ Unofficial file.io Android App 📱

        - Tech Stack = Testing, Fuel, Room, WorkManager, Navigation, PermissionsDispatcher, LiveData, ViewModel


    - https://github.com/skydoves/Pokedex

        - 🗡️ Android Pokedex using Dagger Hilt, Motion, Coroutines, Jetpack (Room, ViewModel, LiveData) based on MVVM architecture.

        - Tech Stack = Dagger Hilt, Coroutines, Testing, Retrofit, Room, Data Binding, LiveData, ViewModel


    - https://github.com/Wiqaytna-app/wiqaytna_android

        - Wiqaytna is the official Moroccan exposure notification app. (Covid19)

        - Tech Stack = RxJava, Testing, Room, Firebase Storage, Firebase Auth, Firebase Messaging, Firebase Perf, Firebase Functions, Navigation, LiveData, ViewModel


    - https://github.com/ZahraHeydari/MusicPlayer

        - A Simple Audio Player to play (online/offline) songs by running a service in the background and displaying a notification at top of the screen.

        - Tech Stack = Koin, Testing, Room, Firebase Storage, Firebase Auth, Firebase Messaging, Firebase Perf, Firebase Functions,  Navigation, LiveData, ViewModel


    - https://github.com/google/iosched

        - The Google I/O 2019 Android App

        - Tech Stack = Dagger Hilt, Coroutines, Testing, Room, Firestore, Firebase Auth, Firebase Messaging, Firebase Functions, Navigation, ARCore, LiveData, ViewModel


    - https://github.com/romannurik/muzei

        - Muzei Live Wallpaper for Android

        - Tech Stack = Coroutines, Testing, Retrofit, Room, Firebase Perf,  WorkManager, Paging, Navigation, LiveData, ViewModel


    - https://github.com/mozilla-mobile/fenix

        - Firefox Preview

        - Tech Stack = Coroutines, Testing, Retrofit, Room, Firebase Perf,  WorkManager, Paging, Navigation, LiveData, ViewModel


    - https://github.com/rumboalla/apkupdater

        - APKUpdater is an open source tool that simplifies the process of finding updates for your installed apps.

        - Tech Stack = Koin, JSoup, Navigation, LiveData, ViewModel


    - https://github.com/VMadalin/android-modular-architecture

        - 📚 Sample Android Components Architecture on a modular word focused on the scalability, testability and maintainability written in Kotlin, following best practices using Jetpack.

        - Tech Stack = Dagger, Coroutines, Testing, Retrofit, Room, Navigation, Paging, Data Binding, LiveData, ViewModel


    - https://github.com/moezbhatti/qksms

        - The most beautiful SMS messenger for Android

        - Tech Stack = Dagger, Coroutines, RxJava, Testing, Retrofit, Realm, ExoPlayer, Conductor, Data Binding, ShortcutBadger, LiveData, ViewModel


    - https://github.com/ApturiCOVID/apturicovid-android

        - Apturi Covid Android lietotne

        - Tech Stack = Dagger, Coroutines, RxJava, Testing, Retrofit, Room,  WorkManager, Data Binding, ShortcutBadger, LiveData, ViewModel


    - https://github.com/idisfkj/AwesomeGithub

        - Android Github客户端，基于组件化开发，支持账户密码与认证登陆。使用Kotlin语言进行开发，项目架构是基于JetPack&DataBinding的MVVM；同时支持组件开发，使用Arouter进行组件间的跳转；网络框架使用了Retrofit&Coroutine。项目持续更新中，为了防止走失，请做好start准备！😊😊

        - Tech Stack = Coroutines, RxJava, Retrofit, Room, WorkManager, Paging, Navigation, Data Binding, ARouter, LiveData, ViewModel


    - https://github.com/HabitRPG/habitica-android

        - Native Android app for Habitica

        - Tech Stack = Dagger, Coroutines, RxJava, Retrofit, Realm, Firebase Messaging, Paging, Navigation, Facebook, FlowLayout, LiveData, ViewModel

     - https://github.com/KhaledSherifSayed/PopularPeople

        - Popular People is a sample Android application 📱 showing stars of the world 👓 built to demonstrate use of Modern Android development tools. Dedicated to all Android Developers with ❤️.

        - Tech Stack = Koin, Coroutines, Testing, Retrofit, Data Binding, Sandwich, LiveData, ViewModel

     - https://github.com/alisonthemonster/Presently

        - Android app for recording gratitude journal entries.

        - Tech Stack = Dagger, Coroutines, RxJava, Testing, Room, Firebase Messaging,  WorkManager, Dropbox, Calendar view, Paging, Biometric, LiveData, ViewModel

     - https://github.com/fibelatti/raffler-kotlin

        - Raffler is a simple raffling app which intends to make decision making easier

        - Tech Stack = Dagger, Coroutines, Testing, Room, LiveData, ViewModel

    - https://github.com/sanmiAde/Yet_Another_Anime_List
       - Yet Another Anime List is an app that allows you to view upcoming and trending animes on MAL. It also allows you to favourite animes.

       - Tech Stack = Dagger, RxJava, Testing using Fakes, MockWebserver, RxRetrofit, Room, Navigation Components, Lottie, LiveData, ViewModel

    - https://github.com/OMIsie11/CovidNow

       - Simple application for tracking Covid-19 info. Stay safe.😷

       - Tech Stack = Koin, Coroutines, Testing, Retrofit, Room, MPAndroidChart, LiveData, ViewModel


    - https://github.com/KevinGitonga/TukoNewsClient

        - A simple and sleek Android client consuming the Tuko News Api.. Data is fetched from the official tuko.co.ke news API.
        
        - Tech Stack = Coroutines, Retrofit, Room, LiveData, ViewModel
        
    - https://github.com/KevinGitonga/NewsFeed

        - A localized News reader Android app powered by newsapi.org. Will Automatically localize your news based on your location if its supported by API.  
        
        - Tech Stack = Coroutines, Retrofit, Room, Pretty Time, LiveData, ViewModel
        
    - https://github.com/auron567/Gallerit

        - A sample Android gallery to search images posted on Reddit built using modern Android development tools.
        
        - Tech Stack = Koin, Coroutines, Testing, Retrofit, Room, Navigation, Data Binding, LiveData, ViewModel

    - https://github.com/enginebai/MovieHunt

        - 🍿Movie Android App written in Kotlin, MVVM, RxJava, Android Architecture Components. It showcases the app development with well-designed architecture and up-to-date Android tech stacks.
        
        - Tech Stack = Koin, RxJava, Room, Paging, Navigation, Epoxy, LiveData, ViewModel
        
    - https://github.com/Shivamdhuria/flows_guide

        - An Android app with Offline first approach, powered by dog.ceo. Build with amazing transition and animations, following material design principles and modern tech stack.
        
        - Tech Stack = Dagger Hilt, Coroutines, Flows, Retrofit, Room, Material Design Components, Navigation, LiveData, ViewModel
        
    - https://github.com/rizmaulana/kotlin-mvvm-covid19

        - 🦠 Simple COVID19 data monitoring worldwide with interactive chart and map 
        
        - Tech Stack = MVVM, Live Data, Koin, RxJava, RxBinding, Offline first with simple caching, Spek2Framwework for Unit Testing, etc
        
    - https://github.com/Tristankluivert/Knote
          
         - Knote app is a simple yet standard note taking app with features like image addition, ocr, text to speech and more
        
         - Tech Stack = ViewModel, Live Data, Koin, Room db, Coroutines etc
         
     - https://github.com/Devansh-Maurya/PukaPuka
         - An Android app to identify books from their covers and give info, built using ML Kit's Text Recognition API, Android Jetpack Libraries and Google Books API
         
         - Tech Stack = MVVM, Kotlin, LiveData, ViewModel, Navigation Components, CameraKit, Firebase ML Kit Text Recognition API, Glide, Volley, Lottie
          

     - https://github.com/fionicholas/Football-App
         - Football App is an application to show Football Match, List Football Team, and Standing in Leagues from TheSportsDB API
         
         - Tech Stack = MVVM, Kotlin, LiveData, ViewModel, Retrofit, Room, Koin, RxJava, etc
          

    - https://github.com/ryanrvldo/MovieCatalogue
      - Movie catalogue is android application that using some architecture components stuff and fetch API from TMDB. This application can show some movie and tv show that available in TMDB API. Also user can search some related stuff here.
      - Tech Stack = MVVM, ViewModel, LiveData, Coroutines, Firebase Cloud Messaging, Retrofit, Room, Glide, Dagger Hilt, and Google Material.
      
      
    - https://github.com/mutualmobile/Praxis

        - Sample app written in Kotlin which fetches random jokes and displays it.


        - Tech Stack = MVVM architecture, Dagger, Retrofit, Kotlin Coroutines, RXJava2, ViewModel, Data Binding


    - https://github.com/MindorksOpenSource/MVVM-Architecture-Android-Beginners

        - Sample app that implements MVVM architecture using Kotlin.

        - Tech Stack = MVVM architecture, Dagger, Kotlin Coroutines, RXJava2, ViewModel, Data Binding, Live Data.

    - https://github.com/mrcsxsiq/Kotlin-Pokedex

        - Sample app that implements MVVM architecture using Kotlin.

        - Tech Stack = MVVM,  Kotlin, LiveData, Navigation Jetpack, ViewModel, Room, Gradle Kotlin DSL, Databinding, Retrofit, Koin and Ktlint

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