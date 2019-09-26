package by.morozova.apod.repository.action

import by.morozova.apod.models.entity.ShortImageInfo
import java.util.concurrent.TimeUnit

fun findMissingImages(
    cached: List<ShortImageInfo>,
    startDate: Long,
    endDate: Long
): List<Pair<Long, Long>> {
    val missingRanges = mutableListOf<Pair<Long, Long>>()
    var rangeStart: Long? = null
    for (date in startDate..endDate step TimeUnit.DAYS.toMillis(1)) {
        if (cached.find { it.date == date } != null) {
            if (rangeStart != null) {
                missingRanges.add(Pair(rangeStart, date - 1))
                rangeStart = null
            }
        } else {
            if (rangeStart == null) {
                rangeStart = date
            }
        }
    }
    return missingRanges
}