<img src="app/src/main/res/mipmap-xxxhdpi/ic_launcher_round.png" />

[![Actions Status](https://github.com/sanogueralorenzo/Android-Kotlin-Clean-Architecture/workflows/android/badge.svg)](https://github.com/sanogueralorenzo/Android-Kotlin-Clean-Architecture/actions)
[![Kotlin Version](https://img.shields.io/badge/kotlin-1.5.0-blue.svg)](http://kotlinlang.org/)
[![AGP](https://img.shields.io/badge/AGP-4.2.0-blue)](https://developer.android.com/studio/releases/gradle-plugin)
[![Gradle](https://img.shields.io/badge/Gradle-7.0-blue)](https://gradle.org)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)

This repository holds 2 projects:

- [Sample Giphy App](https://github.com/sanogueralorenzo/Android-Kotlin-Clean-Architecture/tree/master/sample) is a test Project that displays paginated trending GIFs from Giphy and also contains search functionality.
    - This small project is a good starting point to get an overview of what current frameworks, architectural decisions and testing amongst other things can be expected on the other project in this repository.
    - I always use this repo to try out new things, benchmark & also write about it.

- [Amiga App](https://github.com/sanogueralorenzo/Android-Kotlin-Clean-Architecture/tree/master/app) is a larger feature modularized project aimed at reinventing the todo-calendar-wellbeing concept.
    - Amiga is a digital wellbeing experiment.

- Both projects are under active development & they both share some common modules like [Views](https://github.com/sanogueralorenzo/Android-Kotlin-Clean-Architecture/tree/master/common/views), [Network](https://github.com/sanogueralorenzo/Android-Kotlin-Clean-Architecture/tree/master/common/network), [Resources](https://github.com/sanogueralorenzo/Android-Kotlin-Clean-Architecture/tree/master/common/resources) and [Cache](https://github.com/sanogueralorenzo/Android-Kotlin-Clean-Architecture/tree/master/common/cache).

My main objective is to achieve with the least amount of code RUDT principles which means that the code must be easy to:
- Read
- Update
- Debug
- Test (Unit & UI)

Personal project to try out things:
- Clean Architecture approach (from a pure approach to a simplified feature modularized approach).
- DI (Dagger 2, Koin & now Dagger Hilt)
- Dependency-less, independent, simplified Navigation with reflection.
- Dynamic Feature Modularized.
- Easy to RUDT (read, update, debug & test).
- Playground to try different architectures.
- Playground to try different libraries/frameworks.

Discussions
-
Refer to the issues section: https://github.com/sanogueralorenzo/Android-Kotlin-Clean-Architecture/issues

Mentions
-
[![androidweekly](https://img.shields.io/badge/androidweekly.net-303-orange.svg)](https://androidweekly.net/issues/issue-303)
[![androidweekly](https://img.shields.io/badge/androidweekly.net-333-orange.svg)](https://androidweekly.net/issues/issue-333)
[![kotlinweekly](https://img.shields.io/badge/kotlinweekly.net-119-blue.svg)](https://mailchi.mp/kotlinweekly/kotlin-weekly-119)
[![androidweekly](https://img.shields.io/badge/androidweekly.net-335-orange.svg)](https://androidweekly.net/issues/issue-335)

Social Media
-
[Twitter](https://twitter.com/MarioSanoguera) | [Medium](https://medium.com/@sanogueralorenzo) | [LinkedIn](https://www.linkedin.com/in/mario-sanoguera-de-lorenzo-b7b392103/)

Articles
-
[Clean Architecture Guide (with tested examples): Data Flow != Dependency Rule](https://proandroiddev.com/clean-architecture-data-flow-dependency-rule-615ffdd79e29)

[The death of Presenters and the rise of ViewModels (AAC)](https://proandroiddev.com/the-death-of-presenters-and-the-rise-of-viewmodels-aac-f14d54b419a)

[Intro to App Architecture](https://proandroiddev.com/intro-to-app-architecture-922b392b21b2)

[Intro to App Modularization](https://proandroiddev.com/intro-to-app-modularization-42411e4c421e)

[Official Kotlin Style Guide with Ktlint](https://proandroiddev.com/official-kotlin-style-guide-with-ktlint-4a649c172956)

[Koin in Feature Modules Project](https://proandroiddev.com/koin-in-feature-modules-project-6329f069f943)

[Detecting Kotlin Code Smells with Detekt](https://proandroiddev.com/detecting-kotlin-code-smells-with-detekt-e79c52a35faf)

[Moshi with Retrofit](https://proandroiddev.com/moshi-with-retrofit-in-kotlin-%EF%B8%8F-a69c2621708b)

[Gradle Dependency Management with Kotlin (buildSrc)](https://proandroiddev.com/gradle-dependency-management-with-kotlin-94eed4df9a28)

License
-

    Copyright 2020 Mario Sanoguera de Lorenzo

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this project except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
