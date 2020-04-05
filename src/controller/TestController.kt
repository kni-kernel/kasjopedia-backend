package io.project.controller

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route

fun Route.testRoutes() {

    route("/") {
        get("") {
            call.respond(HttpStatusCode.OK, "test")
        }
    }
}
