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

    fun getByFieldOfStudyAndSemester(fieldOfStudy: String, semester: String): List<Module> {
        return dao.getByFieldOfStudyAndSemester(fieldOfStudy.replace("_", " "), semester.toInt())
    }
}
