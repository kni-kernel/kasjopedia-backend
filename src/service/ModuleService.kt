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
        db.add(python)
        db.add(vpython)
    }

    fun getByName(name: String): Module? {
        return db.find { module -> module.name == name }
    }

    fun getAll(): MutableList<Module> {
        return db
    }
}
