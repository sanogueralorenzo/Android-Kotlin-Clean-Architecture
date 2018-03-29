Data Layer
=

Notes regarding the Data layer
-

- Data layer only has access to the domain layer. This is done by adding to gradle: `implementation project(':domain')`
- Data layer is responsible to save/load from/to disk/cloud.
- Data layer is responsible to have the repositories implementation.
These implementations will implement an interface (contract) from the domain layer and it will be the only way to
pull or push data into the data layer.
- Data layer is responsible to map entities to domain models when pulling something from the repositories.
- Data layer is responsible to map domain models to entities when pushing something to the repositories.
- Data layer could be improved by extracting each data source into different modules with different models.
This is to avoid sharing annotations/information from different data sources (for ex. cache models would not have Moshi annotations since these are only required for remote models).

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
