package io.project.route

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route
import io.project.service.ModuleService

fun Route.moduleRoute(moduleService: ModuleService) {

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
}
