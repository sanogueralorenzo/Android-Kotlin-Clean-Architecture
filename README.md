Android Kotlin Clean Architecture
=

Android Kotlin Clean Architecture project is my attempt to show how a sample/test app can have some clean architecture thrown into it without making it hard to understand.

The main benefits from this project are:
- Makes it easy to understand some concepts from clean architecture.
- Shows how easy it is to apply/follow design principles (separation of concerns for ex.) and how your project can benefit from them.
- Shows how easy it is to have different modules in a project and tries to take the fear away from 1 module, several packages for the different layers.
- Easy to read, scale, test and maintain.

Once this project is understood I highly recommend [https://github.com/android10/Android-CleanArchitecture](https://github.com/android10/Android-CleanArchitecture)
it is a complete different app but it contains more clean architecture knowledge.

I added few links at the bottom, kudos to android10's for the images and links which I've added here for simplicity.

Some extra notes:
- To make it as simple as possible I've skipped some things and kept only the most important ones from the Clean Architecture approach.
- Each module will have its own readme to try and throw some light under how and why of the decisions.

Quick overview of the app
-

Using the API endpoints
- GET http://jsonplaceholder.typicode.com/posts
- GET http://jsonplaceholder.typicode.com/users
- GET http://jsonplaceholder.typicode.com/comments

Create a simple Android app with two screens:
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
![http://fernandocejas.com/2015/07/18/architecting-android-the-evolution/](https://github.com/sanogueralorenzo/Android-Kotlin-Clean-Architecture/blob/master/sample_images/clean_architecture.png)

Architectural approach
-
![http://fernandocejas.com/2015/07/18/architecting-android-the-evolution/](https://github.com/sanogueralorenzo/Android-Kotlin-Clean-Architecture/blob/master/sample_images/clean_architecture_layers.png)

Architectural reactive approach
-
![http://fernandocejas.com/2015/07/18/architecting-android-the-evolution/](https://github.com/sanogueralorenzo/Android-Kotlin-Clean-Architecture/blob/master/sample_images/clean_architecture_layers_details.png)

Discussions
-

Refer to the issues section: https://github.com/sanogueralorenzo/Android-Kotlin-Clean-Architecture/issues

Links
-

[Architecting Android…The clean way?](http://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/)

[Architecting Android…The evolution](http://fernandocejas.com/2015/07/18/architecting-android-the-evolution/)

[Tasting Dagger 2 on Android](http://fernandocejas.com/2015/04/11/tasting-dagger-2-on-android/)

[Clean Architecture…Dynamic Parameters in Use Cases](http://fernandocejas.com/2016/12/24/clean-architecture-dynamic-parameters-in-use-cases/)

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
