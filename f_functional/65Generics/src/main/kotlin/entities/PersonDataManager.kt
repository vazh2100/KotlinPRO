package com.vazh2100.entities

import kotlinx.serialization.json.Json
import java.io.File

object PersonDataManager {
    private val profilesFile = File("profiles.json")


    private val _profiles: MutableList<Person> = loadProfiles()
    val profiles get() = _profiles.toList()


    private fun loadProfiles(): MutableList<Person> {
        val content = profilesFile.readText().trim()
        return Json.decodeFromString(content)

    }
}