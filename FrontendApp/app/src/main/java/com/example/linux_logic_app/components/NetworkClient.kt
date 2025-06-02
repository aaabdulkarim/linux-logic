import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.client.engine.okhttp.*

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.cookies.AcceptAllCookiesStorage
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.JavaNetCookieJar
import java.net.CookieManager
import java.net.CookiePolicy


@Serializable
data class ClientUserRead(
    val username: String = "",
    val password: String,
    val email: String,
    val stayLoggedIn: Boolean = false,
)

val cookieManager = CookieManager().apply {
    setCookiePolicy(CookiePolicy.ACCEPT_ALL)
}
val sharedCookieJar = JavaNetCookieJar(cookieManager)


class NetworkClient {
    val client = HttpClient(OkHttp) {
        engine {
            config {
                cookieJar(sharedCookieJar)
            }
        }
        install(ContentNegotiation) {
            json()
        }
        install(HttpCookies) {
            storage = AcceptAllCookiesStorage()
        }
    }
    private val serverurl = "https://www.linux-logic.com/api"


    suspend fun makeGetRequest(path: String, params: Map<String, String> = emptyMap()): String? {
        return try {
            val response: HttpResponse = client.get(serverurl + path) {
                params.forEach { (key, value) ->
                    parameter(key, value)
                }
            }
            response.bodyAsText()
        } catch (e: Exception) {
            e.printStackTrace()
            println("Error during GET request: ${e.message}")
            null
        }
    }

    suspend fun makePostRequest(path: String, body: ClientUserRead): String? {
        return try {
            println("jason zu strx" + Json.encodeToString(body))
            val response: HttpResponse = client.post(serverurl + path) {
                contentType(ContentType.Application.Json)
                setBody(body)
            }
            println("Response Status: ${response.status}")
            println("Response Headers: ${response.headers.entries()}")
            val text = response.bodyAsText()
            println("Brooo $text")
            text
        } catch (e: Exception) {
            e.printStackTrace()
            println("Error during POST request: ${e.message}")
            null
        }
    }
}

object NetworkService {
    val networkClient = NetworkClient()
}