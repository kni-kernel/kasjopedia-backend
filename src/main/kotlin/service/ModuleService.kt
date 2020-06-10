package io.project.service

import io.project.dao.DbDao
import io.project.model.Module

class ModuleService(private val dao: DbDao) {

    fun getByName(name: String): Module? {
        return dao.getByName(name.replace("_", " "))
    }

    fun getAll(): MutableList<Module> {
        return dao.getAll()
    }

    fun getByFoSStartYearDegreeAndSemester(
        fieldOfStudy: String,
        startYear: String,
        level: String,
        semester: String
    ): List<Module> {
        return dao.getByFoSStartYearDegreeAndSemester(fieldOfStudy, startYear.toInt(), level.toInt(), semester.toInt())
    }

    fun getElectiveSubjects(): List<Module> {
        return dao.getElectiveSubjects()
    }
}
