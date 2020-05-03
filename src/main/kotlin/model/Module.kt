package io.project.model

import java.io.Serializable

data class Module(
    val name: String,
    val ects: Int,
    val fieldOfStudy: String,
    val semester: Int,
    val hours: HashMap<String, Int>
) : Serializable
