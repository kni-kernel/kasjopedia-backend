package io.project.model

import java.io.Serializable

data class FormattedModule(
    val _id: String,
    val name: String,
    val ects: Int,
    val academicYear: String,
    val level: Int,
    val fieldOfStudy: String,
    val semester: Int,
    var hours: String
) : Serializable {

    constructor(module: Module) : this(
        module._id,
        module.name,
        module.ects,
        module.academicYear,
        module.level,
        module.fieldOfStudy,
        module.semester,
        ""
    ) {
        this.hours = mapHours(module.hours)
    }

    private fun mapHours(hours: HashMap<String, Int>): String {
        return "" + hours["wykład"] + "/" +
                hours["ćwiczenia audytoryjne"] + "/" +
                hours["ćwiczenia laboratoryjne"] + "/" +
                hours["ćwiczenia projektowe"] + "/" +
                hours["zajęcia seminaryjne"] + "/" +
                hours["inne"]
    }
}