package io.project.model

import java.io.Serializable

data class Module(
    val name: String,
    val ects: Int,
    val fieldOfStudy: String,
    val semester: Int = 0,
    val hours: HashMap<String, Int> = HashMap<String, Int>()
) : Serializable
