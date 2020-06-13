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

Endpoint          | Description
------------------|-----------
/module           |Returns all data from db
/module/`{name}`  |Returns module for given name
/module/elective  |Returns all elective modules
/module/`{fieldOfStudy}`/`{startYear}`/`{level}`/`{semester}`|Returns all modules matching parameters
