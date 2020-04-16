package io.project.model

import java.io.Serializable

data class Module(val name: String, val ects: Int, val fieldOfStudy: String, val semester: Int = 0) : Serializable {
    val hours = HashMap<String, Int>()

    fun setHours(lecturers: Int, auditorium: Int, laboratory: Int, seminar: Int, project: Int, practical: Int) {
        hours["lecturers"] = lecturers
        hours["auditorium"] = auditorium
        hours["laboratory"] = laboratory
        hours["seminar"] = seminar
        hours["project"] = project
        hours["practical"] = practical
    }
}
