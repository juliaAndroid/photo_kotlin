package by.soveit.apod.models.action

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DateConverter(
    private val pattern: String
) {

    val formatter = object : ThreadLocal<DateFormat>() {
        override fun initialValue(): DateFormat? {
            val dateFormat = SimpleDateFormat(pattern, Locale.US)
            dateFormat.timeZone = TimeZone.getTimeZone("UTC")
            return dateFormat
        }
    }

    fun longDateToString(date: Long): String {
        return formatter.get()!!.format(Date(date))
    }


}