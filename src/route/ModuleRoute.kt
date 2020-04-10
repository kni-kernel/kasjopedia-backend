package io.project.route

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route
import io.project.service.ModuleService

val moduleService = ModuleService()

fun Route.moduleRoute() {

    route("/module") {
        get("/{name}") {
            call.respond(HttpStatusCode.OK, moduleService.getByName(call.parameters["name"]!!) ?: "")
        }

        get {
            call.respond(HttpStatusCode.OK, moduleService.getAll())
        }
    }
}
