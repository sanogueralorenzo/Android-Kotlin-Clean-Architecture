Domain Layer
=

Notes regarding the Domain layer
-

- Domain layer shouldn't include presentation and data layers.
- Domain layer will have all of your business logic.
- Domain layer will have use cases/interactors. These are in charge of retrieving data from 1 or multiple data sources (repositories/gateways).
- Domain layer could be improved by adding request objects and or policies instead of having cache/remote methods in its interface.
This is to avoid and reduce the abstract leaking of information caused by the contract of the repositories that sit in the domain layer.
- Caching and Remote loading of information from use cases can have different approaches and there is no right or wrong.
You just need to investigate around the pros and cons of each one and decide one based on the current requirements and the near future of the project.
This means you could have for example a unique source of truth in the data layer with some logic around the data management and would still be a valid approach.

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
