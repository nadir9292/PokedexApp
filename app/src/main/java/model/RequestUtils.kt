package model

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

private val client = OkHttpClient()
private val gson = Gson()

const val URL_API_POKEMON =
    "https://pokeapi.co/api/v2/pokemon/charizard"

class RequestUtils {

    companion object {
        fun loadWeather(pokemon: String): PokemonBean {
            val json: String = sendGet(URL_API_POKEMON.format(pokemon))

            val pokemon = gson.fromJson(json, PokemonBean::class.java)

            return pokemon
        }

        private fun sendGet(url: String): String {
            println("url : $url")
            val request = Request.Builder().url(url).build()
            return client.newCall(request).execute().use {
                if (!it.isSuccessful) {
                    throw Exception("Réponse du serveur incorrect :${it.code}")
                }
                it.body?.string() ?: ""
            }
        }
    }
}