package ru.mdash.mojang

import com.google.gson.Gson
import com.google.gson.JsonParser
import org.jsoup.Jsoup
import ru.mdash.mojang.request.MojangRequest

@Suppress("unused")
object MojangAPI {
  private val gson = Gson()
  private val jsonParser = JsonParser()

  @Suppress("UNCHECKED_CAST")
  fun <R> get(request: MojangRequest): R {
    val json = Jsoup
        .connect(request.url)
        .ignoreContentType(true)
        .execute()
        .body()

    return gson.fromJson(jsonParser.parse(json), request.type.replyClass) as R
  }
}