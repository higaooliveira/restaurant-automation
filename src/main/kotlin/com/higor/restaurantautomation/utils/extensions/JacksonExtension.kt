package com.higor.restaurantautomation.utils.extensions

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.util.StdDateFormat
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.format.DateTimeFormatterBuilder

object JacksonExtension {

    val jacksonObjectMapper: ObjectMapper by lazy {
        val mapper = ObjectMapper()
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)

        val javaTimeModule = JavaTimeModule()
        javaTimeModule.addSerializer(Instant::class.java, CustomInstantSerializer())

        mapper.dateFormat = SimpleDateFormat(StdDateFormat.DATE_FORMAT_STR_ISO8601)
        mapper.registerModules(
            KotlinModule.Builder()
                .withReflectionCacheSize(512)
                .configure(KotlinFeature.NullToEmptyCollection, false)
                .configure(KotlinFeature.NullToEmptyMap, false)
                .configure(KotlinFeature.NullIsSameAsDefault, false)
                .configure(KotlinFeature.SingletonSupport, false)
                .configure(KotlinFeature.StrictNullChecks, false)
                .build(),
            javaTimeModule,
        )

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
    }
}

fun <T> String.jsonToObject(t: Class<T>): T =
    JacksonExtension.jacksonObjectMapper.readValue(this, t)

fun <T> String.jsonToList(t: Class<T>): List<T> {
    val listType = JacksonExtension.jacksonObjectMapper.typeFactory.constructCollectionType(ArrayList::class.java, t)
    return JacksonExtension.jacksonObjectMapper.readValue(this, listType)
}

fun <T> T.objectToJson(): String =
    JacksonExtension.jacksonObjectMapper.writeValueAsString(this)

private class CustomInstantSerializer :
    InstantSerializer(INSTANCE, false, false, DateTimeFormatterBuilder().appendInstant(3).toFormatter())
