package io.project.model

import java.io.Serializable

data class Module(
    val _id: String,
    val name: String,
    val ects: Int,
    val academicYear: String,
    val level: Int,
    val fieldOfStudy: String,
    val semester: Int
) : Serializable {
    var hours: String = ""

    constructor(
        _id: String,
        name: String,
        ects: Int,
        academicYear: String,
        level: Int,
        fieldOfStudy: String,
        semester: Int, hours: HashMap<String, Int>
    ) : this(_id, name, ects, academicYear, level, fieldOfStudy, semester) {
        this.hours = "" + hours["wykład"] + "/" +
                hours["ćwiczenia audytoryjne"] + "/" +
                hours["ćwiczenia laboratoryjne"] + "/" +
                hours["ćwiczenia projektowe"] + "/" +
                hours["zajęcia seminaryjne"] + "/" +
                hours["inne"]
    }
}
