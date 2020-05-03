package io.project

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.routing.routing
import io.project.connector.GrpcClient
import io.project.dao.mongodb.MongoDbDao
import io.project.route.moduleRoute
import io.project.service.ModuleService
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor

fun main(args: Array<String>): Unit = io.ktor.server.tomcat.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    routing {
        moduleRoute(
            ModuleService(MongoDbDao()),
            GrpcClient(
                ManagedChannelBuilder.forAddress("localhost", 5500).usePlaintext()
                    .executor(Dispatchers.Default.asExecutor()).build()
            )
        )
    }
}
