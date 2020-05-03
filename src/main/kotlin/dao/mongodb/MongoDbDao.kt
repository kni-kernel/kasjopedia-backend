package io.project.dao.mongodb

import io.project.dao.DbDao
import io.project.model.Module
import org.litote.kmongo.*

class MongoDbDao : DbDao {
    private val collection = KMongo.createClient().getDatabase("KasjopejaDB").getCollection<Module>("wfiis")

    override fun getByName(name: String): Module? {
        return collection.findOne(Module::name eq name)
    }

    override fun getAll(): MutableList<Module> {
        val data = mutableListOf<Module>()
        collection.find().into(data)
        return data
    }

    override fun getByFieldOfStudyAndSemester(fieldOfStudy: String, semester: Int): List<Module> {
        val data = mutableListOf<Module>()
        collection.find(Module::fieldOfStudy eq fieldOfStudy, Module::semester eq semester).into(data)
        return data
    }


}
