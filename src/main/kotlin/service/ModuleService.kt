package io.project.service

import io.project.dao.DbDao
import io.project.model.FormattedModule

class ModuleService(private val dao: DbDao) {

    fun getByName(name: String): FormattedModule? {
        return dao.getByName(name.replace("_", " "))?.let { FormattedModule(it) }
    }

    fun getAll(): List<FormattedModule> {
        return dao.getAll().map { module -> FormattedModule(module) }
    }

    fun getByFoSStartYearDegreeAndSemester(
        fieldOfStudy: String,
        startYear: String,
        level: String,
        semester: String
    ): List<FormattedModule> {
        return dao.getByFoSStartYearDegreeAndSemester(fieldOfStudy, startYear.toInt(), level.toInt(), semester.toInt())
            .map { module -> FormattedModule(module) }
    }

    fun getByFoSStartYearDegreeAndSemesterPlusPrevSemesters(
        fieldOfStudy: String,
        startYear: String,
        level: String,
        semester: String
    ): List<FormattedModule> {
        return dao.getByFoSStartYearDegreeAndSemesterPlusPrevSemesters(
            fieldOfStudy,
            startYear.toInt(),
            level.toInt(),
            semester.toInt()
        ).map { module -> FormattedModule(module) }
    }

    fun getElectiveSubjects(startYear: String): List<FormattedModule> {
        return dao.getElectiveSubjects(startYear.toInt()).map { module -> FormattedModule(module) }
    }
}
