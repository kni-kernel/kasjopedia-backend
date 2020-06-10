package io.project.dao

import io.project.model.Module

interface DbDao {

    fun getByName(name: String): Module?

    fun getAll(): MutableList<Module>

    fun getByFoSStartYearDegreeAndSemester(
        fieldOfStudy: String,
        startYear: Int,
        level: Int,
        semester: Int
    ): List<Module>

    fun getElectiveSubjects(): List<Module>

}
