Presentation Layer
=

Notes regarding the Presentation layer
-

- Presentation layer includes both data and domain layers.  This is done by adding to gradle: `implementation project(':domain')` and `implementation project(':data')`
- Access to data layer is only required due to the setup of Retrofit.
- Access to the domain layer is required in order to execute use cases from the presenter.
This is could be improved but it is currently done like this for simplicity.
- Presentation layer is responsible to display information in the screen and to forward user events.
I'm currently using MVVM (From Android Architecture Components) for the presentation layer but this could be easily switched to MVP or MVI.
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
