package com.github.mdashl.mojang.request

import com.github.mdashl.mojang.reply.NameChange
import com.github.mdashl.mojang.reply.Profile

class MojangRequest(val type: Type, parameter: Parameter, value: Any) {
  val url: String = "$BASE_URL${parameter.queryPath}$value${parameter.queryParameters}"

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