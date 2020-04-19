package io.project.dao.mongodb

import io.project.dao.DbDao
import io.project.model.Module
import org.litote.kmongo.*

class MongoDbDao : DbDao {
    private val client = KMongo.createClient()
    val database = client.getDatabase("KasjopejaDB")
    private val col = database.getCollection<Module>("wfiis")

    override fun getByName(name: String): Module? {
        return col.findOne(Module::name eq name)
    }

    override fun getAll(): MutableList<Module> {
        val data = mutableListOf<Module>()
        col.find().into(data)
        return data
    }

    override fun getByFieldOfStudyAndSemester(fieldOfStudy: String, semester: Int): List<Module> {
        val data = mutableListOf<Module>()
        col.find(Module::fieldOfStudy eq fieldOfStudy, Module::semester eq semester).into(data)
        return data
    }


}
