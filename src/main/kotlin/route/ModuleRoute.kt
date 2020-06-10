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

        get("/{fieldOfStudy}/{startYear}/{level}/{semester}") {
            if (call.parameters["semester"]!!.toIntOrNull() == null || call.parameters["startYear"]!!.toIntOrNull() == null || call.parameters["level"]!!.toIntOrNull() == null) {
                call.respond(HttpStatusCode.BadRequest, "Semestr, rok rozpoczęcia i stopień powinny być liczbą")
            }
            call.respond(
                HttpStatusCode.OK, moduleService.getByFoSStartYearDegreeAndSemester(
                    call.parameters["fieldOfStudy"]!!,
                    call.parameters["startYear"]!!,
                    call.parameters["level"]!!,
                    call.parameters["semester"]!!
                )
            )
        }

        get("/elective") {
            call.respond(
                HttpStatusCode.OK,
                moduleService.getElectiveSubjects()
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
