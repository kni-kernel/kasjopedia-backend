package io.project.dao

import io.project.model.Module

interface DbDao {

    fun getByName(name: String): Module?

    fun getAll(): MutableList<Module>

    fun getByFieldOfStudyAndSemester(fieldOfStudy: String, semester: Int): List<Module>

}
