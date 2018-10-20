package ru.mdash.mojang.request

import ru.mdash.mojang.reply.NameChange
import ru.mdash.mojang.reply.Profile

class MojangRequest(val type: Type, private val parameter: Pair<Parameter, Any>) {
  val url: String by lazy {
    var url = BASE_URL

    val (key, value) = parameter

    url += "${key.queryPath}$value${key.queryParameters}"

    url
  }

  enum class Parameter(val queryPath: String, val queryParameters: String = "") {
    PROFILE_BY_NAME("users/profiles/minecraft/"),
    PROFILE_BY_NAME_TIMESTAMP("users/profiles/minecraft/", "?at=0"),
    NAME_HISTORY_BY_UUID("user/profiles/", "/names");
  }

  enum class Type(val replyClass: Class<*>) {
    PROFILE(Profile::class.java),
    NAME_HISTORY(Array<NameChange>::class.java);
  }

  companion object {
    private const val BASE_URL = "https://api.mojang.com/"
  }
}