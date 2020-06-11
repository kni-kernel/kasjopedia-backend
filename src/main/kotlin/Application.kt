package io.project

import com.fasterxml.jackson.databind.SerializationFeature
import io.grpc.ManagedChannelBuilder
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.jackson.jackson
import io.ktor.routing.routing
import io.project.connector.GrpcClient
import io.project.dao.mongodb.MongoDbDao
import io.project.route.moduleRoute
import io.project.service.ModuleService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor

fun main(args: Array<String>): Unit = io.ktor.server.tomcat.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
fun Application.module() {


    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Get)
        method(HttpMethod.Post)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.AccessControlAllowHeaders)
        header(HttpHeaders.ContentType)
        header(HttpHeaders.AccessControlAllowOrigin)
        host(environment.config.property("ktor.cors.hosts").getString())
    }


    routing {
        moduleRoute(
            ModuleService(
                MongoDbDao(
                    environment.config.property("ktor.deployment.mongoHost").getString(),
                    "KasjopejaDB"
                )
            ),
            GrpcClient(
                ManagedChannelBuilder.forAddress(
                    environment.config.property("ktor.deployment.grpcName").getString(),
                    environment.config.property("ktor.deployment.grpcPort").getString().toInt()
                )
                    .usePlaintext()
                    .executor(Dispatchers.Default.asExecutor()).build()
            )
        )
    }
}
