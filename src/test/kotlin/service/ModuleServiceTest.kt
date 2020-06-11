package service

import io.project.dao.mongodb.MongoDbDao
import io.project.model.Module
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ModuleServiceTest {

    private val db = MongoDbDao("localhost", "KasjopejaDBTest")

    @Before
    fun fillDb() {
        db.insertDocuments(generateModules())
    }

    @Test
    fun getByNameTest() {
        Assert.assertEquals(
            Module("Matematyka 2", 7, "2017/2018", 1, "is", 2, generateHours()),
            db.getByName("Matematyka 2")
        )
        Assert.assertEquals(
            Module("Matematyka 1", 8, "2017/2018", 1, "is", 1, generateHours()),
            db.getByName("Matematyka 1")
        )
    }

    @Test
    fun getAllTest() {
        Assert.assertEquals(generateModules(), db.getAll())
    }

    @Test
    fun getByFoSStartYearDegreeAndSemesterTest() {
        Assert.assertEquals(
            listOf(Module("Matematyka 1", 8, "2017/2018", 1, "is", 1, generateHours())),
            db.getByFoSStartYearDegreeAndSemester("is", 2017, 1, 1)
        )
    }

    @Test
    fun getElectiveSubjectsTest() {
        Assert.assertEquals(
            listOf(Module("Fizyka Żeglarstwa", 4, "2017/2018", 1, "is", 0, generateHours())),
            db.getElectiveSubjects()
        )
    }

    @After
    fun clearDb() {
        db.clearDb()
    }

    private fun generateHours(): HashMap<String, Int> {
        val hours: HashMap<String, Int> = HashMap()
        hours["L"] = 30
        hours["C"] = 0
        hours["W"] = 30

        return hours
    }

    private fun generateModules(): List<Module> {
        val moduleList = mutableListOf<Module>();
        moduleList.add(Module("Matematyka 1", 8, "2017/2018", 1, "is", 1, generateHours()))
        moduleList.add(Module("Matematyka 2", 7, "2017/2018", 1, "is", 2, generateHours()))
        moduleList.add(Module("Matematyka 3", 5, "2017/2018", 1, "is", 3, generateHours()))
        moduleList.add(Module("Bazy Danych 1", 6, "2017/2018", 1, "is", 5, generateHours()))
        moduleList.add(Module("Algebra Liniowa", 6, "2018/2019", 1, "is", 1, generateHours()))
        moduleList.add(Module("Metody Numeryczne", 6, "2018/2019", 1, "is", 4, generateHours()))
        moduleList.add(Module("Zaawansowane technologie internetowe", 8, "2017/2018", 2, "is", 1, generateHours()))
        moduleList.add(Module("Fizyka Żeglarstwa", 4, "2017/2018", 1, "is", 0, generateHours()))

        return moduleList
    }

}