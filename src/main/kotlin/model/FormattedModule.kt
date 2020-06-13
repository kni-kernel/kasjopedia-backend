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
        return "" + hours.getOrDefault("wykład", 0) + "/" +
                hours.getOrDefault("ćwiczenia audytoryjne", 0) + "/" +
                hours.getOrDefault("ćwiczenia laboratoryjne", 0) + "/" +
                hours.getOrDefault("ćwiczenia projektowe", 0) + "/" +
                hours.getOrDefault("zajęcia seminaryjne", 0) + "/" +
                hours.getOrDefault("inne", 0)
    }
}