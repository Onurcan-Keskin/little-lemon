package com.onurcan.little_lemon.utils

import com.onurcan.little_lemon.data.AppDatabase
import com.onurcan.little_lemon.data.MenuItemNetwork
import com.onurcan.little_lemon.data.MenuNetwork
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json

internal fun validateRegData(firstName: String, lastName: String, email: String): Boolean {
    var validated = false

    if (firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank()) {
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) validated = true
    }

    return validated
}

internal suspend fun fetchMenu(url: String): List<MenuItemNetwork> {
    val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }
    val httpResponse: MenuNetwork = httpClient.get(url).body()
    return httpResponse.items
}

internal fun saveMenuToDatabase(database: AppDatabase, menuItemsNetwork: List<MenuItemNetwork>) {
    val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
    database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
}