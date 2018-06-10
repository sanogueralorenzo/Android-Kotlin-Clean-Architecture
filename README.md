Android Kotlin Clean Architecture
=

![Android Kotlin](https://github.com/sanogueralorenzo/Android-Kotlin-Clean-Architecture/blob/master/sample_images/android_kotlin.png)

[![CircleCI](https://circleci.com/gh/sanogueralorenzo/Android-Kotlin-Clean-Architecture/tree/master.svg?style=svg)](https://circleci.com/gh/sanogueralorenzo/Android-Kotlin-Clean-Architecture/tree/master) 

Sample project to show how a sample/test app can have some clean architecture without making it hard to understand.

Main benefits:
- Easy to understand some concepts from Clean Architecture.
- Applies & follows design principles.
- Layer separation by modules (not packages) to set clear boundaries between each layer.
- Easy to read, scale, test and maintain.

Some extra notes:
- Simplified Clean Architecture approach .
- For Continuous integration I've used [CircleCI](https://circleci.com/gh/sanogueralorenzo/Android-Kotlin-Clean-Architecture). You can check the the config file [here](https://github.com/sanogueralorenzo/Android-Kotlin-Clean-Architecture/blob/master/.circleci/config.yml)
- Each module will have its own readme to try and throw some light under how and why of the decisions.

Shortcuts to the module Readme

[Presentation Module](https://github.com/sanogueralorenzo/Android-Kotlin-Clean-Architecture/blob/master/presentation/README.md)

[Domain Module](https://github.com/sanogueralorenzo/Android-Kotlin-Clean-Architecture/blob/master/domain/README.md)

[Data Module](https://github.com/sanogueralorenzo/Android-Kotlin-Clean-Architecture/blob/master/data/README.md)

Quick overview of the app
-

Using the API endpoints
- GET http://jsonplaceholder.typicode.com/posts
- GET http://jsonplaceholder.typicode.com/users
- GET http://jsonplaceholder.typicode.com/comments

Create a simple Android app with three screens:
- Screen1: Post list screen that contains:
    - User avatar, name and username of each post.
    - Post title and ellipsized body.

Taping a cell should take you to Screen2.

- Screen2: Post Detail screen that contains:
    - User avatar, name and username of this post.
    - Post title and full post body.
    - Number of comments this post has.
    - List of comments under this post, displaying the avatar and name of the user who has written this comment and the body of this comment.

Taping the user avatar from Screen1 or Screen2 (selected user) should take you to Screen3.

- Screen3: User Detail screen that contains:
    - User related information (name, username, email, phone, address, website and company.
    - Map showing the location of this user.

Clean Architecture
-
![Clean Architecture](https://github.com/sanogueralorenzo/Android-Kotlin-Clean-Architecture/blob/master/sample_images/clean_architecture.png)

Architectural approach
-
![Clean Architecture Layers](https://github.com/sanogueralorenzo/Android-Kotlin-Clean-Architecture/blob/master/sample_images/clean_architecture_layers.png)

Architectural reactive approach
-
![Clean Architecture Layers Details](https://github.com/sanogueralorenzo/Android-Kotlin-Clean-Architecture/blob/master/sample_images/clean_architecture_layers_details.png)

Discussions
-

Refer to the issues section: https://github.com/sanogueralorenzo/Android-Kotlin-Clean-Architecture/issues

Mentions
-
[![androidweekly](https://img.shields.io/badge/androidweekly.net-303-orange.svg?style=flat-square)](http://androidweekly.net/issues/issue-303)

Links
-
[Medium](https://medium.com/@sanogueralorenzo)

[LinkedIn](https://www.linkedin.com/in/mario-sanoguera-de-lorenzo-b7b392103/)

[Twitter](https://twitter.com/MarioSanoguera)

[Moshi with Retrofit](https://proandroiddev.com/moshi-with-retrofit-in-kotlin-%EF%B8%8F-a69c2621708b)

[Gradle Dependency Management with Kotlin (buildSrc)](https://proandroiddev.com/gradle-dependency-management-with-kotlin-94eed4df9a28)

[The death of Presenters and the rise of ViewModels (AAC)](https://proandroiddev.com/the-death-of-presenters-and-the-rise-of-viewmodels-aac-f14d54b419a)

[CircleCI Android Documentation](https://circleci.com/docs/2.0/language-android/)

License
-

    Copyright 2018 Mario Sanoguera de Lorenzo

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
