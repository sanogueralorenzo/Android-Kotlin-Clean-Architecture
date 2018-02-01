Presentation Layer
=

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


Notes regarding the Presentation layer
-

- Presentation layer includes both data and domain layers.  This is done by adding to gradle: `compile project(':domain')` and `compile project(':data')`
- Access to data layer is only required due to the setup of Retrofit.
- Access to the domain layer is required in order to execute use cases from the presenter.
This is wrong but it is currently done for simplicity. Eventually you should have an interface the same way you have one between domain and data layers with request objects if required.
- Presentation layer is responsible to display information in the screen and to forward user events.
I'm currently using MVP for the presentation layer but this could be easily switched to MVVM or MVI.
- Presentation layer is responsible to map domain models to presentation models when pulling something from the use cases (domain).
- Presentation layer is responsible to map presentation models to domain models when pushing something to the use cases (domain).

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
