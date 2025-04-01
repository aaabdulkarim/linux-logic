/*import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android

class NetworkClient {
    private val client = HttpClient(Android) {

    }
    private val serverurl = "https://localhost:8000"


    suspend fun makeGetRequest(path: String, params: Map<String, String> = emptyMap()): String? {
        return try {
            val response: HttpResponse = client.get(serverurl + path) {
                params.forEach { (key, value) ->
                    parameter(key, value)
                }
            }
            response.bodyAsText()
        } catch (e: Exception) {
            println("Error during GET request: ${e.message}")
            null
        }
    }

    suspend fun makePostRequest(path: String, body: Any): String? {
        return try {
            val response: HttpResponse = client.post(serverurl + path) {
                contentType(ContentType.Application.Json)
                setBody(body)
            }
            response.bodyAsText()
        } catch (e: Exception) {
            println("Error during POST request: ${e.message}")
            null
        }
    }
}

object NetworkService {
    val client = NetworkClient()
}*/