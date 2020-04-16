package io.project.service

import io.project.model.Module

class ModuleService {

    //mocked data base
    private val db = mutableListOf<Module>()

    init {
        //test values
        val vpython = Module("vpython", 3, "", 0)
        vpython.setHours(15, 0, 30, 0, 0, 0)
        val python = Module("python", 4, "", 0)
        python.setHours(0, 0, 45, 0, 0, 0)
        val cpp = Module("podstawy-programowania-obiektowego", 6, "is", 3)
        cpp.setHours(30, 0, 30, 0, 0, 0)
        val cpp2 = Module("programowanie-obiektowe-1", 6, "is", 4)
        cpp2.setHours(30, 0, 30, 0, 0, 0)
        val cpp3 = Module("programowanie-obiektowe-2", 6, "is", 5)
        cpp3.setHours(30, 0, 30, 0, 0, 0)
        val graphs = Module("teoria-grafow", 4, "is", 5)
        graphs.setHours(30, 0, 30, 0, 0, 0)
        db.add(python)
        db.add(vpython)
        db.add(cpp)
        db.add(cpp2)
        db.add(cpp3)
        db.add(graphs)
    }

    fun getByName(name: String): Module? {
        return db.find { module -> module.name == name }
    }

    fun getAll(): MutableList<Module> {
        return db
    }

    fun getByFieldOfStudyAndSemester(fieldOfStudy: String, semester: Int): List<Module> {
        return db.filter { module -> module.fieldOfStudy == fieldOfStudy && module.semester == semester }
    }
}
