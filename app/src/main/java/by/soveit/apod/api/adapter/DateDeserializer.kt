package by.soveit.apod.api.adapter

import by.soveit.apod.models.action.DateConverter
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.lang.reflect.Type

class DateDeserializer : JsonDeserializer<Long>, KoinComponent {

    private val dateConverter: DateConverter by inject()

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Long {
        val dateString = json.asString
        return dateConverter.formatter.get()!!.parse(dateString)!!.time
    }
}