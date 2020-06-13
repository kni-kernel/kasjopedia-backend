# Kasjopeja backend

Część projektu stanowiąca backend do generowania semestralnych planów zajęć - SPZ.

## Environmental variables

Variable        | Description
----------------|---------------
APP_GRPC_NAME   |gRpc host name
APP_GRPC_PORT   |gRpc port
APP_MONGO_HOST  |mongodb host name
APP_CORS_HOSTS  |CORS hosts

## Endpoints

Endpoint          |Method| Description|Example
------------------|------|-----------|---
/module           |GET   |Returns all data from db|-
/module/`{name}`  |GET   |Returns module for given name|/module/`vpython`
/module/elective/`{startYear}` |GET   |Returns all elective modules for given start year|/module/elective/`2017`
/module/`{fieldOfStudy}`/`{startYear}`/`{level}`/`{semester}`|GET|Returns all modules matching parameters|/module/`is`/`2017`/`1`/`3`
/form             |POST  |Returns created pdf from post parameter|-
/module/prev/`{fieldOfStudy}`/`{startYear}`/`{level}`/`{semester}`|GET|Returns all modules matching parameters (+ from previous semesters)|/module/`is`/`2017`/`1`/`3`
