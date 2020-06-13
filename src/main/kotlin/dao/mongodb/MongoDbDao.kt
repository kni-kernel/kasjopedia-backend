package io.project.dao.mongodb

import io.project.dao.DbDao
import io.project.model.Module
import org.litote.kmongo.*

class MongoDbDao constructor(
    mongoHostName: String,
    dbName: String
) : DbDao {
    private val collection =
        KMongo.createClient("mongodb://${mongoHostName}").getDatabase(dbName).getCollection<Module>("wfiis")

    override fun getByName(name: String): Module? {
        return collection.findOne(Module::name eq name)
    }

    override fun getAll(): MutableList<Module> {
        val data = mutableListOf<Module>()
        collection.find().into(data)
        return data
    }

    override fun getByFoSStartYearDegreeAndSemester(
        fieldOfStudy: String,
        startYear: Int,
        level: Int,
        semester: Int
    ): List<Module> {
        val data = mutableListOf<Module>()
        collection.find(
            Module::fieldOfStudy eq fieldOfStudy,
            Module::semester eq semester,
            Module::level eq level,
            Module::academicYear eq "$startYear/${startYear + 1}"
        )
            .into(data)
        return data
    }

    override fun getByFoSStartYearDegreeAndSemesterPlusPrevSemesters(
        fieldOfStudy: String,
        startYear: Int,
        level: Int,
        semester: Int
    ): List<Module> {
        val data = mutableListOf<Module>()
        collection.find(
            Module::fieldOfStudy eq fieldOfStudy,
            Module::semester lt semester,
            Module::semester gt 0,
            Module::level eq level,
            Module::academicYear eq "$startYear/${startYear + 1}"
        )
            .into(data)
        return data
    }

    override fun getElectiveSubjects(startYear: Int): List<Module> {
        val data = mutableListOf<Module>()
        collection.find(Module::semester eq 0, Module::academicYear eq "$startYear/${startYear + 1}").into(data)
        return data
    }

    fun insertDocuments(moduleList: List<Module>) {
        collection.insertMany(moduleList);
    }

    fun clearDb() {
        collection.deleteMany("{}")
    }

}
