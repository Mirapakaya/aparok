package com.aparokio.aparok.persistence

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.persistence.common.serializeWallpaperSettings
import com.aparokio.aparok.persistence.model.WallpaperSetting
import com.aparokio.aparok.test_utils.MockDataFactory
import kotlin.test.assertEquals
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class SerializationTest {

    @Test
    fun `should serialize-deserialize map with wallpaper settings`() {

        val id = MockDataFactory.randomUuid()
        val type = MockDataFactory.randomInt()
        val value = MockDataFactory.randomString()

        val givenMap = mapOf(
            id to WallpaperSetting(
                type = type,
                value = value
            )
        )

        val encoded = givenMap.serializeWallpaperSettings()

        val decoded = Json.decodeFromString<Map<Id, WallpaperSetting>>(encoded)

        assertEquals(
            expected = givenMap,
            actual = decoded
        )
    }

}