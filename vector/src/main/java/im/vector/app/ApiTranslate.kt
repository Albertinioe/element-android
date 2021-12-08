/*
 * Copyright (c) 2021 New Vector Ltd
 * Copyright 2021 Qwerty Networks
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.vector.app


import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiTranslate {
    suspend fun runPost(message: String, lng: String) : String {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://m.mybusines.app") // https://m.mybusines.app/api/translate
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(ApiService::class.java)

        val jsonObject = JSONObject()
        jsonObject.put("phrase", message)
        jsonObject.put("lng", lng)

        val jsonObjectString = jsonObject.toString()

        var translate = ""

        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaType())

        try {
            val response = service.createEmployee(requestBody)
            translate = response.translation
            println("Translation phrase = $translate")
        } catch (e: HttpException) {
            println("Retry error: ${e.code()}")
        }
        return translate
    }
}
