package com.gabriel.melo.testeeasynvest.rest

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import java.text.SimpleDateFormat
import java.util.*
import com.fasterxml.jackson.core.JsonProcessingException
import java.io.IOException


class CustomDateSerializer @JvmOverloads constructor(t: Class<Date>? = null) :
    StdSerializer<Date>(t) {

    @Throws(IOException::class, JsonProcessingException::class)
    override fun serialize(
        value: Date, gen: JsonGenerator, arg2: SerializerProvider
    ) {
        gen.writeString(formatter.format(value))
    }

    companion object {

        private val formatter = SimpleDateFormat("yyyy-MM-dd")
    }
}