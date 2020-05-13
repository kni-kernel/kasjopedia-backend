package io.project.route

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.project.connector.GrpcClient
import io.project.service.ModuleService

fun Route.moduleRoute(moduleService: ModuleService, pdfService: GrpcClient) {

    route("/module") {
        get("/{name}") {
            call.respond(HttpStatusCode.OK, moduleService.getByName(call.parameters["name"]!!) ?: "")
        }

        get {
            call.respond(HttpStatusCode.OK, moduleService.getAll())
        }

        get("/{fieldOfStudy}/{semester}") {
            if (call.parameters["semester"]!!.toIntOrNull() == null) {
                call.respond(HttpStatusCode.BadRequest, "Semestr powinien być liczbą")
            }
            call.respond(
                HttpStatusCode.OK, moduleService.getByFieldOfStudyAndSemester(
                    call.parameters["fieldOfStudy"]!!, call.parameters["semester"]!!
                )
            )
        }
    }

    route("/form") {
        post {
            val post = call.receive<String>()
            call.respond(HttpStatusCode.OK, pdfService.sendRequest(post).blob.toByteArray())
        }
    }

}
