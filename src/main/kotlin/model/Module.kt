package io.project.model

import java.io.Serializable

data class Module(
    val _id: String,
    val name: String,
    val ects: Int,
    val academicYear: String,
    val level: Int,
    val fieldOfStudy: String,
    val semester: Int,
    val hours: HashMap<String, Int>
) : Serializable
